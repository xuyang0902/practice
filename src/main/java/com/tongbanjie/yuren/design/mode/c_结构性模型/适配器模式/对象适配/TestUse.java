package com.tongbanjie.yuren.design.mode.c_结构性模型.适配器模式.对象适配;

import com.tongbanjie.yuren.design.mode.c_结构性模型.适配器模式.Mobile;
import com.tongbanjie.yuren.design.mode.c_结构性模型.适配器模式.Voltage220;
import com.tongbanjie.yuren.design.mode.c_结构性模型.适配器模式.类适配.VoltageAdapter;

/**
 * 测试使用
 *
 * @author xu.qiang
 * @date 18/9/10
 */
public class TestUse {

    public static void main(String[] args) {

        System.out.println("\n===============对象适配器==============");
        VoltageAdapter2 voltageAdapter2 = new VoltageAdapter2(new Voltage220());
        Mobile mobile2 = new Mobile();
        mobile2.charging(voltageAdapter2);

    }

    
}
