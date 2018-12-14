## redis
  
[不想搭建本地redis环境的话，直接在这里学习命令吧](http://try.redis.io/)

[从零单排学Redis【青铜】](https://mp.weixin.qq.com/s/XkEK8_OM0OdjpIR2-FkeIQ)  
[从零单排学Redis【白银】](https://mp.weixin.qq.com/s/lxXxsRL4GK2bSFkAqeLurA)  
[从零单排学Redis【黄金】](https://mp.weixin.qq.com/s/3ZNcMkOvVANdsZvrWxzMwg)  
[从零单排学Redis【铂金-】](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484430&idx=1&sn=be69ef08e58dc7559d054221732ee8ee&chksm=ebd7450fdca0cc19a4a8cd788161bfdebc0d51abe9989debadd61ba29e3cce6961ebe6093d5b&token=752118079&lang=zh_CN&scene=21#wechat_redirect)  
[从零单排学Redis【铂金二】](https://mp.weixin.qq.com/s/JfARRZW9xxiPqfcPM4iAYQ)  




        redis3.0支持集群 
        安装
        https://www.cnblogs.com/yuanermen/p/5717885.html
        
        
        重新分配槽点
        redis-trib.rb reshard 192.168.2.100:6379   
        
        加msater  
        redis-trib.rb add-node 192.168.2.202:6380（新）192.168.1.160:7000（集群任一几点）
        加slave
        redis-trib.rb add-node --slave 192.168.2.202:6380（新） 192.168.2.202:6380（master）
        查看集群信息
        cluster info
        cluster nodes
        删除节点 删除前需要吧槽点移走
        redis-trib.rb del-node 192.168.2.202:6380 e4dc23dc67418bf66c6c63655110612cb9516aff
        // del-node  host:port  node-id
        查看集群节点信息：
        # redis-trib.rb check 192.168.2.100:6379 (确认slave节点已经删除)
