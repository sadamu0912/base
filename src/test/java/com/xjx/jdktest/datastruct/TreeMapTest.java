package com.xjx.jdktest.datastruct;

public class TreeMapTest {
    public static void main(String[] args) {
            AVLTreeMap map =new AVLTreeMap<Float,String>();
        map.put(10.0f,"10");
        map.put(9.0f,"9");
        map.put(11f,"11");
        map.put(8.0f,"8");
        map.put(12f,"12");
        map.remove(10f);
        map.displayTree();
    }
}
