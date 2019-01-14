
package com.xjx.jdktest.concurrent.synchronizedtest;


/**
 * 这个实例说明，一个线程访问对象的静态同步方法1，另外一个线程通过同一个对象访问静态变量
 * 可以进来
 * 实际结果： System.out.println(obj.count2);//1、通过对象访问静态变量
 *  System.out.println(count2);//1、直接访问静态变量
 *  都是可以的
 *  可以发现带static的同步方法是锁住class对象，如果是不带static是锁住对象
 */

public class Demo005 {
    private static  int count = 0;

    public   static int count2 = 5;

    public synchronized static   void getCount() {
        System.out.println(Thread.currentThread().getName()+">enter=");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+">getCount="+count);
        System.out.println(Thread.currentThread().getName()+">leave=");
    }

    public static void main(String[] args) {


        final Demo005 obj = new Demo005();
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

