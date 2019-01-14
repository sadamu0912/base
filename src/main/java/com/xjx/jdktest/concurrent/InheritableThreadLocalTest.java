package com.xjx.jdktest.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SIGNAL    = -1：其后继节点已经被阻塞了，到时需要进行唤醒操作
 */
public class InheritableThreadLocalTest {

    public static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void main(String[] args) {
          InheritableThreadLocal<String> inheritableThreadLocal =
                new InheritableThreadLocal<String>();
        inheritableThreadLocal.set("xxxxx");
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"==get  "+
                        inheritableThreadLocal.get()+" from main Thread" );
            }
        });
        try{
            Thread.sleep(1000);
        }catch (Exception e){}
        inheritableThreadLocal.set("xxx2");
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"==get  "+
                        inheritableThreadLocal.get()+" from main Thread" );
            }
        });

        System.out.println(Thread.currentThread().getName()+"get==="+inheritableThreadLocal.get());
        try{
            Thread.sleep(2000);
        }catch (Exception e){}
        executorService.shutdown();
    }
}
