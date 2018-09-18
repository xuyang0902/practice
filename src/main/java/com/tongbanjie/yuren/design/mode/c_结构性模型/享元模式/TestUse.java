package com.tongbanjie.yuren.design.mode.c_结构性模型.享元模式;

/**
 * 测试使用
 *
 * @author xu.qiang
 * @date 18/9/10
 */
public class TestUse {

    public static void main(String[] args) {

        Goods goods1=GoodsFactory.getGoods("iphone7");
        goods1.showGoodsPrice("32G");
        Goods goods2=GoodsFactory.getGoods("iphone7");
        goods2.showGoodsPrice("32G");
        Goods goods3=GoodsFactory.getGoods("iphone7");
        goods3.showGoodsPrice("128G");

    }


    /**
     * 享元模式是结构型设计模式的一种，是池技术的重要实现方式，它可以减少应用程序创建的对象，
     * 降低程序内存的占用，提高程序的性能
     *
     * 享元模式的使用场景
     *   系统中存在大量的相似对象。
     *   需要缓冲池的场景。
     *   细粒度的对象都具备较接近的外部状态，而且内部状态与环境无关，也就是说对象没有特定身份。
     */

}
