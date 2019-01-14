package com.xjx.jdktest.datastruct;

/** 条件：
 * （1）根节点是黑色。
 * （2）每个叶子节点（NIL）是黑色。 [注意：这里叶子节点，是指为空(NIL或NULL)的叶子节点！]
 * （3）如果一个节点是红色的，则它的子节点必须是黑色的。
 * （4）从一个节点到该节点的子孙节点的所有路径上包含相同数目的黑节点。
 * 特性：
 *  以红黑树中的一个黑节点为根节点，又是一个红黑树
 * @param <K>
 * @param <V>
 */
public class RBTreeComparable<K extends Comparable,V> {
    private static final boolean RED   = false;
    private static final boolean BLACK = true;

    private  Node<K,V> root;

    public Node<K, V> getRoot() {
        return root;
    }

    public void setRoot(Node<K, V> root) {
        this.root = root;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private  int size = 0;

    public void put(K key,V value) {
        if (key == null)
            throw new NullPointerException();
        if (root == null) {
            root = new Node(key,value,null);
            size = 1;
            return;
        }
        Node<K,V> e = new Node(key, value,null);
        //root的parent是空
        putRecursive(root,null,e,key,value);
        fixAfterInsertion(e);
        size++;
    }

    /**
     *  递归插入新节点，返回新的树，
     *  把父节点的地址放入新节点
     * @param tree
     * @param parentTree
     * @param tobeInsert
     * @param key
     * @param value
     * @return
     */
    private Node  putRecursive(Node tree,Node parentTree,Node tobeInsert ,K key,V value){
        if(tree==null) {
            tobeInsert.parent = parentTree;
            return tobeInsert;
        }
        if(key.compareTo(tree.key)<0){
            tree.left = putRecursive(tree.left,tree,tobeInsert,key,value);
        }else if(key.compareTo(tree.key)>0){
            tree.right = putRecursive(tree.right,tree,tobeInsert,key,value);
        }else{
            tree.value = value;
        }
        return tree;
    }

    private  Node parentOf(Node p) {
        return (p == null ? null: p.parent);
    }

    private   boolean colorOf(Node p) {
        return (p == null ? BLACK : p.color);
    }
    private  void setColor(Node p, boolean c) {
        if (p != null)
            p.color = c;
    }

    private  Node leftOf(Node p) {
        return (p == null) ? null: p.left;
    }

    private  Node rightOf(Node p) {
        return (p == null) ? null: p.right;
    }

    /**
     *因为红黑树加入了parent属性，所以左旋的时候，
     * 还要判断root节点是rootParent的左节点还是右节点
     * 【else if (oldRoot.parent.left == oldRoot)】
     * 右旋转刚好相反
     */
    private void rotateLeft(Node oldRoot) {
        if (oldRoot != null) {
            Node rightSubTree = oldRoot.right;
            oldRoot.right = rightSubTree.left;
            if (rightSubTree.left != null)
                rightSubTree.left.parent = oldRoot;
            rightSubTree.parent = oldRoot.parent;
            if (oldRoot.parent == null)
                root = rightSubTree;
            else if (oldRoot.parent.left == oldRoot)
                oldRoot.parent.left = rightSubTree;
            else
                oldRoot.parent.right = rightSubTree;
            rightSubTree.left = oldRoot;
            oldRoot.parent = rightSubTree;
        }
    }

    private void rotateRight(Node p) {
        if (p != null) {
            Node l = p.left;
            p.left = l.right;
            if (l.right != null) l.right.parent = p;
            l.parent = p.parent;
            if (p.parent == null)
                root = l;
            else if (p.parent.right == p)
                p.parent.right = l;
            else p.parent.left = l;
            l.right = p;
            p.parent = l;
        }
    }

    /**
     *
     * @param x
     */
    private void fixAfterInsertion(Node<K,V> x) {
        x.color = RED;

        while (x != null && x != root && x.parent.color == RED) {
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
                Node<K,V> y = rightOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == rightOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateLeft(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateRight(parentOf(parentOf(x)));
                }
            } else {
                Node<K,V> y = leftOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == leftOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateRight(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateLeft(parentOf(parentOf(x)));
                }
            }
        }
        root.color = BLACK;
    }



    static final class Node<K extends Comparable,V> {
        private K key;
        private V value;
        private Node<K,V> left;
        private  Node<K,V>  right;
        private Node<K,V>  parent;
        private boolean color = BLACK;
        public Node(K key,V value,Node parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
            this.color = BLACK;
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

        public Node<K, V> getLeft() {
            return left;
        }

        public void setLeft(Node<K, V> left) {
            this.left = left;
        }

        public Node<K, V> getRight() {
            return right;
        }

        public void setRight(Node<K, V> right) {
            this.right = right;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }
    }
}
