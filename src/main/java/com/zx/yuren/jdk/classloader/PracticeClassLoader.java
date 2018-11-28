package com.zx.yuren.jdk.classloader;

import java.io.*;

/**
 * 自定义一个类加载器
 *
 * @author xu.qiang
 * @date 18/11/7
 */
public class PracticeClassLoader extends ClassLoader {

    private String classpath;

    public PracticeClassLoader(String classpath) {

        this.classpath = classpath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte [] classDate=getDate(name);

            if(classDate==null){

            } else{
                //defineClass方法将字节码转化为类
                return defineClass(name,classDate,0,classDate.length);
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

        return super.findClass(name);
    }
    //返回类的字节码
    private byte[] getDate(String className) throws IOException{
        InputStream in = null;
        ByteArrayOutputStream out = null;
        String path=classpath + File.separatorChar +
                className.replace('.',File.separatorChar)+".class";
        try {
            in=new FileInputStream(path);
            out=new ByteArrayOutputStream();
            byte[] buffer=new byte[2048];
            int len=0;
            while((len=in.read(buffer))!=-1){
                out.write(buffer,0,len);
            }
            return out.toByteArray();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally{
            in.close();
            out.close();
        }
        return null;
    }



}
