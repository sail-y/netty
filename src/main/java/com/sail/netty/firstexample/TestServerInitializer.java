package com.sail.netty.firstexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;


/**
 * @author yangfan
 * @date 2017/05/20
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 请求相应编码
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        // 处理请求
        pipeline.addLast("testHttpServerHandler", new TestHttpServerHandler());

    }
}
