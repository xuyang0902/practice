package com.zx.yuren.design.mode.c_结构性模型.适配器模式.类适配;

import com.zx.yuren.design.mode.c_结构性模型.适配器模式.Mobile;

/**
 * 测试使用
 *
 * @author xu.qiang
 * @date 18/9/10
 */
public class TestUse {

    public static void main(String[] args) {

        System.out.println("===============类适配器==============");
        Mobile mobile = new Mobile();
        mobile.charging(new VoltageAdapter());

    }

    
}
