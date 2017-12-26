package com.sail.netty.handler3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author yangfan
 * @date 2017/12/25
 */
public class MyClientHandler extends SimpleChannelInboundHandler<PersonProtocol> {

    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            String messageToBeSent = "sent from client ";
            byte[] content = messageToBeSent.getBytes(CharsetUtil.UTF_8);
            int length = messageToBeSent.getBytes("utf-8").length;

            PersonProtocol personProtocol = new PersonProtocol();
            personProtocol.setLength(length);
            personProtocol.setContent(content);

            ctx.writeAndFlush(personProtocol);
        }
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) {
        int length = msg.getLength();
        byte[] content = msg.getContent();

        System.out.println("客户端接收到的数据：");
        System.out.println("长度；" + length);
        System.out.println("内容：" + new String(content, CharsetUtil.UTF_8));
        System.out.println("客户端接受到的消息数量：" + ++count);

    }
}
