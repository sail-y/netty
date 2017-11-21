package com.sail.nettyinaction.ch5;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * @author yangfan
 * @date 2017/11/07
 */
public class SliceTest {
    public static void main(String[] args) {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);

        ByteBuf sliced = buf.slice(0, 15);

        System.out.println(sliced.toString(utf8));

        buf.setByte(0, (byte) 'J');

        assert buf.getByte(0) == sliced.getByte(0);


    }
}
