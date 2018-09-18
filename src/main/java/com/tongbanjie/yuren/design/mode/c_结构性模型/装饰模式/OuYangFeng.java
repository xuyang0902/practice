package com.tongbanjie.yuren.design.mode.c_结构性模型.装饰模式;

public class OuYangFeng extends Master {
    public OuYangFeng(Swordsman mSwordsman) {
        super(mSwordsman);
    }
    public void teachAttackMagic(){
        System.out.println("欧阳锋教授蛤蟆功");
        System.out.println("杨过使用蛤蟆功");
    }
    @Override
    public void attackMagic() {
        super.attackMagic();
        teachAttackMagic();
    }
}