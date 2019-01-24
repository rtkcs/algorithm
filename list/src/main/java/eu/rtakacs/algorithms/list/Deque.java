package eu.rtakacs.algorithms.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

import eu.rtakacs.algorithms.list.LinkedList.Node;

public class Deque<Item> implements Iterable<Item> {
	
	private Node<Item> head;
	private Node<Item> tail;
	private int currentSize;
	
	class Node<Item> implements Comparable<Node<Item>> {

		private Item item;
		private Node<Item> next;
		private Node<Item> previous;
		
		Node(Item i){
			this.item = i;
			this.next = null;
			this.previous = null;
		}
		
		public Item getItem() {
			return item;
		}

		public void setItem(Item i) {
			this.item = i;
		}

		public Node<Item> getNext() {
			return next;
		}

		public void setNext(Node<Item> next) {
			this.next = next;
		}

		public Node<Item> getPrevious() {
			return previous;
		}

		public void setPrevious(Node<Item> previous) {
			this.previous = previous;
		}

		@Override
		public String toString() {
			return "Node [v=" + item + "]";
		}

		@Override
		public int compareTo(Deque<Item>.Node<Item> o) {
//			return 0;
			return ((Comparable<Item>)this.item).compareTo(o.getItem());
		}
	}
	
	class DequeIterator implements Iterator<Item>{
		
		Node<Item> currentNode;
		
		DequeIterator(){
			this.currentNode = Deque.this.head;
		}
		
		public boolean hasNext() {
			return this.currentNode!=null;
		}

		public Item next() {
			if(hasNext()) {
				Item value = this.currentNode.getItem();
				this.currentNode = this.currentNode.getNext();
				return value;
			} else {
				throw new NoSuchElementException(); 
			}
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	/**
	 * Construct an empty deque.
	 */
	public Deque() {
		this.head = null;
		this.tail = null;
		this.currentSize = 0;
	}
	
	/**
	 * is the deque empty?
	 * @return boolean
	 */
	public boolean isEmpty() {
		return this.currentSize==0;
	} 
	
	/**
	 * Return the number of items on the deque.
	 * @return int
	 */
	public int size() {
		return this.currentSize;
	} 
	
	/**
	 * Add the item to the front.
	 * @param item
	 */
	public void addFirst(Item item) {
		if(item==null) {
			throw new IllegalArgumentException("Can not add null.");
		}
		Node<Item> newFirst = new Node<Item>(item);
		
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
	
	/**
	 * Add the item to the end.
	 * @param item
	 */
	public void addLast(Item item) {
		if(item==null) {
			throw new IllegalArgumentException("Can not add null.");
		}
		
		if(this.tail==null) {
			this.addFirst(item);
			return;
		}
		
		Node<Item> newLast = new Node<Item>(item);
		this.tail.setNext(newLast);
		newLast.setPrevious(this.tail);
		this.tail = newLast;
		++this.currentSize;
	} 
	
	/**
	 * Remove and return the item from the front.
	 * @return
	 */
	public Item removeFirst() {
		
		
		// empty list
		if(this.head==null) {
//			return null;
			throw new NoSuchElementException("Deque is empty, can not remove element.");
		}
		// 1 item
		if(this.head.equals(this.tail)) {
			Node<Item> removedNode = this.head;
			this.head = null;
			this.tail = null;
			--this.currentSize;
			return removedNode.getItem();
		}
		Node<Item> removedNode = this.head;
		
		this.head = this.head.getNext();
		this.head.setPrevious(null);
		--this.currentSize;
		
		removedNode.setNext(null);
		removedNode.setPrevious(null);
		return removedNode.getItem();
	}
	
	/**
	 * Remove and return the item from the end.
	 * @return
	 */
	public Item removeLast() {
		// empty list
		if(this.tail==null) {
//			return null;
			throw new NoSuchElementException("Deque is empty, can not remove element.");
		}
		// 1 Node 
		if(this.tail.equals(this.head)) {
			Node<Item> removedNode = this.tail;
			this.head = null;
			this.tail = null;
			--this.currentSize;
			return removedNode.getItem();
		}
		
		Node<Item> removedNode = this.tail;
		this.tail = this.tail.getPrevious();
		this.tail.setNext(null);
		
		--this.currentSize;
		
		removedNode.setNext(null);
		removedNode.setPrevious(null);
		return removedNode.getItem();		
	}
	
	/**
	 * Return an iterator over items in order from front to end.
	 */
	public Iterator<Item> iterator(){
		return new DequeIterator();
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
