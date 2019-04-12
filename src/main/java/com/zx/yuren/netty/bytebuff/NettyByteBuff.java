package com.zx.yuren.netty.bytebuff;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledByteBufAllocator;

/**
 * @author xu.qiang
 * @date 18/11/26
 */
public class NettyByteBuff {

    public static void main(String[] args) {

        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeBytes("hello".getBytes());


        ByteBuf byteBuf2 = Unpooled.buffer();
        byteBuf2.writeBytes("hello2".toString().getBytes());

        //组合buffer 仅仅只是组合  不会copy字节数组
        CompositeByteBuf byteBufs = Unpooled.compositeBuffer();
        byteBufs.addComponent(byteBuf);
        byteBufs.addComponent(byteBuf2);
        for (ByteBuf buf : byteBufs) {
            System.out.println(new String(buf.nioBuffer().array()));
        }


        UnpooledByteBufAllocator byteBufAllocator = new UnpooledByteBufAllocator(true);


        Unpooled.buffer(1024);
    }
}
