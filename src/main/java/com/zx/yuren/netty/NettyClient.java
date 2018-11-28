package com.zx.yuren.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.concurrent.CountDownLatch;

/**
 * @author xu.qiang
 * @date 18/11/13
 */
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup worker = new NioEventLoopGroup(4);

        Bootstrap handler = bootstrap.group(worker)
                .option(ChannelOption.SO_KEEPALIVE, false)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_SNDBUF, 65536)
                .option(ChannelOption.SO_RCVBUF, 65536)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {

                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("decodeRpc", new StringDecoder(CharsetUtil.UTF_8));//解码器
                        pipeline.addLast("encodeRpc", new StringEncoder(CharsetUtil.UTF_8));//编码器
                        pipeline.addLast("handler", new NettyClientHandler());
                    }
                });
        final CountDownLatch latch = new CountDownLatch(1);


        ChannelFuture connect = bootstrap.connect("127.0.0.1", 9999);

        connect.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(final ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    latch.countDown();
                } else {
                    //通道完成连接失败  抛异常出去
                    throw new RuntimeException("connect server channel  failed ");
                }
            }
        });
        latch.await();

        int loop = 100000;
        while (loop-- > 0) {
            connect.channel().writeAndFlush("hello-server");

            Thread.sleep(1000);
        }

    }

    /**
     * rpc 客户端处理器
     */
    static class NettyClientHandler extends SimpleChannelInboundHandler<String> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

            System.out.println("threadName" + Thread.currentThread().getName() + "     客户端接收到消息-->>>" + msg);


        }
    }
}
