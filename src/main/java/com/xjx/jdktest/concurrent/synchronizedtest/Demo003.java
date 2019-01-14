
package com.xjx.jdktest.concurrent.synchronizedtest;


/**
 * 这个实例说明，synchronized并不支持，读写锁的概念，一个线程在读数据的时候，另外一个线程
 * 不能进来
 */

public class Demo003 {
    //private   int count = 0;
     private  static int count = 0;


/**
     * 一个线程先进来，睡2秒，获取monitor，
     * 然后假如说另外一个线程可以进来，说明多个读线程，可以共存
     * @return
     */

    public synchronized  String getCount() {
        //public synchronized static  vo id add() {
        System.out.println(Thread.currentThread().getName()+">enter=====");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+">getcount="+count);

       return new Integer(count).toString();
    }


/**
     * 一个线程先进来，睡2秒，获取monitor，
     * 然后假如说另外一个线程可以进来，说明多个读线程，可以共存
     * @return
     */

    public synchronized  String getCount2() {
        //public synchronized static  vo id add() {
        System.out.println(Thread.currentThread().getName()+">enter=====");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+">getcount="+count);

        return new Integer(count).toString();
    }



    public static void main(String[] args) {


        final Demo003 obj = new Demo003();
        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run() {
                obj.getCount();	//1.同一个对象、同一把锁
            }
        }, "thread1");

        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run() {
                //thread1.add();	//1、同一个对象、同一把锁
                obj.getCount2();
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
