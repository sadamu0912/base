package com.xjx.jdktest.concurrent.sematic;

import java.util.concurrent.locks.LockSupport;


/**
 * 线程的挂起和唤醒
 * 睡眠是【主动开始】【有时间限制的结束】
 * 挂起是【主动开始】【主动结束】
 * 阻塞是【被动开始】【被动结束】
 */
public class LockSupportTest1 {
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();

        LockSupport.unpark(thread);

        System.out.println("a");
        LockSupport.park();
        System.out.println("b");
        LockSupport.park();
        System.out.println("c");
        //输出a和b
    }
}
