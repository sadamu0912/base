package com.xjx.jdktest.base;

import java.util.concurrent.CountDownLatch;

/**
 * jdk7 下面跑
 * 构造一个简化的hashmap，去掉一些无关参数，来看头插法，
 * 如何导致，死循环,开始为4，然后扩展为8
 *  假设都落在1号bucket上,扩展之后也落在1号bucket上
 * @param <K>
 * @param <V>
 */
public class FakeHashMap<K,V> {

    private static final Integer index = 1;
    private Node<K,V>[] table =  new Node[4];
    private CountDownLatch start = new CountDownLatch(1);
    private CountDownLatch sleep = new CountDownLatch(1);

    static class Node<K,V>{
        private K key;
        private V  value;
        private Node<K,V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }
    }

    public void put(K key, V value){
        Node node = new Node<>(key,value,null);
        if(table[index]!=null){
            Node tail = table[index];
            while(tail.next!=null){
                tail=  tail.next;
            }
            tail.next = node;
        }else{
            table[index] = node;
        }

    }

    public void transfer( Thread task) {
        if(task.getName().equals("Task1")){
            transfer(new Node[8]);
        }else if(task.getName().equals("Task2")){
            transfer2(new Node[8]);
        }

    }
    /**
     * task1  执行Node<K,V> next = e.next;完之后，睡觉10秒
     * @param newTable
     */
    public   void transfer(Node[] newTable) {
        for (Node<K,V> e : table) {
            while(null != e) {
                Node<K,V> next = e.next;
                try {
                    System.out.println("Task1 has runned a short time======");
                    start.countDown();
                    System.out.println("Task1 wait======");
                    sleep.await();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                e.next = newTable[index];
                newTable[index] = e;
                e = next;
            }
        }
        System.out.println("Task1 finished======");
    }
    public  void transfer2(Node[] newTable) {
        //start开关，先让transfer先执行
        try {
            start.await();
            System.out.println("Task2 begin======");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Node<K,V> e : table) {
            while(null != e) {
                Node<K,V> next = e.next;
                e.next = newTable[index];
                newTable[index] = e;
                e = next;
            }
        }
        System.out.println("Task2 finished======");
        sleep.countDown();
    }
}
