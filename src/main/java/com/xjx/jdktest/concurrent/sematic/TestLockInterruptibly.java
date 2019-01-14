package com.xjx.jdktest.concurrent.sematic;

import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock.lockInterruptibly();//等待锁的过程中会立即响应中断
 * 在【临界区代码段的任意位置】被中断
 */
public class TestLockInterruptibly {
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {

                    do123();
                    doPrint("thread 1 end.");

                } catch (InterruptedException e) {
                    doPrint("thread 1 is interrupted.");
                }
            }
        },"thread 1");

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

        Thread thread3 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {

                    do321();
                    doPrint("thread 3 end.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    doPrint("thread 3 is interrupted！！！");
                }
            }
        },"thread 3");

        thread1.start();
        try {
            Thread.sleep(1000);// 等待一会使得thread1会在thread2前面执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
// 1秒后把线程2中断
        thread2.interrupt();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread3.start();
    }

    private static void do123() throws InterruptedException {
        lock.lockInterruptibly();//等待锁的过程中会立即响应中断
        doPrint(Thread.currentThread().getName() + " is locked.");
        try {
            doPrint(Thread.currentThread().getName() + " doSoming1....");
            Thread.sleep(8000);// 等待几秒方便查看线程的先后顺序
            doPrint(Thread.currentThread().getName() + " doSoming2....");

            doPrint(Thread.currentThread().getName() + " is finished.");
        } finally {
            doPrint(Thread.currentThread().getName()+"释放锁");
            lock.unlock();
        }
    }

    private static void do321() throws InterruptedException {
        lock.lockInterruptibly();//等待锁的过程中会立即响应中断
        doPrint(Thread.currentThread().getName() + " is locked.");
        try {
            doPrint(Thread.currentThread().getName() + " doSoming1....");
            for(long i=0;i<5000000L;i++){
                System.out.println(i);
            }
            Thread.sleep(10000);// 等待几秒方便查看线程的先后顺序
            doPrint(Thread.currentThread().getName() + " doSoming2....");

            doPrint(Thread.currentThread().getName() + " is finished.");
        } finally {
            doPrint(Thread.currentThread().getName()+"释放锁");
            lock.unlock();
        }
    }

    private static void doPrint(String text) {
        System.out.println((new Date()).toLocaleString() + " : " + text);
    }
}

