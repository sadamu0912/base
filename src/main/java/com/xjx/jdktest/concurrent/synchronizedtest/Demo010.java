package com.xjx.jdktest.concurrent.synchronizedtest;

/**
 * 如果一个线程锁住对象，能通过实例访问静态变量，或者其他变量
 */
public class Demo010 {
    private static   Integer count = 0;

    public   static Integer count2 = 2;

    private Integer count3 = 3;
    public Integer count4 =4;

    public synchronized Integer getCount3() {
        return count3;
    }

    public static void main(String[] args) {


        final Demo010 obj = new Demo010();
        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run() {
                synchronized (obj){
                    System.out.println(Thread.currentThread().getName()+">enter=");
                   // int k = 10/0;
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
                System.out.println("对象被锁住时，并且发生异常");
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
