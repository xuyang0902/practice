###Dubbo 阿里巴巴开源的RPC框架，个人认为应该是国内互联网圈子用的最多的一个rpc框架了。值得深入学习一下

##### 如何学习dubbo？ 官网地址:http://dubbo.apache.org/en-us/docs/user/quick-start.html

     step1：了解基本概念，设计原理
     step2：dubbo helloworld 学习一下
     step3：兄弟，debug看源码吧，想啃下这座大山，就必须debug看源码
     step4：总结 学习完，记得简单总结，否则很容易就忘了!!!
     
#### Go  dubbo的设计 http://dubbo.apache.org/en-us/docs/dev/design.html 

![dubbo核心主成的关系]( /doc/dubbo/dubbo-relation.jpg)
      
      这张图基本就是dubbo总体设计的一个思想
     
     
![dubbo分层设计]( /doc/dubbo/dubbo分层设计.jpg)
     
      看源码前看一遍这个图，阅读完源码之后，再看一遍这个图片，你可能会有更多的收获哦
     

#### 阅读源码前，你应该知道的事情。
     
     了解以下概念，可以帮助你更轻松的学习dubbo
     0、简单了解下zk的概念，部署，简单api调用。【入门即可】
     1、先了解netty的使用 自学。【入门即可】
     2、了解java的SPI，以及dubbo自己定义的一套SPI  http://dubbo.apache.org/en-us/docs/dev/SPI.html【must】
     3、官网先了解一下dubbo代码的设计分层【need】
     4、spring基础不好的话，没必要看xml配置源码，直接api进入源码，会让你感觉更轻松一点【基础好 无需care】


#### 一张图说明zk在dubbo中做了什么
![zk存储了什么信息？]( /doc/dubbo/zookeeper在dubbo中到底做了什么.png)
     
     连接到zk上我们清楚的可以看到，zk存储了 提供者，消费者，路由，配置信息。任何节点的变更都会通知到对应的组件。


#### 关注核心类 

      ServiceConfig#export()  服务发布
      ReferenceConfig#get()  服务订阅