//package com.xjx.jdktest.concurrent.component.cyclicbarrier;
//
//import java.util.concurrent.BrokenBarrierException;
//import java.util.concurrent.CyclicBarrier;
//import java.util.concurrent.TimeoutException;
//import java.util.concurrent.locks.Condition;
//import java.util.concurrent.locks.ReentrantLock;
//
//public class Temp {
//    private final ReentrantLock lock = new ReentrantLock();
//    private final Condition trip = lock.newCondition();
//    private final int parties;
//    private final Runnable barrierCommand;
//    private int count;
//    private Generation generation = new Generation();
//    private static class Generation {
//        boolean broken = false;
//    }
//
//    private void nextGeneration() {
//        // signal completion of last generation
//        trip.signalAll();
//        // set up next generation
//        count = parties;
//        generation = new Generation();
//    }
//
//    private int dowait(boolean timed, long nanos)
//            throws InterruptedException, BrokenBarrierException,
//            TimeoutException {
//        final ReentrantLock lock = this.lock;
//        // 获取“独占锁(lock)”
//        lock.lock();
//        try {
//            // 保存“当前这一代屏障是否损坏”
//            final Generation g = generation;
//            if (g.broken)
//                throw new BrokenBarrierException();
//            if (Thread.interrupted()) {
//                breakBarrier();
//                throw new InterruptedException();
//            }
//            int index = --count;
//            // 如果index=0，则意味着“有parties个线程到达barrier”。
//            if (index == 0) {  // tripped
//                boolean ranAction = false;
//                try {
//                    //这里可以看出，满足条件时，先执行的barrierCommand，再唤醒其他线程去执行代码
//                    final Runnable command = barrierCommand;
//                    if (command != null)
//                        command.run();
//                    ranAction = true;
//                    //  调用trip.signalAll();唤醒线程，执行各自await方法后面的代码
//                    nextGeneration();
//                    return 0;
//                } finally {
//                    if (!ranAction)
//                        breakBarrier();
//                }
//            }
//            // loop until tripped, broken, interrupted, or timed out
//            for (;;) {
//                try {
//                    if (!timed)
//                        //这里可以看出，调用cyclicBarrier.await()方法的时候，实际上是
//                        //被ConditionObject的await()方法阻塞，而最终调用LockSupport.park(this);
//                        //Disables the current thread for thread scheduling,释放锁
//                        //被插入独占锁的trip条件等待队列中
//                        trip.await();
//                    else if (nanos > 0L)
//                        nanos = trip.awaitNanos(nanos);
//                } catch (InterruptedException ie) {
//                    if (g == generation && ! g.broken) {
//                        breakBarrier();
//                        throw ie;
//                    } else {
//                        // We're about to finish waiting even if we had not
//                        // been interrupted, so this interrupt is deemed to
//                        // "belong" to subsequent execution.
//                        Thread.currentThread().interrupt();
//                    }
//                }
//                if (g.broken)
//                    throw new BrokenBarrierException();
//                if (g != generation)
//                    return index;
//                if (timed && nanos <= 0L) {
//                    breakBarrier();
//                    throw new TimeoutException();
//                }
//            }
//        } finally {
//            lock.unlock();
//        }
//    }
//}
