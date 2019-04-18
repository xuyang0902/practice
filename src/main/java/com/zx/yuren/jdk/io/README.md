
###### 零copy https://www.cnblogs.com/f-ck-need-u/p/7615914.html


#####解释 
    从上到下 ：用户空间  内核  硬件设备

    零复制的概念是避免将数据在内核空间和用户空间进行拷贝。主要目的是减少不必要的拷贝，避免让CPU做大量的数据拷贝任务。
    
    
    
###### Netty 的 Zero-copy 体现在如下几个个方面:
    
    1.Netty 提供了 CompositeByteBuf 类, 它可以将多个 ByteBuf 合并为一个逻辑上的 ByteBuf, 避免了各个 ByteBuf 之间的拷贝.
    2.通过 wrap 操作, 我们可以将 byte[] 数组、ByteBuf、ByteBuffer等包装成一个 Netty ByteBuf 对象, 进而避免了拷贝操作.
    3.ByteBuf 支持 slice 操作, 因此可以将 ByteBuf 分解为多个共享同一个存储区域的 ByteBuf, 避免了内存的拷贝.
    4.通过 FileRegion 包装的FileChannel.tranferTo 实现文件传输, 可以直接将文件缓冲区的数据发送到目标 Channel, 避免了传统通过循环write方式导致的内存拷贝问题.