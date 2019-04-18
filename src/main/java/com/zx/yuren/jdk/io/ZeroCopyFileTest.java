package com.zx.yuren.jdk.io;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author xu.qiang
 * @date 19/4/18
 */
public class ZeroCopyFileTest {


    /**
     * 通过字节流的方式复制文件
     * @param fromFile 源文件
     * @param toFile   目标文件
     * @throws FileNotFoundException 未找到文件异常
     */
    public static void fileCopyNormal(File fromFile, File toFile) throws FileNotFoundException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(fromFile));
            outputStream = new BufferedOutputStream(new FileOutputStream(toFile));
            //用户态缓冲有1kB这么大，不算小了
            byte[] bytes = new byte[1024];
            int i;
            //读取到输入流数据，然后写入到输出流中去，实现复制
            while ((i = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 用filechannel进行文件复制
     *
     * @param fromFile 源文件
     * @param toFile   目标文件
     */
    public static void fileCopyWithFileChannel(File fromFile, File toFile) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        FileChannel fileChannelInput = null;
        FileChannel fileChannelOutput = null;
        try {
            fileInputStream = new FileInputStream(fromFile);
            fileOutputStream = new FileOutputStream(toFile);
            //得到fileInputStream的文件通道
            fileChannelInput = fileInputStream.getChannel();
            //得到fileOutputStream的文件通道
            fileChannelOutput = fileOutputStream.getChannel();
            //将fileChannelInput通道的数据，写入到fileChannelOutput通道
            fileChannelInput.transferTo(0, fileChannelInput.size(), fileChannelOutput);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileChannelInput != null) {
                    fileChannelInput.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (fileChannelOutput != null) {
                    fileChannelOutput.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        File fromFile = new File("/Users/tbj/usr/local/tmp/tongbei.war");
        File toFile = new File("/Users/tbj/usr/local/tmp/tongbei.war.bak");

        //预热
        fileCopyNormal(fromFile, toFile);
        fileCopyWithFileChannel(fromFile, toFile);

        //计时
        long start = System.nanoTime();
        for (int i = 0; i < 5; i++) {
            fileCopyNormal(fromFile, toFile);
        }
        System.out.println("fileCopyNormal time: " + (System.nanoTime() - start));

        start = System.nanoTime();
        for (int i = 0; i < 5; i++) {
            fileCopyWithFileChannel(fromFile, toFile);
        }
        System.out.println("fileCopyWithFileChannel time: " + (System.nanoTime() - start));


        //磁盘读到内核后 直接到 socket缓冲区   底层用的是sendfile netty底层对这个做了包装
        File file = new File("test.zip");
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        FileChannel fileChannel = raf.getChannel();
        MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 1234));
        fileChannel.transferTo(0, fileChannel.size(), socketChannel);

    }

}
