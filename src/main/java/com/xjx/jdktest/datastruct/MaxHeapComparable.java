package com.xjx.jdktest.datastruct;

import java.util.ArrayList;
import java.util.List;

/**
 * 最大堆
 * @param <T>
 */
public class MaxHeapComparable<T extends Comparable> {
    /**
     * 根节点也就是最大值
     */
    private List<T> elementData;
    private int capacity;
    private int size ;
    private static final int DEFAULT_CAPACITY = 8;

    public MaxHeapComparable() {
        this.capacity = DEFAULT_CAPACITY;
    }

    public MaxHeapComparable(int initailCapaticty) {
        this.capacity = initailCapaticty;
    }

    public boolean add (T e){
        try{
            ensureCapacity(size+1);
        }catch (Exception ex){ex.printStackTrace();}
        if(elementData==null){
            elementData = new ArrayList<>();
            for(int i=0;i<capacity;i++){
                elementData.add(null);
            }
        }
        elementData.set(size++,e);
        floatUp(elementData,size-1);
        return true;
    }

    /**
     * 删除元素只能从根节点删除
     */
    public void remove(){
        elementData.set(0,elementData.get(size-1));
        elementData.set(size-1,null);
        sinkDown(elementData,0,size-1);
        size--;
    }

    private void ensureCapacity(int size){
        if(size-capacity>0) grow();
    }

    private void grow(){
            int oldCapacity = elementData.size();
            int newCapacity = oldCapacity << 1;
            capacity = newCapacity;
            List<T> newData = new ArrayList<>(newCapacity);
        List<T> oldEmptyData = new ArrayList<>(oldCapacity);
        for(int i=0;i<oldCapacity;i++){
            oldEmptyData.add(null);
        }
            newData.addAll(elementData);
        newData.addAll(oldEmptyData);
            elementData = newData;
    }

    /**
     * 插入的时候，大根堆是大的数字上浮
     * 小根堆是小的数字上浮。
     * 逐层往上。
     */
    public void floatUp(List<T> list,  int k) {
        int childIndex = k;
        int parentIndex = (childIndex - 1) / 2;
        // temp保存插入的叶子节点值，用于最后的赋值
        T temp = list.get(k);
        while (childIndex > 0 && temp.compareTo(list.get(parentIndex)) > 0) {
            swap(childIndex,parentIndex,list);
            childIndex = parentIndex;
            parentIndex = (childIndex - 1) / 2;
        }
    }

    private void swap(int index1,int index2,List<T> list){
        T temp = list.get(index1);
        list.set(index1,list.get(index2));
        list.set(index2,temp);
    }

    public void printHeap(List<T> list) {
        list =  trimNullElementFromList(list);
        for (T n : list) {
            System.out.print(n+",");
        }
    }

    /**
     * 把小的往下沉，大的往上浮
     * @param list
     * @param parentIndex
     */
    public  void sinkDown(List<T> list, int parentIndex,int length) {
        if(list==null||list.size()==0) return;
        // temp保存父节点值，用于最后的赋值
        T temp = list.get(parentIndex);
        int child1Index = 2 * parentIndex + 1;
        int child2Index = 2 * parentIndex + 2;
        while (child1Index <length) {
            if(child2Index<length&&list.get(child2Index)!=null&&
                    temp.compareTo(list.get(child1Index))>0&&temp.compareTo(list.get(child2Index))>0){
               break;
            }
            int replaceIndex =    child1Index;
            //替换大的那个元素
            if(child2Index<length&&list.get(child2Index)!=null&&list.get(child2Index).compareTo(list.get(child1Index))>0) {
                replaceIndex = child2Index;
            }
            swap(parentIndex,replaceIndex,list);
            //list.set(parentIndex,list.get(replaceIndex));
                parentIndex = replaceIndex;
                child1Index = 2 * parentIndex + 1;
                child2Index =  2 * parentIndex + 2;
        }
        //list.set(parentIndex,temp);
    }

    /**
     *                  10
     *                 / \
     *               8     9
     *              / \    / \
     *             6   7   1  2
     *            / \
     *           0   3
     * @param list
     * @return
     */
    public List<T> buildMaxHeap(List<T> list) {
        //从最后一个节点array.length-1的父节点（array.length-1-1）/2开始，直到根节点0，反复调整堆
        for (int i = (list.size() - 2) / 2; i >= 0; i--) {
            sinkDown(list, i,list.size());
        }
        return list;
    }

    /**
     *  delete null from list
     * @param list
     * @return
     */
    private List<T> trimNullElementFromList(List<T> list){
        List<T> returnList = new ArrayList<>();
      for(T e:list){
        if(e!=null){
            returnList.add(e);
        }
      }
      return  returnList;
    }

    public List<T> heapSort(){
        elementData = trimNullElementFromList(elementData);
        elementData = buildMaxHeap(elementData); //建堆
        List<T>  returnList = new ArrayList<>();
        returnList.addAll(elementData);
        for(int i=elementData.size()-1;i>=0;i--){
            returnList.set(i,elementData.get(0));
            elementData.set(0,elementData.get(i));
            elementData.set(i,null);
            sinkDown(elementData, 0,i);
        }
        return returnList;
    }

    public List<T> heapSort(List<T> list){
        list = trimNullElementFromList(list);
        list = buildMaxHeap(list); //建堆
        List<T>  returnList = new ArrayList<>();
        returnList.addAll(list);
        for(int i=list.size()-1;i>=0;i--){
            returnList.set(i,list.get(0));
            list.set(0,list.get(i));
            list.set(i,null);
            sinkDown(list, 0,i);
        }
        return returnList;
    }

    public static void main(String[] args) {
        MaxHeapComparable heap = new MaxHeapComparable();
//        Integer[] array = new Integer[]{1, 3, 2, 6, 7, 9, 10, 0, 8};
//        List<Integer> list = Arrays.asList(array);
//        heap.printHeap(heap.heapSort(list));
        heap.add(new Integer(1));
        heap.add(new Integer(4));
        heap.add(new Integer(4));
        heap.add(new Integer(3));
        heap.add(new Integer(2));
        heap.add(new Integer(6));
        heap.add(new Integer(7));
        heap.add(new Integer(9));
        heap.add(new Integer(11));
        heap.add(new Integer(0));
        heap.add(new Integer(8));
        heap.remove();
        heap.printHeap(heap.elementData);
    }


}
