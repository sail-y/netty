package com.sail.nio;

import java.nio.ByteBuffer;

/**
 * @author yangfan
 * @date 2017/08/15
 */
public class NioTest5 {
    public static void main(String[] args) {
        //
        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(15);
        buffer.putLong(50000000L);
        buffer.putDouble(14.123123);
        buffer.putChar('好');
        buffer.putShort((short) 2);
        buffer.putChar('的');

        buffer.flip();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
        System.out.println(buffer.getChar());

    }
}
