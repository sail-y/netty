package com.sail.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * 输出10个随机数，输出很简单，但是包含的逻辑却很丰富。
 *
 * @author yangfan
 * @date 2017/08/13
 */
public class NioTest1 {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);

        System.out.println("capacity: " + buffer.capacity());
        for (int i = 0; i < 5; i++) {
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }

        // 10
        System.out.println("before flip limit: " + buffer.limit());

        // 读写状态的翻转
        buffer.flip();

        // 5
        System.out.println("after flip limit: " + buffer.limit());

        System.out.println("enter while loop");

        while (buffer.hasRemaining()) {
            // 0,1,2,3,4
            System.out.println("position: " + buffer.position());
            // 5,5,5,5,5
            System.out.println("limit: " + buffer.limit());
            // 10,10,10,10,10
            System.out.println("capacity: " + buffer.capacity());

            System.out.println(buffer.get());
        }
    }
}
