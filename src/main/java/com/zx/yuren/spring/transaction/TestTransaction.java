package com.zx.yuren.spring.transaction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 测试事务同步器
 *
 * @author xu.qiang
 * @date 18/8/5
 */
public class TestTransaction {


    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-transaction.xml");


        TransactionTemplate template = applicationContext.getBean("transactionTemplate", TransactionTemplate.class);


        template.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                /**
                 * 注册了一个事务同步器
                 *
                 * 这个同步器在事务执行完了之后会处理，无论rollback | commit都会执行
                 */
                TransactionSynchronizationManager.registerSynchronization(new MyTransactionSynchronization());

                System.out.println("--->执行DB");

//                int index = 1/0;


                return null;
            }
        });

    }


}
