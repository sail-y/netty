package com.sail.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * 模拟聊天
 * @author yangfan
 * @date 2017/08/27
 */
public class NioServer {

    private static Map<String, SocketChannel> clientMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            try {
                int nums = selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                selectionKeys.forEach(selectionKey -> {
                    final SocketChannel client;
                    try {
                        if (selectionKey.isAcceptable()) {
                            ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                            client = server.accept();
                            client.configureBlocking(false);
                            client.register(selector, SelectionKey.OP_READ);


                            // 每次有客户端连接后，生成一个ID并放入map中
                            String key = "[" + UUID.randomUUID().toString() + "]";
                            clientMap.put(key, client);

                        } else if (selectionKey.isReadable()) {
                            client = (SocketChannel) selectionKey.channel();


                            ByteBuffer readBuffer = ByteBuffer.allocate(1024);


                            int count = client.read(readBuffer);
                            String senderKey = null;

                            for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                if (client == entry.getValue()) {
                                    senderKey = entry.getKey();
                                    break;
                                }
                            }

                            if (count > 0) {
                                readBuffer.flip();
                                Charset charset = Charset.forName("utf-8");
                                String receivedMessage = String.valueOf(charset.decode(readBuffer).array());
                                System.out.println(client + ":" + receivedMessage);

                                for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                    SocketChannel value = entry.getValue();

                                    ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

                                    writeBuffer.put((senderKey + ": " + receivedMessage).getBytes());
                                    writeBuffer.flip();

                                    value.write(writeBuffer);

                                }

                                if (receivedMessage.equals("1")) {
                                    selectionKeys.clear();


                                }
                            }else if (count == -1) {
                                // 判断是否客户端断开了连接
                                clientMap.remove(senderKey);
                            }

                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });

                // 每次读取完成状态后必须清除，因为每一个selectionKey的channel只能被读取一次
                selectionKeys.clear();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
