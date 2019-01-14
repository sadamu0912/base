package com.xjx.jdktest.concurrent.synchronizedtest;


import java.util.concurrent.CountDownLatch;

/**
 * synchronized 是非公平锁还是公平锁  5个线程一起启动，3个线程调用synchronized方法，还有一个线程睡了1秒之后
 * 才开始调用ynchronized方法，还有一个线程直接拿对象锁，拿到之后睡30s然后执行同步方法
 */
public class Demo011 {
    private static CountDownLatch ready = new CountDownLatch(1);
    private static Integer count =1;
    public synchronized void getCount() {
        System.out.println(Thread.currentThread().getName()+">enter getCount=");
        System.out.println(Thread.currentThread().getName()+">getCount="+count);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+">leave getCount=");
    }
    public static void main(String[] args) {
            final Demo011 obj = new Demo011();
            for(int i=0;i<3;i++){
              new Thread(new DemoThread(ready,obj)).start();
            }
            new Thread(new DemoThread2(ready,obj),"DemoThread2").start();
        new Thread(new DemoThread3(ready,obj),"DemoThread3").start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            ready.countDown();
    }
}
class DemoThread implements Runnable{
    CountDownLatch latch ;
    Demo011 obj;
    DemoThread(CountDownLatch latch,Demo011 obj){
        this.latch = latch;
        this.obj = obj;
    }
    @Override
    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
                    obj.getCount();
    }
}

class DemoThread2 implements Runnable{
    CountDownLatch latch ;
    Demo011 obj;
    DemoThread2(CountDownLatch latch,Demo011 obj){
        this.latch = latch;
        this.obj = obj;
    }
    @Override
    public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            obj.getCount();
    }
}

class DemoThread3 implements Runnable{
    CountDownLatch latch ;
    Demo011 obj;
    DemoThread3(CountDownLatch latch,Demo011 obj){
        this.latch = latch;
        this.obj = obj;
    }
    @Override
    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (obj){
            System.out.println(Thread.currentThread().getName()+">getObj=");
            long start = System.currentTimeMillis();
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            obj.getCount();
            System.out.println("this thread has owned monitor for "+new Long(System.currentTimeMillis()-start).toString()+" ms");
            System.out.println(Thread.currentThread().getName()+">release Obj=");
        }

    }
}
