package com.zx.yuren.design.mode.c_结构性模型.代理模式;

/**
 * 测试使用
 *
 * @author xu.qiang
 * @date 18/9/10
 */
public class TestUse {

    public static void main(String[] args) {

    }


    /*
     * 动态代理
     *  @see com.zx.yuren.jdk.reflection.java.JDKInvocationHandler
     * 静态代理
     *  代理对象拥有真实对象的实例，最终调用真实对象。
     *
     * 代理模式的类型主要有以下几点：
     远程代理：为一个对象在不同的地址空间提供局部代表，这样系统可以将Server部分的事项隐藏。
     虚拟代理：使用一个代理对象表示一个十分耗资源的对象并在真正需要时才创建。
     安全代理：用来控制真实对象访问时的权限。
     智能指引：当调用真实的对象时，代理处理另外一些事，比如计算真实对象的引用计数，当该对象没有引用时，可以自动释放它；或者访问一个实际对象时，检查是否已经能够锁定它，以确保其他对象不能改变它。

     */

}
