package eu.rtakacs.algorithms.binaryheap;

public class BinaryHeap<E> {
	
	int lastPosition;
	int size;
	E[] array;
	
	public BinaryHeap(int size) {
		this.size = size;
		this.lastPosition = 0;
		array = (E[]) new Object[size];
	}
	
	/**
	 * 
	 * @param node
	 * @return
	 */
	public boolean addNode(E node) {
		array[this.lastPosition++] = node;
		int child = this.lastPosition-1;
		trickleUp2(child);
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	public E removeNode() {
		
		E returnType = array[0];
		swap(0,--this.lastPosition);
		trickleDown(0);
		return returnType;
	}
	
	public E[] getSortedHeap() {
		
		do {
			swap(0, --this.lastPosition);
			trickleDown(0);
		} while(this.lastPosition > 0);
		
		E[] newArray = (E[])this.array;
		return newArray;
	}

	
	/**
	 * 
	 * @param child
	 */
	private void trickleUp(int child) {
		int parent = getParent(this.lastPosition);
		
		while(((Comparable)array[parent]).compareTo(array[child]) < 0 ) {
			swap(child, parent);
			child = parent;
			parent = getParent(child);
			if(parent < 0 ||child == 0) { 
				break;
			}
		}
	}
	
	private void trickleUp2(int position) {
		if(position == 0) { 
			return;
		}
		int parent = getParent(position);
//		if (parent < 1 ) return;
		if(((Comparable<E>)array[parent]).compareTo(array[position]) < 0 ) {
			swap(position, parent);
			trickleUp2(parent);
		}
	}
	

	
	/**
	 * 
	 * @param parent
	 */
	private void trickleDown(int parent) {
		
		int leftChild = 2 * parent + 1;
		int rightChild = 2 * parent + 2;
		
		if(leftChild == (this.lastPosition-2) && 
			((Comparable<E>)array[parent]).compareTo(array[leftChild]) < 0) {
			swap(parent, leftChild);
			return;
		}
		
		if(rightChild == (this.lastPosition-1) && 
				((Comparable<E>)array[parent]).compareTo(array[rightChild]) < 0) {
			swap(parent, rightChild);
			return;
		}
		
		if(leftChild >= this.lastPosition || rightChild >= this.lastPosition) {
			return;
		}
		
		if(((Comparable<E>)array[leftChild]).compareTo(array[rightChild]) > 0 && 
			((Comparable<E>)array[parent]).compareTo(array[leftChild]) < 0) {
			swap(parent, leftChild);
			trickleDown(leftChild);
		} else 
		if(((Comparable<E>)array[leftChild]).compareTo(array[rightChild]) < 0 && 
				((Comparable<E>)array[parent]).compareTo(array[rightChild]) < 0) {
			swap(parent, rightChild);
			trickleDown(rightChild);
		}
	}
	
	/**
	 * Return the index of the parent node from the child node.
	 * @param child int
	 * @return the int parent node
	 */
	private int getParent(int child) {
		return (int)Math.floor((child-1)/2);
	}
	
	/**
	 * Return the 2 indexes of the children nodes from the parent node.
	 * int[0] -  left chidl, int[1] - right child.
	 * 	 * 
	 * @param parent int
	 * @return int[] 2 child nodes in the array
	 */
	private int[] getChildren(int parent) {
		int[] children = new int[2];
		children[0] = 2 * parent + 1;
		children[1] = 2 * parent + 2;
		
		
		
		return children;
	}
	
	/**
	 * 
	 * @param from int
	 * @param to parent
	 */
	private void swap(int from, int to) {
		E temp = array[from];
		array[from] = array[to];
		array[to] = temp;
	}
}
