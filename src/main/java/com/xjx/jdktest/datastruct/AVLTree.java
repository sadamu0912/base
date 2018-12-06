package com.xjx.jdktest.datastruct;

public class AVLTree<T extends Comparable<T>> {

    protected Node root;

    public void insert(T data){
        insert(data,root);
    }

    private Node insert(T data,Node<T> t){
        int compareResult = data.compareTo(t.data);
        if(compareResult<0){
            //就插入左子树
            t.leftNode = insert(data,t.leftNode);
            //插入之后打破平衡
            if(height(t.leftNode)-height(t.rightNode)==2){
                if (data.compareTo((T) t.leftNode.data) < 0) {
                    //如果x小于t的左子树的值，那么x会被插到t的左子树的左子树上，符合LL 用右旋转调整。
                    t = rightRotate(t);
                } else {
                    //如果x大于t的左子树的值，则会被插到t的左子树的右子树上，符合LR，用先左旋转后右旋转来矫正。
                    t = leftAndRightRotate(t);
                }
            }
        }else if (compareResult > 0) {//插到右子树上，逻辑和上面一样。
            t.rightNode = insert(data, t.rightNode);
            if (height(t.rightNode) - height(t.leftNode) == 2) {
                if (data.compareTo((T) t.rightNode.data) > 0) {
                    t = leftRotate(t);
                } else {
                    t = rightAndLeftRotate(t);
                }
            }
        } else {
            //已经有这个值了
        }
        t.height = Math.max(height(t.leftNode), height(t.rightNode)) + 1;
        return t;
    }

    /**
     * 删除数据
     */
    private Node<T> remove(T x, Node<T> t) {
        if (t == null)
            return null;
        int compareResult = x.compareTo(t.data);
        if (compareResult < 0) {
            t.leftNode = remove(x, t.leftNode);
            //完了之后验证该子树是否平衡
            if (t.rightNode != null) {        //若右子树为空，则一定是平衡的，此时左子树相当对父节点深度最多为1, 所以只考虑右子树非空情况
                if (t.leftNode == null) {     //若左子树删除后为空，则需要判断右子树
                    if (height(t.rightNode) - t.height == 2) {
                        Node<T> k = t.rightNode;
                        if (k.rightNode != null) {        //右子树存在，按正常情况单旋转
                            t = leftRotate(t);
                        } else {                      //否则是右左情况，双旋转
                            t = rightAndLeftRotate(t);
                        }
                    }
                }
                if (t.leftNode!=null){                  //否则判断左右子树的高度差
                    //左子树自身也可能不平衡，故先平衡左子树，再考虑整体
                    Node<T> k = t.leftNode;
                    //删除操作默认用右子树上最小节点补删除的节点
                    //k的左子树高度不低于k的右子树
                    if (k.rightNode != null) {
                        if (height(k.leftNode) - height(k.rightNode) == 2) {
                            Node<T> m = k.leftNode;
                            if (m.leftNode != null) {     //左子树存在，按正常情况单旋转
                                k = rightRotate(k);
                            } else {                      //否则是左右情况，双旋转
                                k = leftAndRightRotate(k);
                            }
                        }
                    } else {
                        if (height(k.leftNode) - k.height == 2) {
                            Node<T> m = k.leftNode;
                            if (m.leftNode != null) {     //左子树存在，按正常情况单旋转
                                k = rightRotate(k);
                            } else {                      //否则是左右情况，双旋转
                                k = leftAndRightRotate(k);
                            }
                        }
                    }
                    if (height(t.rightNode) - height(t.leftNode) == 2) {
                        //右子树自身一定是平衡的，左右失衡的话单旋转可以解决问题
                        t = leftRotate(t);
                    }
                }
            }
            //完了之后更新height值
            t.height = Math.max(height(t.leftNode), height(t.rightNode)) + 1;
        } else if (compareResult > 0) {
            t.rightNode = remove(x, t.rightNode);
            //下面验证子树是否平衡
            if (t.leftNode != null) {         //若左子树为空，则一定是平衡的，此时右子树相当对父节点深度最多为1
                t = balanceChild(t);
            }
            //完了之后更新height值
            t.height = Math.max(height(t.leftNode), height(t.rightNode)) + 1;
        } else if (t.leftNode != null && t.rightNode != null) {
            //默认用其右子树的最小数据代替该节点的数据并递归的删除那个节点
            Node<T> min = t.rightNode;
            while (min.leftNode != null) {
                min = min.leftNode;
            }
//            t.element = findMin(t.right).element;
            t.data = min.data;
            t.rightNode = remove(t.data, t.rightNode);
            t = balanceChild(t);
            //完了之后更新height值
            t.height = Math.max(height(t.leftNode), height(t.rightNode)) + 1;
        } else {
            t = (t.leftNode != null) ? t.leftNode : t.rightNode;
        }
        return t;
    }

    private Node<T> balanceChild(Node<T> t) {
        if (t.rightNode == null) {        //若右子树删除后为空，则只需判断左子树与根的高度差
            if (height(t.leftNode) - t.height == 2) {
                Node<T> k = t.leftNode;
                if (k.leftNode != null) {
                    t = rightRotate(t);
                } else {
                    t = leftAndRightRotate(t);
                }
            }
        } else {              //若右子树删除后非空，则判断左右子树的高度差
            //右子树自身也可能不平衡，故先平衡右子树，再考虑整体
            Node<T> k = t.rightNode;
            //删除操作默认用右子树上最小节点（靠左）补删除的节点

            if (k.leftNode != null) {
                if (height(k.rightNode) - height(k.leftNode) == 2) {
                    Node<T> m = k.rightNode;
                    if (m.rightNode != null) {        //右子树存在，按正常情况单旋转
                        k = leftRotate(k);
                    } else {                      //否则是右左情况，双旋转
                        k = rightAndLeftRotate(k);
                    }
                }
            } else {
                if (height(k.rightNode) - k.height == 2) {
                    Node<T> m = k.rightNode;
                    if (m.rightNode != null) {        //右子树存在，按正常情况单旋转
                        k = leftRotate(k);
                    } else {                      //否则是右左情况，双旋转
                        k = rightAndLeftRotate(k);
                    }
                }
            }
            //左子树自身一定是平衡的，左右失衡的话单旋转可以解决问题
            if (height(t.leftNode) - height(t.rightNode) == 2) {
                t = rightRotate(t);
            }
        }
        return t;
    }

    /**
     * j就是改变了3个节点的地址
     * @param t
     * @return
     */
    private Node rightRotate(Node t){
        Node newTree = t.leftNode;
        t.leftNode = newTree.rightNode;
        newTree.rightNode = t;
        t.height = Math.max(height(t.leftNode), height(t.rightNode)) + 1;
        newTree.height = Math.max(height(newTree.leftNode), height(newTree.rightNode)) + 1;
        return newTree;
    }

    /**
     * 左旋转
     */
    private Node<T> leftRotate(Node t) {
        Node<T> newTree = t.rightNode;
        t.rightNode = newTree.leftNode;
        newTree.leftNode = t;
        t.height = Math.max(height(t.leftNode), height(t.rightNode)) + 1;
        newTree.height = Math.max(height(newTree.leftNode), height(newTree.rightNode)) + 1;
        return newTree;
    }

    /**
     * 先左旋后右旋
     */
    private Node<T> leftAndRightRotate(Node<T> t) {
        t.leftNode = leftRotate(t.leftNode);
        return rightRotate(t);
    }

    /**
     * 先右旋后左旋
     */
    private Node<T> rightAndLeftRotate(Node<T> t) {
        t.rightNode = rightRotate(t.rightNode);
        return leftRotate(t);
    }



    /**
     * 获取指定树的高度
     */
    private int height(Node<T> t) {
        return t == null ? -1 : t.height;
    }

    private static class Node<T> {
        public Node leftNode;
        public Node rightNode;
        public T data;
        public int height ;
    }
}
