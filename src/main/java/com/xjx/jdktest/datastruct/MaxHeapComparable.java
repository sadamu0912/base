package com.xjx.jdktest.datastruct;

import java.util.List;

public class MaxHeapComparable<T extends Comparable> {
    /**
     * 根节点也就是最大值
     */
    private T root;
    private List<T> elementData;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 8;

    public MaxHeapComparable() {
        this.capacity = DEFAULT_CAPACITY;
    }

    public MaxHeapComparable(int initailCapaticty) {
        this.capacity = initailCapaticty;
    }

    /**
     * 插入的时候，大根堆是大的数字上浮
     * 小根堆是小的数字上浮。
     * 逐层往上。
     */
    public void floatUp(T[] array, int k) {
        int childIndex = k;
        int parentIndex = (childIndex - 1) / 2;
        // temp保存插入的叶子节点值，用于最后的赋值
        T temp = array[k];
        while (childIndex > 0 && temp.compareTo(array[parentIndex]) > 0) {
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (childIndex - 1) / 2;
        }
        array[parentIndex] = temp;

    }

    public void printHeap(T[] array) {
        for (T n : array) {
            System.out.println(n);
        }
    }

    /**
     * 把小的往下沉，大的往上浮
     * @param array
     * @param parentIndex
     */
    public  void sinkDown(T[] array, int parentIndex,int length) {
        if(array==null) return;
        // temp保存父节点值，用于最后的赋值
        T temp = array[parentIndex];
        int child1Index = 2 * parentIndex + 1;
        int child2Index = 2 * parentIndex + 2;
        while (child1Index <length) {
            if(array[child2Index]!=null&&temp.compareTo(array[child1Index])>0&&temp.compareTo(array[child2Index])>0){
               break;
            }
            int replaceIndex =    child1Index;
            //替换大的那个元素
            if(array[child2Index]!=null&&array[child2Index].compareTo(array[child1Index])>0) {
                replaceIndex = child2Index;
            }
                array[parentIndex] = array[replaceIndex];

                parentIndex = replaceIndex;
                child1Index = 2 * parentIndex + 1;
                child2Index =  2 * parentIndex + 2;
        }
        array[parentIndex] = temp;
    }

    /**
     *                  10
     *                 / \
     *               8     9
     *              / \    / \
     *             6   7   1  2
     *            / \
     *           0   3
     * @param array
     * @return
     */
    public T[] buildMaxHeap(T[] array) {
        //从最后一个节点array.length-1的父节点（array.length-1-1）/2开始，直到根节点0，反复调整堆
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            sinkDown(array, i,array.length);
        }
        return array;
    }

    public T[] heapSort(T[] array){
        array = buildMaxHeap(array); //建堆
        T[] returnArray = array.clone();
        for(int i=array.length-1;i>=0;i--){
            returnArray[i] = array[0];
            array[0] = array[i];
            array[i] =null;
            sinkDown(array, 0,i);
        }
        return returnArray;
    }

    public static void main(String[] args) {
        MaxHeapComparable heap = new MaxHeapComparable();
        Integer[] array = new Integer[]{1, 3, 2, 6, 7, 9, 10, 0, 8};
        heap.printHeap(heap.heapSort(array));


    }


}
