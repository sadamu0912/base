package com.xjx.jdktest.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 自定义同步器
 */
public class MutexLock {
    /**
     * 同步器主要管理同步状态，同步队列，还有condition队列
     */
    private static class Sync extends  AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            if(compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if(getState()==0){
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        ConditionObject newCondition(){return new ConditionObject();}

        @Override
        protected boolean isHeldExclusively() {
            return super.isHeldExclusively();
        }
    }
    private final Sync sync = new Sync();

    public void lock (){
        sync.tryAcquire(1);
    }

    public  boolean tryLock(){
        return  sync.tryAcquire(1);
    }

    public void unlock (){
        sync.tryRelease(1);
    }
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }
}
