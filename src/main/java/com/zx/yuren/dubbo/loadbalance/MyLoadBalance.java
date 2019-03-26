/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zx.yuren.dubbo.loadbalance;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.cluster.loadbalance.AbstractLoadBalance;

import java.util.List;
import java.util.Random;

/**
 * 自定义负载均衡算法，根据接口参数来 路由到不同的提供者机器上，客户端根据参数做分发，满足需求
 *
 * 注意：dubbo默认当provider节点只有一台的时候 负载均衡算法不介入；
 *
 * @author qianlei
 * @author william.liangf
 */
public class MyLoadBalance extends AbstractLoadBalance {


    private final Random random = new Random();

    private SelectStrategy selectStrategy;

    @Override
    protected <T> Invoker<T> doSelect(List<Invoker<T>> invokers, URL url, Invocation invocation) {

        if(!"com.zx.yuren.dubbo.api.HelloDubbo".equals(url.getServiceInterface())){
            throw new RuntimeException("只有这个接口可以用啊,其他接口不能选择这个负载策略");
        }

        Object[] arguments = invocation.getArguments();

        for (Object argument : arguments) {

            System.out.println(argument.toString());
        }

        if(this.selectStrategy != null){
            this.selectStrategy.select();
        }


        return invokers.get(0);
    }


    public void setSelectStrategy(SelectStrategy selectStrategy) {
        this.selectStrategy = selectStrategy;
    }
}