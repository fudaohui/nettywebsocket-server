package com.fdh.websocket.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component
public class WebSocketServer {

    public void start() {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup wokerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, wokerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new WebSocketChannelInitializer());

            try {
                ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(8899)).sync();
                channelFuture.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            bossGroup.shutdownGracefully();
            wokerGroup.shutdownGracefully();
        }

    }
}
