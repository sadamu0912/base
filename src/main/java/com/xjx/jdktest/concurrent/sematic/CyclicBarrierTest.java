package com.xjx.jdktest.concurrent.sematic;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 串行开关，【重置语义】
 * 【串行电路，当所有开关都合上，这个屏障放开，电流通过】
 * 【重置代表，比如说5个开关串联，已经合上两个了，但是重置之后，所有开关又断开了】
 */
public class CyclicBarrierTest {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier2 = new CyclicBarrier(2);
//如果是一个初始的CyclicBarrier，则reset()之后，什么也不会发生
        System.out.println("如果是一个初始的CyclicBarrier，则reset()之后，什么也不会发生");
        barrier2.reset();
        System.out.println();

        Thread.sleep(100);
//如果是一个已经打开一次的CyclicBarrier，则reset()之后，什么也不会发生
        ExecutorService executorService2 = Executors.newCachedThreadPool();
        System.out.println();
//如果是一个 有线程正在等待的线程，则reset()方法会使正在等待的线程抛出异常
        executorService2.submit(() -> {
            executorService2.submit(() -> {
                try {
                    barrier2.await();
                    System.out.println("333屏障已经打开.");
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                    System.out.println("333被中断");
                } catch (BrokenBarrierException e) {
                    System.out.println("在等待过程中，执行reset()方法，等待的线程抛出BrokenBarrierException异常，并不再等待");
                    //e.printStackTrace();
                }
            });
        });
        Thread.sleep(100);
        barrier2.reset();
        executorService2.shutdown();
    }
}
