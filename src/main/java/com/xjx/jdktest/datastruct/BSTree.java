package com.xjx.jdktest.datastruct;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 *
 */
public class BSTree {

	private Node root ;

	private static final String SUCCESSOR="successor";

	private static final String SUCCESSOR_PARENT="parent";

	public BSTree() { //初始化为一个空树
		root = null;
	}

	/**
	 *   遍历BSTree ，循环终止条件是： 被比较的元素的index和index相等
	 *   或者被比较的元素已经是叶子节点
	 * @param inputIndex
	 * @return
	 */
	public Node find(int inputIndex){
		if(root ==null) return null;
		 return  getRecursive(root,inputIndex);
		/*if(root ==null) return null;
		Node toBeCompared = root;
		while(!(toBeCompared.index == inputIndex ||isLeafNode(toBeCompared))){
			if (inputIndex < toBeCompared.index) {
				toBeCompared = toBeCompared.leftNode;
			} else {   //向右子树查询
				toBeCompared = toBeCompared.rightNode;
			}
		}
		if(toBeCompared.index == inputIndex){
			return toBeCompared;
		}else{
			return null;
		}*/
	}



	private  Node getRecursive(Node tree,int inputIndex){
		if(inputIndex<tree.index){
			return getRecursive(tree.leftNode,inputIndex);
		}else if(inputIndex>tree.index){
			return  getRecursive(tree.rightNode,inputIndex);
		}else{
			return  tree;
		}
	}
 public Node getRoot(){
		return root;
 }


	//根据key删除一个节点
	public boolean delete(int index){
		Node current = root ;
		Node parent = root ;
		boolean isLeftChild = true;
		while (current.index != index) {
			parent = current;
			if (index <= current.index) {
				isLeftChild = true;
				current = current.leftNode;
			} else {
				isLeftChild = false ;
				current = current.rightNode;
			}
			if (current == null) {
				return false;
			}
		}

		//如果没有子节点，直接删除即可
		if (isLeafNode(current)) {
			if (current == root) { //如果删除的节点为root
				root = null; //空树
			}
			else if(isLeftChild){ //如果删除左子节点
				parent.leftNode = null;
			}
			else { //如果删除右子节点
				parent.rightNode = null;
			}
		}

		//如果没有右节点，用左子树代替当前节点即可。
		else if(current.rightNode == null){
			if (current == root) {
				root = current.leftNode;
			}
			else if (isLeftChild) {
				parent.leftNode = current.leftNode;
			}
			else {
				parent.rightNode = current.leftNode;
			}
		}


		//如果没有左节点，直接用右子树代替当前删除的节点
		else if (current.leftNode == null) {
			if (current == root) {
				root = current.rightNode;
			}
			else if (isLeftChild) {
				parent.leftNode = current.rightNode;
			}
			else {
				parent.rightNode = current.rightNode;
			}
		}

		else{
			//如果左右节点都有，则从右子树种寻找最小值作为继承者
			//parent,current,leftChild,rightChild z指针都要变化
			Node leftChild = current.leftNode;
			Node rightChild = current.rightNode;
			Map map = getSuccessor(current);
			Node successor = (Node) map.get(SUCCESSOR);
			Node successorTreeMaxNode = getSuccessorTreeMaxNode(successor);
			Node successorParent = (Node) map.get(SUCCESSOR_PARENT);
			successorParent.leftNode =null;
			if(isLeafNode(successor)){
				if(isLeftChild){
					parent.leftNode = successor;
					successorTreeMaxNode.leftNode = leftChild;
					successorTreeMaxNode.rightNode = rightChild;
				}else{
					parent.rightNode = successor;
					successorTreeMaxNode.leftNode = leftChild;
					successorTreeMaxNode.rightNode = rightChild;
				}
			}else{
				if(isLeftChild){
					parent.leftNode = successor;
					successorTreeMaxNode.leftNode = leftChild;
					successorTreeMaxNode.rightNode = rightChild;
				}else{
					parent.rightNode = successor;
					successorTreeMaxNode.leftNode = leftChild;
					successorTreeMaxNode.rightNode = rightChild;
				}
			}
		}

	return true;





	}

	private Node getSuccessorTreeMaxNode(Node successor){
		Node maxNode = successor;
		while(successor.rightNode!=null&&successor.rightNode.index>maxNode.index){
			maxNode = successor.rightNode;
		}
		return maxNode;
	}

	/**
	 * 如果左子树为空，就是这棵树中的最小值,返回successor的父节点 ，还有successor这棵树
	 * @param delNode
	 * @return
	 */
	private Map<String,Node> getSuccessor(Node delNode){

		//从右子树种找到最小节点，所以从右节点开始找
		Map<String,Node> result = new HashMap<>();
		Node minNode = delNode.rightNode;
		Node parent = delNode ;
		//循环终止条件是是叶子节点，或右节点是叶子节点
		while( minNode.leftNode !=null){
			parent =minNode;
			minNode = minNode.leftNode;
		}
		result.put(SUCCESSOR_PARENT,parent);
		result.put(SUCCESSOR,minNode);
		return result;
	}

	private boolean isLeafNode(Node node){
		return (node.leftNode==null&&node.rightNode==null);
	}


	/**
	 * 如果小于当前值，左节点为空，就挂上去，
	 * 或者大于当前值，右节点为空，就挂上去
	 * @param index
	 * @param element
	 * @return
	 */
	public boolean insert(int index,Object element){
		if(root ==null) {root = new Node(null,null,index,element);return true;}
		Node newNode = new Node(null,null,index,element);
		Node toBeCompared = root ;
		while(true){
			if (index <= toBeCompared.index && toBeCompared.leftNode!=null ) {
				toBeCompared = toBeCompared.leftNode;
			} else if(index <= toBeCompared.index && toBeCompared.leftNode==null ) {   //向右子树查询
				toBeCompared.leftNode = newNode;
				return true;
			}else if(index > toBeCompared.index && toBeCompared.rightNode!=null ) {   //向右子树查询
				toBeCompared = toBeCompared.rightNode;
			}else{
				toBeCompared.rightNode = newNode;
				return true;
			}
		}

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

	//打印树
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
					System.out.print(temp.index);
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

	//遍历  前序后序中序，的区别是父节点先打印，还是中间打印，还是后面打印。左右打印顺序不变
	//前序遍历，从根节点开始遍历。
	public void preOrder(Node localRoot){
		if (localRoot != null) {
			System.out.print(localRoot.index+"  " );
			preOrder(localRoot.leftNode);
			preOrder(localRoot.rightNode);
		}
	}

	//遍历
	//中序遍历，从根节点开始遍历。
	public void inOrder(Node localRoot){
		if (localRoot != null) {
			inOrder(localRoot.leftNode);
			System.out.print(localRoot.index+"  " );
			inOrder(localRoot.rightNode);
		}
	}

	//遍历
	//后序遍历，从根节点开始遍历。
	public void backOrder(Node localRoot){
		if (localRoot != null) {
			backOrder(localRoot.leftNode);
			backOrder(localRoot.rightNode);
			System.out.print(localRoot.index+"  " );
		}
	}


	public class Node{
		public Node leftNode;
		public Node rightNode;
		public long index;
		public Object element;

		public Node(Node leftNode, Node rightNode, long index, Object element) {
			this.leftNode = leftNode;
			this.rightNode = rightNode;
			this.index = index;
			this.element = element;
		}

		public void displayNode(){
			System.out.println("{");
			System.out.println(index);
			System.out.println(",  ");
			System.out.println(element.toString());
			System.out.println("} ");
		}

	}
}
