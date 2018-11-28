package com.zx.yuren.design.mode.c_结构性模型.适配器模式.类适配;

import com.zx.yuren.design.mode.c_结构性模型.适配器模式.Voltage220;
import com.zx.yuren.design.mode.c_结构性模型.适配器模式.Voltage5;

/**
 * 介绍：Adapter类：完成220V-5V的转变
 * 通过继承src类，实现 dst 类接口，完成src->dst的适配。
 * @author xu.qiang
 * @date 18/9/10
 */

public class VoltageAdapter extends Voltage220 implements Voltage5 {
    @Override
    public int output5V() {
        int src = output220V();
        System.out.println("适配器工作开始适配电压");
        int dst = src / 44;
        System.out.println("适配完成后输出电压：" + dst);
        return dst;
    }
}