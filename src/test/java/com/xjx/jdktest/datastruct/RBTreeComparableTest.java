package com.xjx.jdktest.datastruct;

import com.alibaba.fastjson.JSON;

public class RBTreeComparableTest {
    public static void main(String[] args) {
        RBTreeComparable<Integer,String> rbTree = new RBTreeComparable();
        rbTree.put(10,"10");
        rbTree.put(5,"5");
        rbTree.put(15,"15");
        rbTree.put(20,"20");
        rbTree.put(18,"18");
        System.out.println(JSON.toJSONString(rbTree));
    }
}
