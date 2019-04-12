package com.zx.yuren.netty.bytebuff;

import java.nio.ByteBuffer;

/**
 * @author xu.qiang
 * @date 18/11/26
 */
public class JdkByteBuffer {

    public static void main(String[] args) {

        /**
         * 1M = 1024K = 1024*1024个字节
         *
         * 1M=1024k=1048576字节
         * 算法是：
         * 8bit(位)=1Byte(字节)
         * 1024Byte(字节)=1KB
         * 1024KB=1MB
         * 1024MB=1GB
         * 1024GB=1TB
         * 一个汉字要占用2个字节
         *
         * visualVm打开看看
         * mac活动监视器打开看看
         */

        System.out.println(">>>>>>");

        ByteBuffer heap = ByteBuffer.allocate(1024 * 1024 * 50);//堆内存申请字节数组
        ByteBuffer Direct = ByteBuffer.allocateDirect(1024 * 1024 * 50);//对外内存申请内存 不会影响jvm内存

        /**
         *  private int mark = -1;//标记位置
         *  private int position = 0;//读写的下一个位置
         *  private int limit;//写的时候表示最多写多少，读的时候表示最多读多少
         *  private int capacity;//字节数组容量大小
         */

        ByteBuffer wrap = ByteBuffer.wrap("haha".getBytes());
        System.out.println(new String(wrap.array()));
        System.out.println(printByteBufferInfo(wrap));


        /**
         * 读写还需要flip下，api没那么好用。需要注意上面的四个属性
         */

        ByteBuffer testBuffer = ByteBuffer.allocate(1024);
        testBuffer.putChar('a');
        System.out.println(printByteBufferInfo(testBuffer));

        testBuffer.mark();//记录下position
        testBuffer.putChar('b');
        System.out.println(printByteBufferInfo(testBuffer));

        //恢复到mark点
        testBuffer.reset();
        System.out.println(printByteBufferInfo(testBuffer));

        //反转  读a
        testBuffer.flip();
        System.out.println((char)testBuffer.getChar());

    }


    private static String printByteBufferInfo(ByteBuffer wrap) {
        StringBuffer sb = new StringBuffer();
        sb.append(" [pos=");
        sb.append(wrap.position());
        sb.append(" lim=");
        sb.append(wrap.limit());
        sb.append(" cap=");
        sb.append(wrap.capacity());
        sb.append("]");
        return sb.toString();
    }
}
