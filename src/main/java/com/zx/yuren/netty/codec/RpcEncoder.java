package com.zx.yuren.netty.codec;

import com.zx.yuren.netty.model.RpcCmd;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码器
 *
 * @author xu.qiang
 * @date 17/8/12
 */
public class RpcEncoder extends MessageToByteEncoder<RpcCmd> {

    @Override
    public void encode(ChannelHandlerContext ctx, RpcCmd in, ByteBuf out) throws Exception {

        out.writeBytes(in.encode());
    }
}
