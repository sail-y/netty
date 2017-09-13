package com.sail.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author yangfan
 * @date 2017/09/12
 */
public class OldClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8899);

        // 这里选择一个比较大一点的文件，否则看不出来效果
        String fileName = "/Users/xiaomai/software/工具/CleanMyMac 3.5.1.dmg";

        InputStream inputStream = new FileInputStream(fileName);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        byte[] buffer = new byte[4096];
        long readCount;

        long total = 0;

        long startTime = System.currentTimeMillis();

        while ((readCount = inputStream.read(buffer)) >=0) {
            total += readCount;
            dataOutputStream.write(buffer);

        }

        System.out.println("发送总字节数：" + total + "，耗时：" + (System.currentTimeMillis() - startTime));

        inputStream.close();
        dataOutputStream.close();
    }
}
