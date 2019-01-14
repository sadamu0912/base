package com.xjx.jdktest.concurrent.sematic;


import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程响应中断的语义，
 * 是在Thread.sleep .await 等线程挂起或者睡眠状态再打断
 * 还是在线程执行代码的【任意位置】打断，
 *
 */
public class TestLock2 {
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {


        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {

                    do321();
                    doPrint("thread 2 end.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    doPrint("thread 2 is interrupted！！！");
                }
            }
        },"thread 2");

        thread2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 1秒后把线程2中断
        thread2.interrupt();
    }

    private static void do123() throws InterruptedException {
        lock.lockInterruptibly();
        doPrint(Thread.currentThread().getName() + " is locked.");
        try {
            doPrint(Thread.currentThread().getName() + " doSoming1....");
            Thread.sleep(10000);// 等待几秒方便查看线程的先后顺序
            doPrint(Thread.currentThread().getName() + " doSoming2....");

            doPrint(Thread.currentThread().getName() + " is finished.");
        } finally {
            lock.unlock();
        }
    }

    private static void do321() throws InterruptedException {
        lock.lock();
        doPrint(Thread.currentThread().getName() + " is locked.");
        try {
            for(long i=0;i<10000000L;i++){
                System.out.println(i);
            }
            doPrint(Thread.currentThread().getName() + " doSoming1....");
            Thread.sleep(10000);// 此时才会响应中断
            doPrint(Thread.currentThread().getName() + " doSoming2....");

            doPrint(Thread.currentThread().getName() + " is finished.");
        } finally {
            lock.unlock();
        }
    }
    private static void doPrint(String text) {
        System.out.println((new Date()).toLocaleString() + " : " + text);
    }
}

