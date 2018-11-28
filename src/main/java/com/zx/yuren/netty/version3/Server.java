//package com.zx.yuren.netty.version3;
//
//import org.jboss.netty.bootstrap.ServerBootstrap;
//import org.jboss.netty.channel.ChannelPipeline;
//import org.jboss.netty.channel.ChannelPipelineFactory;
//import org.jboss.netty.channel.Channels;
//import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
//import org.jboss.netty.handler.codec.string.StringDecoder;
//import org.jboss.netty.handler.codec.string.StringEncoder;
//
//import java.net.InetSocketAddress;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//public class Server {
//    public static void main(String[] args) {
//        //设置Server客户端
//        ServerBootstrap bootstrap = new ServerBootstrap();
//
//        //设置线程池
//        ExecutorService boss = Executors.newFixedThreadPool(1);
//        ExecutorService worker = Executors.newFixedThreadPool(1);
//
//        //设置nioSocket工厂
//        bootstrap.setFactory(new NioServerSocketChannelFactory(boss, worker));
//
//        //设置管道
//        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
//            //在管道里添加拦截器
//            @Override
//            public ChannelPipeline getPipeline() throws Exception {
//                ChannelPipeline pipeline = Channels.pipeline();
//                pipeline.addLast("decoder", new StringDecoder());
//                pipeline.addLast("encoder", new StringEncoder());
//                pipeline.addLast("helloHandler", new HelloHandler());
//                return pipeline;
//            }
//        });
//        //监听10010端口
//        bootstrap.bind(new InetSocketAddress(10010));
//
//        System.out.println("服务端启动");
//
//    }
//}