package eu.rtakacs.algorithms.list;

import java.util.Iterator;

/**
 * Linked List
 * https://www.youtube.com/watch?v=-EDUhPg6930&list=PLpPXw4zFa0uKKhaSz87IowJnOTzh9tiBk&index=11
 * Boundary conditions:
 * <ul>
 * <li>Empty data structure</li>
 * <li>Single element in the data structure</li>
 * <li>Adding / removing at the beginning of data structure</li>
 * <li>adding / removing at the end of a data structure</li>
 * <li>Working in the middle</li>
 * </ul>
 * @author TAK10892
 *
 * @param <V>
 */
public class LinkedList<V> implements Iterable<V> {
		
	private Node<V> head;
	private Node<V> tail;
	private int currentSize;
	
	class Node<gi>/* implements Comparable<Node<V>> */ {

		private V v;
		private Node<V> next;
		private Node<V> previous;
		
		Node(V v){
			this.v = v;
			this.next = null;
			this.previous = null;
		}
		
		public V getV() {
			return v;
		}

		public void setV(V v) {
			this.v = v;
		}

		public Node<V> getNext() {
			return next;
		}

		public void setNext(Node<V> next) {
			this.next = next;
		}

		public Node<V> getPrevious() {
			return previous;
		}

		public void setPrevious(Node<V> previous) {
			this.previous = previous;
		}

		@Override
		public String toString() {
			return "Node [v=" + v + "]";
		}
		/*
		public int compareTo(LinkedList<V>.Node<V> o) {
			return ((Comparable<V>)this.v).compareTo(o.getV());
		}
		*/
	}
	
	LinkedList(){
		this.head = null;
		this.currentSize = 0;
	}
	
	public void addFirst(V v) {
		Node<V> newFirst = new Node<V>(v);
		
		if(this.head==null) {
			this.head = newFirst;
			this.tail = newFirst;
			++this.currentSize;
			return;
		}
		
		newFirst.setNext(this.head);
		this.head.setPrevious(newFirst);
		this.head = newFirst;
		++this.currentSize;
	}
	
	public void addLast(V v) {
		
		if(this.tail==null) {
			this.addFirst(v);
			return;
		}
		
		Node<V> newLast = new Node<V>(v);
		this.tail.setNext(newLast);
		newLast.setPrevious(this.tail);
		this.tail = newLast;
		++this.currentSize;
		
	}
	
	public Node<V> removeFirst() {
		// empty list
		if(this.head==null) {
			return null;
		}
		// 1 item
		if(this.head.equals(this.tail)) {
			Node<V> removedNode = this.head;
			this.head = null;
			this.tail = null;
			--this.currentSize;
			return removedNode;
		}
		Node<V> removedNode = this.head;
		
		this.head = this.head.getNext();
		this.head.setPrevious(null);
		--this.currentSize;
		
		removedNode.setNext(null);
		removedNode.setPrevious(null);
		return removedNode;
	}
	
	public Node<V> removeLast() {
		// empty list
		if(this.tail==null) {
			return null;
		}
		// 1 Node 
		if(this.tail.equals(this.head)) {
			Node<V> removedNode = this.tail;
			this.head = null;
			this.tail = null;
			--this.currentSize;
			return removedNode;
		}
		
		Node<V> removedNode = this.tail;
		this.tail = this.tail.getPrevious();
		this.tail.setNext(null);
		
		--this.currentSize;
		
		removedNode.setNext(null);
		removedNode.setPrevious(null);
		return removedNode;
	}
	
	public boolean isEmpty() {
		return this.currentSize==0;
	}
	
	public int size() {
		return this.currentSize;
	}

	public Iterator<V> iterator() {
		
		return new ListIterator();
	}
	
	class ListIterator implements Iterator<V>{
		
		Node<V> currentNode;
		
		ListIterator(){
			this.currentNode = LinkedList.this.head;
		}
		
		public boolean hasNext() {
			return this.currentNode!=null;
		}

		public V next() {
			V value = this.currentNode.getV();
			this.currentNode = this.currentNode.getNext();
			return value;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}



}
