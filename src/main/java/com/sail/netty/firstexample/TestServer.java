package com.sail.netty.firstexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author yangfan
 * @date 2017/05/20
 */
public class TestServer {
    public static void main(String[] args) throws Exception {


        // 分发任务
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 实际处理的
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 启动服务
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }



}
