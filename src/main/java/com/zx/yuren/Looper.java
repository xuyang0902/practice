package com.zx.yuren;

/**
 * @author xu.qiang
 * @date 19/2/13
 */
public class Looper {
    public static void main(String[] args) throws InterruptedException {


        new Thread(new Runnable() {

            @Override
            public void run() {
                while(true){

                    System.out.println("hahah");
                }

            }
        }).start();


        while(true){


            System.out.println("hjjjjjjjjjjjj");
        }


    }
}
