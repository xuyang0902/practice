package com.zx.yuren.jdk.socket;

import java.net.URL;

/**
 * @author xu.qiang
 * @date 18/11/12
 */
public class SocketMain {

    public static void main(String[] args) throws Exception {

        //创建一个URL的实例
        URL baidu = new URL("http://www.baidu.com");
        URL url = new URL(baidu, "/index.html?username=tom#test");//？表示参数，#表示锚点
        System.out.println(url.getProtocol());//获取协议
        System.out.println(url.getHost());//获取主机
        System.out.println(url.getPort());//如果没有指定端口号，根据协议不同使用默认端口。此时getPort()方法的返回值为 -1
        System.out.println(url.getPath());//获取文件路径
        System.out.println(url.getFile());//文件名，包括文件路径+参数
        System.out.println(url.getRef());//相对路径，就是锚点，即#号后面的内容
        System.out.println(url.getQuery());//查询字符串，即参数


        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        System.out.println(SocketMain.class.getResource(""));
        System.out.println(SocketMain.class.getResource("/"));

    }
}
