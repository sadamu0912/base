package com.xjx.jdktest.concurrent.component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    public static void main(String[] args) {
         Lock lock = new ReentrantLock();
        lock.lock();
        for (int i = 0; i < 5; i++) {
            System.out.println("ThreadName=" + Thread.currentThread().getName()
                    + (" " + (i + 1)));
        }
        for (int i = 0; i < 5; i++) {

        }
        lock.unlock();
    }
}
