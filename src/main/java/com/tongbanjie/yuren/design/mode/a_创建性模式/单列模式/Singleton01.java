package com.tongbanjie.yuren.design.mode.a_创建性模式.单列模式;

/**
 * 饿汉式
 * @author xu.qiang
 * @date 2018/9/10
 */
public class Singleton01 {

    private final static Singleton01 INSTANCE = new Singleton01();

    private Singleton01() {
    }

    public static Singleton01 getInstance() {
        return INSTANCE;
    }
}
