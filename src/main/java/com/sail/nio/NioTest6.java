package com.sail.nio;

import java.nio.ByteBuffer;

/**
 * Buffer的分片--slice()
 * Slice Buffer与原有Buffer共享相同的底层数组
 * @author yangfan
 * @date 2017/08/15
 */
public class NioTest6 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte)i);
        }

        buffer.position(2);
        buffer.limit(6);

        // 会返回一个包含[2,6)原始数据的Buffer，任意修改2个Buffer的数据，对应都会发生变化
        ByteBuffer sliceBuffer = buffer.slice();

        for (int i = 0; i < sliceBuffer.capacity(); i++) {
            byte b = sliceBuffer.get(i);
            b *= 2;
            sliceBuffer.put(i , b);
        }

        buffer.clear();

        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }

        // 输出下面结果
//        0
//        1
//        4
//        6
//        8
//        10
//        6
//        7
//        8
//        9
    }
}
