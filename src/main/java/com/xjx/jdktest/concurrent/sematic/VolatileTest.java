package com.xjx.jdktest.concurrent.sematic;

import java.util.concurrent.CountDownLatch;

/**
 * 这个实例说明，volatile不具备原子性
 */
public class VolatileTest {

    private static volatile int count=0 ;

    private static CountDownLatch start  = new CountDownLatch(10);

    /**
     * 这里是个复合操作，读取count变量，计算，然后写回去
     * */
    public  synchronized void increase(){
        count ++;
    }
    public void  getCount(){
        System.out.println(count);
    }
    public static void main(String[] args) throws InterruptedException{
        VolatileTest test =  new VolatileTest();
        for(int i=0;i<10;i++){
            new Thread(new Task(start,test)).start();
        }
        start.await();
        test.getCount();
    }
}

class Task implements Runnable{
        private CountDownLatch latch;
        private VolatileTest test ;
    public Task(CountDownLatch start,VolatileTest test){
        this.latch = start;
        this.test = test;
    }
    @Override
    public void run() {
        System.out.println("countDown===");

        for(int j=0;j<1000;j++)
           test. increase();
        latch.countDown();
    }
}
