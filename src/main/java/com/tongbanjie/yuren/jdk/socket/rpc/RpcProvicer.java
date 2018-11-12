package com.tongbanjie.yuren.jdk.socket.rpc;

import java.io.IOException;

public class RpcProvicer {

    public static void main(String[] args) throws IOException {
        HelloService service = new HelloServiceImpl();
        RpcFramework.export(service, 1234);
    }
}