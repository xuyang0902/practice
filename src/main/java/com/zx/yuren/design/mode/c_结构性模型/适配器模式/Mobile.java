package com.zx.yuren.design.mode.c_结构性模型.适配器模式;

/**
 * 介绍：Client类：手机 .需要5V电压
 * @author xu.qiang
 * @date 18/9/10
 */

public class Mobile {
    /**
     * 充电方法
     *
     * @param voltage5
     */
    public void charging(Voltage5 voltage5) {
        if (voltage5.output5V() == 5) {
            System.out.println("电压刚刚好5V，开始充电");
        } else if (voltage5.output5V() > 5) {
            System.out.println("电压超过5V，都闪开 我要变成note7了");
        }
    }
}