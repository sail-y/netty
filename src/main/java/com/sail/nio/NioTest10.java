package com.sail.nio;

import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * 文件锁
 * @author yangfan
 * @date 2017/08/17
 */
public class NioTest10 {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest10.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        // 从索引3锁6个长度
        // true表示共享锁，false表示排他锁
        FileLock fileLock = fileChannel.lock(3, 6, true);

        System.out.println("valid: " + fileLock.isValid());
        System.out.println("lock type: " + fileLock.isShared());

        fileLock.release();

        randomAccessFile.close();
    }
}
