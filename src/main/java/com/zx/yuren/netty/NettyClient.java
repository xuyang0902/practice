package com.zx.yuren.netty;

import com.zx.yuren.netty.codec.RpcDecoder;
import com.zx.yuren.netty.codec.RpcEncoder;
import com.zx.yuren.netty.model.RpcCmd;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.CountDownLatch;

/**
 * @author xu.qiang
 * @date 18/11/13
 */
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup worker = new NioEventLoopGroup(4, new MyThreadFactory("Client-"));

        ChannelInitializer<SocketChannel> channelInitializer = new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {

                ChannelPipeline pipeline = ch.pipeline();
//                pipeline.addLast("decodeRpc", new StringDecoder(CharsetUtil.UTF_8));//解码器
//                pipeline.addLast("encodeRpc", new StringEncoder(CharsetUtil.UTF_8));//编码器

                pipeline.addLast("encode", new RpcEncoder());
                pipeline.addLast("decode", new RpcDecoder());
                pipeline.addLast("handler", new NettyClientHandler());
            }
        };


        Bootstrap handler = bootstrap.group(worker)
                .option(ChannelOption.SO_KEEPALIVE, false)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_SNDBUF, 65536)
                .option(ChannelOption.SO_RCVBUF, 65536)
                .channel(NioSocketChannel.class)
                .handler(channelInitializer);

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


        int loop = 1;
        while(true){


            Channel channel = connect.channel();
            RpcCmd cmd = new RpcCmd();
            cmd.setMessage("hello-server--"+loop);

            channel.writeAndFlush(cmd);

            loop--;

            if(loop == 0){
                break;
            }

        }

        while(true){

            Thread.sleep(1000);
        }

    }

    /**
     * rpc 客户端处理器
     */
    static class NettyClientHandler extends SimpleChannelInboundHandler<RpcCmd> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, RpcCmd msg) throws Exception {

            System.out.println("threadName" + Thread.currentThread().getName() + "     客户端接收到消息-->>>" + msg);


        }
    }
}
