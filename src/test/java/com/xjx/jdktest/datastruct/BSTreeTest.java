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

        theTree.insert(87, new Float(1.6f));
        theTree.insert(93, new Float(1.2f));
        theTree.insert(97, new Float(1.5f));

        theTree.insert(19, new Float(1.5f));
        theTree.insert(30, new Float(1.4f));
        theTree.insert(11, new Float(1.3f));
        theTree.insert(13, new Float(1.3f));

        theTree.insert(20, new Float(1.3f));
        theTree.insert(98, new Float(1.5f));
        theTree.insert(99, new Float(1.5f));
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
