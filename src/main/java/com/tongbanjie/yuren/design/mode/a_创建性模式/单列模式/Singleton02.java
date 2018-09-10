package com.tongbanjie.yuren.design.mode.a_创建性模式.单列模式;

/**
 *懒汉式
 * @author xu.qiang
 * @date 2018/9/10
 */
public class Singleton02 {

    private static volatile Singleton02 INSTANCE;

    private Singleton02() {
    }

    /**
     * 双重检查
     * @return
     */
    public static Singleton02 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton02.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton02();
                }
            }
        }
        return INSTANCE;
    }
}
