package com.sail.netty.bytebuf;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * @author yangfan
 * @date 2017/11/29
 */
public class ByteBufTest1 {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("杨hello world", Charset.forName("utf-8"));

        // 表示是堆上的缓冲，是用字节数组存放的
        if (byteBuf.hasArray()) {
            byte[] content = byteBuf.array();

            System.out.println(new String(content, Charset.forName("utf-8")));

            System.out.println(byteBuf);

            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.capacity());

            int length = byteBuf.readableBytes();

            System.out.println(length);

            for (int i = 0; i < byteBuf.readableBytes(); i++) {
                System.out.println((char)byteBuf.getByte(i));
            }

            System.out.println(byteBuf.getCharSequence(0,4, Charset.forName("utf-8")));
        }
    }
}
