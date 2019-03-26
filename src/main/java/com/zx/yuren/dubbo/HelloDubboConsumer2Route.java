package com.zx.yuren.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.extension.SpringExtensionFactory;
import com.zx.yuren.dubbo.api.HelloDubbo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 使用编程式代码变成，读者容易断点进去看。
 *
 *
 * 客户端
 *
 * @author xu.qiang
 * @date 18/11/28
 */
public class HelloDubboConsumer2Route {


    public static void main(String[] args) {


        //dubbo-spring的spi  需要拿到com.zx.yuren.dubbo.route.MyRoute
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/dubbo-route.xml");
        SpringExtensionFactory.addApplicationContext(applicationContext);

        // Application Info
        ApplicationConfig application = new ApplicationConfig();
        application.setName("app_02");

        // Registry Info
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("192.168.1.121:2181");
        registry.setProtocol("zookeeper");

        // NOTES: ReferenceConfig holds the connections to registry and providers, please cache it for performance.

        // Refer remote service
        ReferenceConfig<HelloDubbo> reference = new ReferenceConfig<HelloDubbo>(); // In case of memory leak, please cache.
        reference.setApplication(application);
        reference.setRegistry(registry);
        reference.setInterface(HelloDubbo.class);
        reference.setVersion("1.0.0");
        reference.setLoadbalance("my");

        // Use xxxService just like a local bean
        HelloDubbo xxxService = reference.get(); // NOTES: Please cache this proxy instance.


        String 不错哦 = xxxService.sayHello("不错哦");
        System.out.println(不错哦);


        while(true){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
