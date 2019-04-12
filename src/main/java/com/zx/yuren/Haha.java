package com.zx.yuren;

/**
 * @author xu.qiang
 * @date 19/3/27
 */
public class Haha {


    public static void main(String[] args) {
        hehe();
    }

    static Haha haha = new Haha();

    static{
        System.out.println("1");
    }

    {
        System.out.println(2);
    }

    Haha(){
        System.out.println("3");
        System.out.println("a:"+a+"  b:"+b);
    }

    public static void hehe(){
        System.out.println("4");
    }

    int a = 110;
    static  int b = 112;

}
