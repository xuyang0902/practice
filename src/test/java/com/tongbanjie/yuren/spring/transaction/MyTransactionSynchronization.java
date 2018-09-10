package com.tongbanjie.yuren.spring.transaction;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;

/**
 * @author xu.qiang
 * @date 18/8/5
 */
public class MyTransactionSynchronization extends TransactionSynchronizationAdapter {


    @Override
    public void beforeCompletion() {
        super.beforeCompletion();

        System.out.println("---->>>> MyTransactionSynchronization   beforeCompletion");

    }

    @Override
    public void afterCompletion(final int status) {
        super.afterCompletion(status);

        System.out.println("---->>>> MyTransactionSynchronization   afterCompletion");

    }

    /**
     * @return
     * @see org.springframework.transaction.support.TransactionSynchronizationAdapter#getOrder()
     */
    @Override
    public int getOrder() {
        return DataSourceUtils.CONNECTION_SYNCHRONIZATION_ORDER + 1;
    }

}
