package com.xjx.jdktest.base;

import java.util.concurrent.CountDownLatch;

/**
 * jdk7 下面跑
 */
public class FakeHashMapTest {
    public static void main(String[] args) {
        FakeHashMap map = new FakeHashMap();
        map.put("1","A");
        map.put("2","B");
        map.put("3","C");
        CountDownLatch allReady = new CountDownLatch(2);
        new Thread(new Task2(map,allReady),"Task2").start();
        new Thread(new Task1(map,allReady),"Task1").start();
    }
}
class Task1 implements Runnable{
    private FakeHashMap map;
    //先让两个线程都跑起来
    private CountDownLatch allReady ;

    public Task1(FakeHashMap map, CountDownLatch allReady) {
        this.map = map;
        this.allReady = allReady;
    }
    @Override
    public void run() {
        try {
            allReady.countDown();
            allReady.await();
            map.transfer(Thread.currentThread());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class Task2 implements Runnable{
    private FakeHashMap map;
    //先让两个线程都跑起来
    private CountDownLatch allReady ;
    public Task2(FakeHashMap map, CountDownLatch allReady) {
        this.map = map;
        this.allReady = allReady;
    }
    @Override
    public void run() {
        try {
            allReady.countDown();
            allReady.await();
            map.transfer(Thread.currentThread());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
