package com.sail.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * 只读Buffer,我们可以随时将一个普通Buffer调用asReadOnlyBuffer方法返回一个只读Buffer
 * 但不能将一个只读Buffer转换为读写Buffer
 * @author yangfan
 * @date 2017/08/15
 */
public class NioTest7 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        //class java.nio.HeapByteBuffer
        System.out.println(buffer.getClass());

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

        //class java.nio.HeapByteBufferR
        System.out.println(readOnlyBuffer.getClass());

        readOnlyBuffer.position(0);
        // ReadOnlyBufferException
        readOnlyBuffer.put((byte) 2);

    }
}
