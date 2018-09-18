package com.tongbanjie.yuren.design.mode.c_结构性模型.适配器模式;

/**
 * 介绍：src类: 我们有的220V电压
 * @author xu.qiang
 * @date 18/9/10
 */

public class Voltage220 {
    public int output220V() {
        int src = 220;
        System.out.println("我是" + src + "V");
        return src;
    }
}