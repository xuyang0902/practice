package com.zx.yuren.netty.model;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * @author xu.qiang
 * @date 19/4/11
 */
public class RpcCmd implements Serializable {

    private static final long serialVersionUID = -7660422240931744219L;
    private long uniqueId;

    private String message;

    public long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    //编码
    public ByteBuffer encode() throws IOException {

        // 1> header
        String data = JSON.json(this);
        int length = data.getBytes().length + 4;
        ByteBuffer result = ByteBuffer.allocateDirect(length);
        result.putInt(data.getBytes().length);//长度
        result.put(data.getBytes());//数据包

        result.flip();

        return result;
    }


    //解码
    public static RpcCmd decode(final byte[] array) throws UnsupportedEncodingException, ParseException {
        try{

            ByteBuffer byteBuffer = ByteBuffer.wrap(array);
            int length = byteBuffer.getInt();
            byte[] headerData = new byte[length];
            byteBuffer.get(headerData);
            return JSON.parse(new String(headerData, "UTF-8"),RpcCmd.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public String toString() {
        return "RpcCmd{" +
                "uniqueId=" + uniqueId +
                ", message='" + message + '\'' +
                '}';
    }
}
