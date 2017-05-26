package com.sail.netty.forthexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author yangfan
 * @date 2017/05/22
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * IdleStateHandler可以检测连接的3种状态
     */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new IdleStateHandler(5, 7, 3, TimeUnit.SECONDS));
        pipeline.addLast(new MyServerHandler());

    }
}
