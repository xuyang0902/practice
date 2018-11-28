package com.zx.yuren.jdk.socket.rpc;

public class RpcConsumer {

    public static void main(String[] args) {
        HelloService service = RpcFramework.refer(HelloService.class, "127.0.0.1", 1234);
        String result = service.hello("World");
        String hi = service.hi("小明");
        System.out.println("client: " + result);
        System.out.println("client: " + hi);
    }
}