package com.sail.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author yangfan
 * @date 2017/08/15
 */
public class NioTest4 {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("input.txt");
        FileOutputStream outputStream = new FileOutputStream("output.txt");

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);

        while (true) {
            // 如果删除这行代码，第一次运行后position和limit是相等的，那么这个时候是不能再写入数据的了，所以read会返回0个字节。
            buffer.clear();

            // 先读取数据,返回值是每次读取的字节
            int read = inputChannel.read(buffer);
            System.out.println("read: " + read);

            // 第一次读取完成后，调用clear()将position设置为0，limit归位。
            // 那么这个时候返回的read应该是-1，因为Channel里面已经没有数据了
            if (-1 == read) {
                break;
            }

            // 翻转
            // position为0，limit为之前的position
            buffer.flip();

            // 写入数据
            outputChannel.write(buffer);
        }


        inputChannel.close();
        outputChannel.close();

    }
}
