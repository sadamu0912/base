package com.xjx.jdktest.concurrent;

import java.util.concurrent.CountDownLatch;

public class VolatileTest2 {

    private static volatile boolean status=false ;

    private static CountDownLatch start  = new CountDownLatch(1);

    public void setStatusTrue(){
        status =true;
    }
    public void  getStatus(){
        System.out.println(status);
    }
    public static void main(String[] args) throws InterruptedException{
        VolatileTest2 test =  new VolatileTest2();
        new Thread(new Task2(start,test)).start();
        for(int i=0;i<10;i++){
            new Thread(new Task1(start,test)).start();
        }
    }
}

class Task1 implements Runnable{
    private CountDownLatch latch;
    private VolatileTest2 test ;
    public Task1(CountDownLatch start,VolatileTest2 test){
        this.latch = start;
        this.test = test;
    }
    @Override
    public void run() {
        try{
           latch.await();
        }catch (Exception e){
        }
            test.getStatus();
    }
}

/**
 * 这个线程吧状态设置成true,然后同步计数器马上变成0.之后，就其它线程马上就能看到status状态为true
 */
class Task2 implements Runnable{
    private CountDownLatch latch;
    private VolatileTest2 test ;
    public Task2(CountDownLatch start,VolatileTest2 test){
        this.latch = start;
        this.test = test;
    }
    @Override
    public void run() {
        test.setStatusTrue();
        latch.countDown();
        System.out.println("countDown===");
    }
}
