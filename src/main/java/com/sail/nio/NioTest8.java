package com.sail.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * DirectByteBuffer
 * @author yangfan
 * @date 2017/08/17
 */
public class NioTest8 {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("input2.txt");
        FileOutputStream outputStream = new FileOutputStream("output2.txt");

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(512);

        while (true) {
            buffer.clear();

            int read = inputChannel.read(buffer);
            System.out.println("read: " + read);

            if (-1 == read) {
                break;
            }

            buffer.flip();

            outputChannel.write(buffer);
        }


        inputChannel.close();
        outputChannel.close();

    }
}
