package com.xjx.jdktest.concurrent.component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 由读写锁ReentrantReadWriteLock 写出的线程安全的hashmap.
 */
public class Cache  {
    static Map<String,Object> cache = new HashMap<>();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();

    public static Object get(String key){
        readLock.lock();
        try{
            return cache.get(key);
        }finally {
            readLock.unlock();
        }
    }

    public static boolean put(String key,Object  obj){
        writeLock.lock();
        try{
            cache.put(key,obj);
            return  true;
        }finally {
            writeLock.unlock();
        }
    }

    public static void clear(){
        writeLock.lock();
        try{
            cache.clear();
        }finally {
            writeLock.unlock();
        }
    }
}
