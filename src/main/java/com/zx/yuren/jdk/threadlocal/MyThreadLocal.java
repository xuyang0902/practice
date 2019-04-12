package com.zx.yuren.jdk.threadlocal;

/**
 * @author xu.qiang
 * @date 19/4/10
 */
public class MyThreadLocal {

    private static final ThreadLocal<String> SESSION = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "DEFAULT";
        }
    };

    public static void main(String[] args) {

        System.out.println(SESSION.get());
        SESSION.set("1");
        System.out.println(SESSION.get());
        SESSION.remove();
        System.out.println(SESSION.get());

    }


}
