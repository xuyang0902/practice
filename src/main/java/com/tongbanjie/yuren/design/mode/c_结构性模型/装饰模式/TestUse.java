package com.tongbanjie.yuren.design.mode.c_结构性模型.装饰模式;

/**
 * 测试使用
 *
 * @author xu.qiang
 * @date 18/9/10
 */
public class TestUse {

    public static void main(String[] args) {

        //创建杨过
        YangGuo mYangGuo=new YangGuo();
        //洪七公教授杨过打狗棒法，杨过会了打狗棒法
        HongQiGong mHongQiGong=new HongQiGong(mYangGuo);
        mHongQiGong.attackMagic();

        //欧阳锋教授杨过蛤蟆功，杨过学会了蛤蟆功
        OuYangFeng mOuYangFeng=new OuYangFeng(mYangGuo);
        mOuYangFeng.attackMagic();

    }


    /**
     * 装饰模式是结构型设计模式之一，不必改变类文件和使用继承的情况下，动态地扩展一个对象的功能，是继承的替代方案之一。
     * 它是通过创建一个包装对象，也就是装饰来包裹真实的对象。
     *
     *
     * 你会觉得代理模式和装饰模式有点像，都是持有了被代理或者被装饰对象的引用。
     * 它们两个最大的不同就是装饰模式对引用的对象增加了功能，
     * 而代理模式只是对引用对象进行了控制却没有对引用对象本身增加功能。
     *
     */

}
