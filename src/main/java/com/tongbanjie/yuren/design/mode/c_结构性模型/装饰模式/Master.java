package com.tongbanjie.yuren.design.mode.c_结构性模型.装饰模式;

public abstract class Master extends Swordsman{
    private Swordsman mSwordsman;

    public Master(Swordsman mSwordsman){
        this.mSwordsman=mSwordsman;
    }
    @Override
    public void attackMagic() {
        mSwordsman.attackMagic();
    }
}