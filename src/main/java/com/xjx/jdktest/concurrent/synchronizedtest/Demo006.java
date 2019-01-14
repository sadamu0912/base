
package com.xjx.jdktest.concurrent.synchronizedtest;


/**
 * 这个例子证明：当我锁住class对象的时候，能访问static变量
 */

public class Demo006 {
    private   int count = 0;

    public   static int count2 = 5;

    public   void getCount() {
        synchronized (Demo006.class){
            System.out.println(Thread.currentThread().getName()+">enter=");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+">getCount="+count);
        }

    }

    public static void main(String[] args) {


        final Demo006 obj = new Demo006();
        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run() {
                obj.getCount();	//1.同一个对象、同一把锁
            }
        }, "thread1");

        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run() {
                //thread1.add();
                System.out.println(obj.count2);//1、通过对象访问静态变量
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

