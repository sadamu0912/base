package com.xjx.jdktest.concurrent.sematic;

import java.sql.Connection;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        new Thread(()->{
            lock.lock();
            Condition condition = lock.newCondition();
            try{
               condition.await();
            }catch (Exception e){

            }finally {
                System.out.println(Thread.currentThread().getName()+"释放锁");
                lock.unlock();
            }
        }).start();
    }
}
