package com.xjx.jdktest.datastruct;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BSTreeComparable<T extends Comparable> {

    private transient Node root;

    private static final String SUCCESSOR="successor";

    private static final String SUCCESSOR_PARENT="parent";

    private static final Node EMPTY_NODE = new Node(null,null,null,"empty node");

    public BSTreeComparable() { //初始化为一个空树
        root = null;
    }

    /**ConcurrentHashMap
     * 查询数据方法
     * @param key
     * @return
     */
    public Node get(T key){
        if(root ==null) return null;
        Node node =  getRecursive(root,key);
        if(node ==null) return  EMPTY_NODE;
        return node;
    }

    /**
     * 递归比较大小，如果小于该节点，则拿左节点继续比较
     * 如果大于该节点，就拿右节点继续比较
     * @param tree
     * @param key
     * @return
     */
    private Node getRecursive(Node tree,T key){
        if(tree==null) return null;
        if(key.compareTo(tree.key)<0){
            return getRecursive(tree.leftNode,key);
        }else if(key.compareTo(tree.key)>0){
            return getRecursive(tree.rightNode,key);
        }else{
            return tree;
        }
    }

    /**
     * 往BST中插入数据
     * @param key
     * @param value
     */
    public  void put(T key,Object value){
        if (root == null){
            root =  new Node(null,null,key,value);
        }else{
            putRecursive(root,key,value);
        }
    }
    private Node  putRecursive(Node tree,T key,Object value){
        if(tree==null) return new Node(null,null,key,value);
        if(key.compareTo(tree.key)<0){
            tree.leftNode = putRecursive(tree.leftNode,key,value);
        }else if(key.compareTo(tree.key)>0){
            tree.rightNode = putRecursive(tree.rightNode,key,value);
        }else{
            tree.content = value;
        }
        return tree;
    }

    public void remove(T key){
       Node newTree = remove(root,key);
       root = newTree;
    }

    /**
     * 从tree中找到这个节点，然后干掉，然后返回一颗新的树
     * @param tree
     * @param key
     * @return
     */
    private Node remove(Node tree ,T key){
        if(tree==null) return null;

        //先递归去找到这个节点
        if (key.compareTo(tree.key) < 0) {
            tree.leftNode = remove(tree.leftNode, key);
            return tree;
        }
        else if (key.compareTo(tree.key) > 0) {
            tree.rightNode = remove(tree.rightNode, key);
            return tree;
        }// 相等说明已经找到这个节点了
        else{
            //case 1 假如这个节点左子树为空，则右子树顶包
            if (tree.leftNode == null && tree.rightNode!=null) {
                Node rightTree = tree.rightNode;
               tree=null;
                return rightTree;
            }
            //case 2 假如这个节点右子树为空，则左子树顶包
            else if (tree.rightNode == null&& tree.leftNode!=null){
                Node leftTree = tree.leftNode;
                tree=null;
                return  leftTree;
                //case 3 是叶子节点
            }else if(tree.rightNode == null&& tree.leftNode==null){
                tree=null;    return  null;

            }
            //case 4 假如这个节点左右子树都不为空，则找到右子树种的最小值顶包
            else{
                Node toBeDeleted = tree;
                Map map = getSuccessor(tree.rightNode,null);
                Node successor = (Node) map.get(SUCCESSOR);
                Node successorParent = (Node) map.get(SUCCESSOR_PARENT);
                //如果successorParent是null的话，说明successor就是右节点
                if(successorParent==null){
                    successor.leftNode = toBeDeleted.leftNode;
                    tree=null;
                    toBeDeleted =null;
                    return   successor;
                }else{
                    successor.leftNode = toBeDeleted.leftNode;
                    successor.rightNode = toBeDeleted.rightNode;
                    successorParent.leftNode =null;
                    tree=null;
                    toBeDeleted =null;
                    return successor;
                }
            }
        }
    }

    /**
     * 返回顶包的节点，右子树的最小值就是继承者
     * @param tree
     * @param map
     * @return
     */
    private Map<String,Node> getSuccessor(Node tree,Map<String,Node> map) {
        if (map == null) {
            map =  new HashMap<>();
        }
        if (tree.leftNode == null){
            map.put(SUCCESSOR,tree);
            return map;
        }
        map.put(SUCCESSOR_PARENT,tree);
        Map<String,Node> map2 =  getSuccessor(tree.leftNode,map);
        return map2;
    }


    public static int getHeight(Node root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = 0;//记录左子树的树高
        int rightHeight = 0;//记录右子树树高
        if (root.leftNode != null) {//左子树不为空
            leftHeight += getHeight(root.leftNode) + 1;//实际就是左子树树高的累计，加上root节点，如果不加1，得到的就是最大子树的树高，不好root节点
        }
        if (root.rightNode != null) {
            rightHeight += getHeight(root.rightNode) + 1;
        }
        return leftHeight >= rightHeight ? leftHeight : rightHeight;
    }
    public void displayTree(){
        Stack globalStack = new Stack();
        globalStack.push(root);
        int treeHeight = getHeight(root);
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
                Node temp = (Node)globalStack.pop();
                if (temp!=null) {
                    System.out.print(temp.key);
                    localStack.push(temp.leftNode);
                    localStack.push(temp.rightNode);
                    if (temp.leftNode != null || temp.rightNode !=null) {
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

    static final class Node<T>{
        public Node leftNode;
        public Node rightNode;
        public T key;
        public Object content;

        public Node(Node leftNode, Node rightNode, T key, Object content) {
            this.leftNode = leftNode;
            this.rightNode = rightNode;
            this.key = key;
            this.content = content;
        }
    }
}
