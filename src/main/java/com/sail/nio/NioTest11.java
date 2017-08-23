package com.sail.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.stream.Stream;

/**
 * 关于Buffer的Scattering与Gathering
 *
 * @author yangfan
 * @date 2017/08/17
 */
public class NioTest11 {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(address);

        int messageLength = 2 + 3 + 4;

        ByteBuffer[] buffers = new ByteBuffer[3];

        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();

        while (true) {
            int bytesRead = 0;
            while (bytesRead < messageLength) {
                // 数组类型的读
                long r = socketChannel.read(buffers);

                bytesRead += r;

                System.out.println("bytesRead: " + bytesRead);

                Stream.of(buffers)
                        .map(buffer -> "position: " + buffer.position() + ", limit: " + buffer.limit())
                        .forEach(System.out::println);

            }

            Stream.of(buffers).forEach(Buffer::flip);


            long bytesWritten = 0;

            while (bytesWritten < messageLength) {
                long r = socketChannel.write(buffers);
                bytesWritten += r;
            }

            Stream.of(buffers).forEach(Buffer::clear);

            System.out.println("bytesRead:  " + bytesRead + "， byteWritten: " + bytesWritten + ", messageLength: " + messageLength);
        }
    }
}
