package com.xjx.jdktest.concurrent.sematic;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest3 {
    public static void main(String[] args) {
        System.out.println("main");
        LockSupport.park();

        LockSupport.unpark(Thread.currentThread());
    }
}
