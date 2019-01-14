package com.xjx.jdktest.concurrent.container;

import java.util.concurrent.atomic.AtomicReference;

public class CLHLock {

    AtomicReference<QNode> tail = new AtomicReference<QNode>(new QNode());
    ThreadLocal<QNode> myPred;
    ThreadLocal<QNode> myNode;

    public static class QNode {
        //注意这个地方 如果不加volatile则会导致线程永远死循环
        public volatile boolean locked = false;
    }

    public CLHLock() {
        myNode = new ThreadLocal<QNode>() {
            protected QNode initialValue() {
                return new QNode();
            }
        };
        myPred = new ThreadLocal<QNode>() {
            protected QNode initialValue() {
                return null;
            }
        };
    }

    public void lock() {
        QNode qnode = myNode.get();
        qnode.locked = true;
        QNode pred = tail.getAndSet(qnode);
        myPred.set(pred);
        while (pred.locked) {
            //非阻塞算法
        }
    }

    public void unlock() {
        QNode qnode = myNode.get();
        qnode.locked = false;
        myNode.set(myPred.get());
    }
}
