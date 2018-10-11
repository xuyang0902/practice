package com.tongbanjie.yuren;

import java.util.Comparator;

/**
 * @author xu.qiang
 * @date 18/9/10
 */
public class HelloWord {

    public static void main(String[] args) {

        System.out.println("要的 = 有的 + 舍得");
        System.out.println("每天进步一点点");


        System.out.println(1 << 24);

        /*
         *   厂家申请编号|厂家自定义编号
         *   8位:8位:8位|8位:8位:8位
         *
         *
         *  一个厂商可以生成：16777216个网络设备
         *  00000 0000 ： 0000 0000：0000 0000
         */


        Integer a = 1;
        Integer b = 1;
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(a.equals(b));

        String aa = "11";
        String bb = "22";
        System.out.println(aa.hashCode());
        System.out.println(bb.hashCode());
        System.out.println(aa.equals(bb));

    }


}
