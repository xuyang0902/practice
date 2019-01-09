package com.zx.yuren;

/**
 * @author xu.qiang
 * @date 18/9/10
 */
public class HelloWord {

    public static void main(String[] args) {

        System.out.println("要的 = 有的 + 舍得");
        System.out.println("每天进步一点点");


        /**
         * 网卡mac地址
         *
         * 前24位叫做组织唯一标志符（Organizationally Unique Identifier，即OUI），是由IEEE的注册管理机构给不同厂家分配的代码，区分了不同的厂家。
         *
         * 后24位是由厂家自己分配的，称为扩展标识符。同一个厂家生产的网卡中MAC地址后24位是不同的。
         */
        System.out.println(1 << 24);

        /*
         *   厂家申请编号|厂家自定义编号
         *   8位:8位:8位|8位:8位:8位
         *
         *  一个厂商可以生成：16777216个网络设备
         *  00000 0000 ： 0000 0000：0000 0000
         */


        try{

            int index = 1/0;
        }finally {

            System.out.println(">>>>>");
        }
    }

}
