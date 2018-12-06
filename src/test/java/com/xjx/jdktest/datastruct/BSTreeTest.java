package com.xjx.jdktest.datastruct;

public class BSTreeTest {
    public static void main(String[] args) {
        BSTree theTree = new BSTree();
        theTree.insert(50, new Float(1.3f));
        theTree.insert(25, new Float(1.1f));
        theTree.insert(75, new Float(1.7f));

        theTree.insert(12, new Float(1.3f));
        theTree.insert(37, new Float(1.9f));
        theTree.insert(43, new Float(1.3f));

        System.out.println(theTree.find(12).index);

       // theTree.displayTree();
//		theTree.traverse(1);
//		theTree.traverse(2);
//		theTree.traverse(3);
      //  theTree.delete(25);
        theTree.displayTree();
     //   System.out.println("钱序遍历=============");
     //   theTree.preOrder(theTree.getRoot());
      //  System.out.println("\n");
      //  System.out.println("中序遍历=============");
     //   theTree.inOrder(theTree.getRoot());
       // System.out.println("\n");
      //  System.out.println("后序遍历=============");
       // theTree.backOrder(theTree.getRoot());
    }
}
