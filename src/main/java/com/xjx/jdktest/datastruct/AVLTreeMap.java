package com.xjx.jdktest.datastruct;


import java.util.Stack;

public class AVLTreeMap <Key extends Comparable,Value>{

    private void update(TreeNode tree) {
        tree.size = size(tree.left) + size(tree.right) + 1;
        tree.height = Math.max(height(tree.left), height(tree.right)) + 1;
    }

    private TreeNode rightRotate(TreeNode oldRoot){
        TreeNode newRoot = oldRoot.left;
        oldRoot.left = newRoot.right ;
        update(oldRoot);
        newRoot.right = oldRoot ;
        update(newRoot);
        return  newRoot;
    }

    private TreeNode leftRotate(TreeNode oldRoot){
        TreeNode newRoot = oldRoot.right;
        oldRoot.right = newRoot.left ;
        update(oldRoot);
        newRoot.left = oldRoot ;
        update(newRoot);
        return  newRoot;
    }

    private TreeNode balance(TreeNode root) {
        if (height(root.left) - height(root.right) > 1) {
                if (height(root.left.left) > height(root.left.right)) { //LL
                root = rightRotate(root);
            }
            else { //LR
                root.left = leftRotate(root.left);
                root = rightRotate(root);
            }
        }
        else if (height(root.right) - height(root.left) > 1) {
            if (height(root.right.right) > height(root.right.left)) { //RR
                root = leftRotate(root);
            }
            else { //RL
                root.right = rightRotate(root.right);
                root = leftRotate(root);
            }
        }
        return root;
    }

    private TreeNode put(TreeNode root, Key key, Value value) {
        if (root == null)  return new TreeNode(key, value);
        if (key.compareTo(root.key) < 0) root.left = put(root.left, key, value);
        else if (key.compareTo(root.key) > 0) root.right = put(root.right, key, value);
        else root.value = value;
        update(root);
        return balance(root);
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }
    private TreeNode min(TreeNode root) {
        if (root.left == null) return root;
        return min(root.left);
    }

    private TreeNode remove(TreeNode root, Key key) {
        if (root == null) return null;
        if (key.compareTo(root.key) < 0) {
            root.left = remove(root.left, key);
        }
        else if (key.compareTo(root.key) > 0) {
            root.right = remove(root.right, key);
        }
        else {
            if (root.left == null) return root.right;
            else if (root.right == null) return  root.left;
            else {
                TreeNode successor = min(root.right);
                Key tempKey = root.key;
                root.key = successor.key;
                root.value = successor.value;
                successor.key = tempKey;
                root.right = remove(root.right, tempKey);
            }
        }
        update(root);
        return balance(root);
    }

    public void remove(Key key) {
        remove(root, key);
    }





    private  class TreeNode {
        Key key;
        Value value ;
        TreeNode left,right;
        int height ,size ;

        public TreeNode(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.height = 1;
            this.size =1;
        }
    }
    private TreeNode root;
    private int height(TreeNode tree){
        if (tree == null) return 0;
        return tree.height;
    }

    private int size(TreeNode tree) {
        if (tree == null) return 0;
        return tree.size;
    }

    public void displayTree(){
        Stack globalStack = new Stack();
        globalStack.push(root);
        int treeHeight = height(root);
        int nBlanks = (int) Math.pow(2d,(double)treeHeight);
        boolean isRowEmpty = false;
        System.out.println("=========================================================================");
        while(!isRowEmpty) {
            Stack localStack = new Stack();
            isRowEmpty = true;
            for (int  j =0;j<nBlanks;j++) {
                System.out.print(" ");
            }

            while (!globalStack.isEmpty()) {
                TreeNode temp = (TreeNode)globalStack.pop();
                if (temp!=null) {
                    System.out.print(temp.key);
                    localStack.push(temp.left);
                    localStack.push(temp.right);
                    if (temp.left != null || temp.right !=null) {
                        isRowEmpty = false;
                    }
                }
                else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0;j<nBlanks*2-2;j++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
            nBlanks /= 2;
            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }
        }
        System.out.println("=========================================================================");
    }


}

