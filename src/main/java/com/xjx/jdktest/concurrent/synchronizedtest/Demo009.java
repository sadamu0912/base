package com.xjx.jdktest.concurrent.synchronizedtest;

/**
 * 如果一个线程锁住对象，能通过实例访问静态变量，或者其他变量
 */
public class Demo009 {
    private static   Integer count = 0;

    public   static Integer count2 = 2;

    private Integer count3 = 3;
    public Integer count4 =4;

    public Integer getCount3() {
        return count3;
    }

    public static void main(String[] args) {


        final Demo009 obj = new Demo009();
        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run() {
               synchronized (obj){
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
        }, "thread1");

        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("对象被锁住时，访问对象的非同步方法，或者变量");
                System.out.println(obj.getCount3());
                System.out.println(obj.count4);
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
