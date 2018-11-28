package com.zx.yuren.design.mode.a_创建性模式.抽象工厂;

/**
 * 测试使用
 *
 * @author xu.qiang
 * @date 18/9/10
 */
public class TestUse {

    public static void main(String[] args) {

    }


    /**
     * 抽象工厂模式，其实相对于工厂方法模式来说，就多了一点东西，
     * 所谓多的一点东西就是将同一品牌的产品都交给同一个工厂去生产。
     * 解决工厂方法模式的单一性；否则，如果每一个产品都需要一个工厂去生产的话，会需要非常多的子工厂类，造成类泛滥；
     * 同一品牌的产品交给同一个工厂来生产的意思就是：比如所，现在生产的需要不仅仅是单一的Hair电视机，还要有Hair空调，Hair冰箱等，
     * 这些同一品牌的产品都交给HairFactory来生产，而这些属于同一品牌的产品有一个专业名词：一个产品族；
     */


}
