package com.xjx.jdktest.concurrent;

public class ThreadLocalTest {
    public static void main(String[] args) throws InterruptedException {
        final ThreadLocalTest test = new ThreadLocalTest();

        test.set();
        System.out.println(test.getLong());
        System.out.println(test.getString());
        // 在这里新建了一个线程
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.set(); //
                System.out.println("Thread1==yield");
                try{
                    Thread.currentThread().yield();
                }catch (Exception e){}
                test.set2();
                System.out.println(Thread.currentThread().getName()+"==="+test.getLong());
                System.out.println(Thread.currentThread().getName()+"==="+test.getString());
            }
        },"Thread1") ;
        thread1.start();

        System.out.println(test.getLong());
        System.out.println(test.getString());
    }

    ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
    ThreadLocal<String> stringLocal = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "";
        }
    };

    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }
    public void set2() {
        longLocal.set(new Long(3));
        stringLocal.set("xxxxxxx");
    }

    public long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

}

