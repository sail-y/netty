package com.sail.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author yangfan
 * @date 2017/08/23
 */
public class NioTest12 {
    public static void main(String[] args) throws IOException {
        int[] ports = new int[5];

        ports[0] = 5000;
        ports[1] = 5001;
        ports[2] = 5002;
        ports[3] = 5003;
        ports[4] = 5004;

        Selector selector = Selector.open();

        for (int i = 0; i < ports.length; i++) {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // 配置成非阻塞
            serverSocketChannel.configureBlocking(false);
            ServerSocket serverSocket = serverSocketChannel.socket();
            InetSocketAddress address = new InetSocketAddress(ports[i]);
            serverSocket.bind(address);

            // register()会触发一个SelectionKey被添加到Selector的keys中，可以用selector.keys()查看。
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            // System.out.println(selector.keys().size());


            System.out.println("监听端口： " + ports[i]);
        }


        while (true) {
            int numbers = selector.select();
            System.out.println("numbers: " + numbers);


            // 准备好的通道（有客户端连接了）
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iter = selectionKeys.iterator();
            // 准备建立连接
            while (iter.hasNext()) {


                SelectionKey selectionKey = iter.next();
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector, SelectionKey.OP_READ);

                    iter.remove();
                    System.out.println("获得客户端连接： " + socketChannel);
                } else if (selectionKey.isReadable()) {
                    // 接收数据
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                    int bytesRead = 0;

                    while (true) {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                        byteBuffer.clear();

                        int read = socketChannel.read(byteBuffer);

                        if (read <= 0) {
                            break;
                        }

                        byteBuffer.flip();

                        socketChannel.write(byteBuffer);

                        bytesRead += read;

                    }

                    System.out.println("读取： " + bytesRead + ", 来自于: " + socketChannel);
                    iter.remove();
                }


            }

        }
    }
}
