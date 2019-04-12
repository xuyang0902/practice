package com.zx.yuren.netty.codec;

import com.alibaba.dubbo.common.json.JSON;
import com.zx.yuren.netty.model.RpcCmd;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 解码器
 *
 * @author xu.qiang
 * @date 17/8/12
 */
public class RpcDecoder extends ByteToMessageDecoder {

    @Override
    public final void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        /**
         * 解码需要解决半包的问题，如果拿不到数据 就不读数据，等缓冲区满了在读
         */


        if (in.readableBytes() < 4) {
            return;
        }

        in.markReaderIndex();
        int dataLength = in.readInt();

        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return;
        }

        byte[] data = new byte[dataLength];
        in.readBytes(data);

        out.add(JSON.parse(new String(data), RpcCmd.class));
    }

}
