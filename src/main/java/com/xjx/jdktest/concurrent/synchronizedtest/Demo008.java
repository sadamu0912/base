package com.xjx.jdktest.concurrent.synchronizedtest;

/**
 * 如果一个线程锁住class对象，能通过实例访问class对象吗？
 */
public class Demo008 {

    public   void getCount() {
        synchronized(Demo008.class){
            System.out.println(Thread.currentThread().getName()+">enter=");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+">leave=");
        }

    }

    public static void main(String[] args) {


        final Demo008 obj = new Demo008();
        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run() {
                obj.getCount();	//1.同一个对象、同一把锁
            }
        }, "thread1");

        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("获取class对象并且调用class对象的方法");
                System.out.println(obj.getClass().getName());
            }
        }, "thread2");

        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        t2.start();
    }
}
