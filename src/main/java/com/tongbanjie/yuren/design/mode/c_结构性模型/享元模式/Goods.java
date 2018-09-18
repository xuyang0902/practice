package com.tongbanjie.yuren.design.mode.c_结构性模型.享元模式;

public class Goods implements IGoods{
    private String name;//名称
    private String version;//版本
    Goods(String name){
        this.name=name;
    }
    @Override
    public void showGoodsPrice(String version) {
        if(version.equals("32G")){
            System.out.println("价格为5199元");
        }else if(version.equals("128G")){
            System.out.println("价格为5999元");
        }
    }
}