package com.tongbanjie.yuren.netty;

import com.tongbanjie.yuren.jdk.socket.nio_0.NIOServer0;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;


/**
 * netty官方的源码分析 还是蛮不错的
 * https://netty.io/wiki/related-articles.html
 *
 * @author xu.qiang
 * @date 18/11/13
 * @see NIOServer0
 * @see com.tongbanjie.yuren.jdk.socket.nio.Start
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(4);

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        ServerBootstrap handler = serverBootstrap.group(bossGroup, workerGroup)
                .option(ChannelOption.SO_BACKLOG, 4096)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.SO_KEEPALIVE, false)
                .option(ChannelOption.SO_SNDBUF, 65536)
                .option(ChannelOption.SO_RCVBUF, 65536)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {

                        ChannelPipeline pipeline = ch.pipeline();

                        pipeline.addLast("decodeRpc", new StringDecoder(CharsetUtil.UTF_8));//解码器
                        pipeline.addLast("encodeRpc", new StringEncoder(CharsetUtil.UTF_8));//编码器
                        pipeline.addLast("handler", new NettyServerHandler());
                    }
                });

        handler.bind(new InetSocketAddress("127.0.0.1", 9999)).sync();

    }

    static class NettyServerHandler extends SimpleChannelInboundHandler<String> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {


            System.out.println("threadName" + Thread.currentThread().getName() + "   服务端接受到消息-->" + msg);
            ctx.channel().writeAndFlush("hello-client");

        }

    }
}
