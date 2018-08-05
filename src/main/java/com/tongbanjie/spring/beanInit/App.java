package com.tongbanjie.spring.beanInit;

/**
 * Hello world!
 */
public class App {

    static {
        System.out.println("com.tongbanjie.App类正在被加载");
    }

    public App() {
        System.out.println("com.tongbanjie.App构造器正在被调用");
    }

    public void init(){

        System.out.println("com.tongbanjie.App init方法正在被调用");
    }

    private String appName;

    private Integer version;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
