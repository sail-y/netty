package com.sail.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author yangfan
 * @date 2017/09/13
 */
public class NewIOClient {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8899));
        socketChannel.configureBlocking(true);

        // 这里选择一个比较大一点的文件，否则看不出来效果
        String fileName = "/Users/xiaomai/software/工具/CleanMyMac 3.5.1.dmg";

        FileChannel fileChannel = new FileInputStream(fileName).getChannel();

        long startTime = System.currentTimeMillis();

        // 一行代码解决传输
        // 这行代码是用的零拷贝的方式来进行的文件传输，交给操作系统直接将文件系统缓存传输给了目标Channel
        // 而没有拷贝进用户空间的过程
        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        System.out.println("发送总字节数：" + transferCount + ", 耗时：" + (System.currentTimeMillis() - startTime));

        fileChannel.close();
    }
}
