
Spring 核心
   IOC:控制反转，依赖注入
   Aop:面向切面编程
----------------------------------
AbstractApplicationContext#refresh()：核心的抽象实现类。简单实现了配置中心接口，使用模板设计模式，需要具体子类来实现抽象方法。（核心的spring加载流程类）




核心的关键点

1、创建beanFactory，load xml配置文件，注册bean【core，不同的schema不同的解析器】
2、可以手动调整注册表  实现 BeanDefinitionRegistryPostProcessor 
3、注册beanPostProcessor 排序
4、初始化不需要懒加载的bean 【core 代理，拦截都是在这层做的】
   getBean()