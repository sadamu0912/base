/*
package com.xjx.jdktest.concurrent;

import java.util.concurrent.TimeUnit;

public class VolatileTest3 {

    public volatile   boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        VolatileTest3 test = new VolatileTest3();
        long time1 = System.currentTimeMillis();
        for(int i=0;i<10;i++) {
            Thread backgroundThread = new Thread(new Runnable() {

                public void run() {
                    // TODO Auto-generated method stub
                    int count = 0;
                    while (!test.stopRequested) {
                        System.out.println(count);
                    }
                    System.out.println("time5="+System.currentTimeMillis());
                }
            });
            backgroundThread.start();
        }
        long time2 = System.currentTimeMillis();
        System.out.println("启动线程"+(time2-time1));
        TimeUnit.SECONDS.sleep(1);
        System.out.println("time3="+System.currentTimeMillis());
        test.stopRequested = true;
        long time4 = System.currentTimeMillis();
        System.out.println("time4="+System.currentTimeMillis());
    }

}
*/
