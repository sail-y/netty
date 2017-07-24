package com.sail.nettyinaction.ch2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author yangfan
 * @date 2017/07/13
 */
public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: " + EchoServer.class.getSimpleName() + " <port>");
        }

        int port = Integer.parseInt(args[0]);
        new EchoServer(port).start();
    }

    public void start() throws Exception {
        final EchoServerHandler serverHandler = new EchoServerHandler();

        // 创建 Event- LoopGroup
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            //  创建 Server-Bootstrap
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    //指定所使用的 NIO 传输 Channel
                    .channel(NioServerSocketChannel.class)
                    // 使用指定的 端口设置套 接字地址
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //  添加一个EchoServer-Handler到子Channel的ChannelPipeline
                            ch.pipeline().addLast(serverHandler);
                            //  EchoServerHandler被标注为@Shareable，所以我们可以总是使用同样的实例
                        }
                    });


            // 异步地绑定服务器; 调用 sync()方法阻塞等待直到绑定完成
            ChannelFuture f = b.bind().sync();

            // 获取Channel的CloseFuture，并且阻塞当前线程直到它完成
            f.channel().closeFuture().sync();
        } finally {
            //  关闭EventLoopGroup，释放所有的资源
            group.shutdownGracefully().sync();
        }
    }
}
