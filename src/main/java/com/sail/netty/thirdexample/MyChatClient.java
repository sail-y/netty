package com.sail.netty.thirdexample;

import com.sail.netty.secondexample.MyClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author yangfan
 * @date 2017/05/22
 */
public class MyChatClient {
    public static void main(String[] args) throws Exception {
        // 客户端只需要一个EventLoopGroup
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new MyChatClientInitializer());

            Channel channel = bootstrap.connect("localhost", 8899).sync().channel();
            // channel.closeFuture().sync();

            // 写一个死循环不断的去读取用户在控制台的输入

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            for (;;) {
                channel.writeAndFlush(br.readLine() + "\r\n");
            }




        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
