package com.xjx.jdktest.concurrent.sematic;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest2 {
    public static void main(String[] args) {
        System.out.println("main");

        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("thread2====");
                LockSupport.park();
                System.out.println("a");
            }
        }, "thread2");
        t2.start();
        try {
            Thread.sleep(1000);
        }catch (Exception e){}
        LockSupport.unpark(t2);
    }

}
