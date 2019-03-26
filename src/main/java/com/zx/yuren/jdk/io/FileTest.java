package com.zx.yuren.jdk.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 文件测试
 * @author xu.qiang
 * @date 19/3/14
 */
public class FileTest {


    public static void main(String[] args) throws IOException {
        File file = new File("/usr/local/tmp/haha.txt");

        if(!file.exists()){
                file.createNewFile();
        }


        FileOutputStream os  = new FileOutputStream(file);

        os.write(new String("heheda").getBytes());
        os.flush();

        os.close();
    }

}
