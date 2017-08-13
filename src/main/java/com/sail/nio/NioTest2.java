package com.sail.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author yangfan
 * @date 2017/08/13
 */
public class NioTest2 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("NioTest2.txt");
        // 通过文件输入流可以获取到通道对象
        FileChannel fileChannel = fileInputStream.getChannel();

        // 无论读写都必须通过Buffer来操作
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        fileChannel.read(byteBuffer);

        // 之前是往Buffer里面写，现在进行Buffer的读，所以要调用flip()方法
        byteBuffer.flip();

        while (byteBuffer.hasRemaining()) {
            byte b = byteBuffer.get();
            System.out.println("Character: " + (char) b);
        }

        fileInputStream.close();

    }
}
