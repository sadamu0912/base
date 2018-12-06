package com.xjx.jdktest.datastruct;

public class BSTreeComparableTest {
    public static void main(String[] args) {
        BSTreeComparable tree = new BSTreeComparable();
        tree.put(20,"20");
        tree.put(10,"10");
        tree.put(7,"7");
        tree.put(4,"4");
        tree.put(30,"30");
        tree.put(40,"40");
        tree.put(14,"14");
        tree.put(12,"12");
        tree.put(15,"15");
        //tree.remove(12);
        //tree.remove(30);
        //tree.remove(7);
        tree.remove(10);
       tree.displayTree();
    }
}
