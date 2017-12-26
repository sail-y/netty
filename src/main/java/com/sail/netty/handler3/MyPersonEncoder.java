package com.sail.netty.handler3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author yangfan
 * @date 2017/12/25
 */
public class MyPersonEncoder extends MessageToByteEncoder<PersonProtocol> {

    @Override
    protected void encode(ChannelHandlerContext ctx, PersonProtocol msg, ByteBuf out) {
        System.out.println("MyPersonEncoder encode invoked!");

        out.writeInt(msg.getLength());
        out.writeBytes(msg.getContent());
    }
}
