//package com.zx.yuren.netty.version3;
//
//import org.jboss.netty.channel.*;
//
//public class HelloHandler extends SimpleChannelHandler {
//
//    /**
//     * 接收消息
//     */
//    @Override
//    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
//        System.out.println("messageReceived" +e);
//        System.out.println("客户端发来消息:" + e.getMessage());
//        //回写数据
//        Channel channel = e.getChannel();
//        channel.write("hi");
//        super.messageReceived(ctx, e);
//
//
//    }
//    /**
//     * 关闭链接
//     */
//    @Override
//    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
//        System.out.println("channelClosed" +e);
//        super.channelClosed(ctx, e);
//    }
//
//    /**
//     * 建立连接
//     */
//    @Override
//    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
//        System.out.println("channelConnected" +e);
//        super.channelConnected(ctx, e);
//    }
//    /**
//     * 关闭连接 清除缓存
//     */
//    @Override
//    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
//        System.out.println("channelDisconnected" +e);
//        super.channelDisconnected(ctx, e);
//    }
//    /**
//     * 捕获异常
//     */
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
//        System.out.println("exceptionCaught" +e);
//        super.exceptionCaught(ctx, e);
//    }
//
//
//}