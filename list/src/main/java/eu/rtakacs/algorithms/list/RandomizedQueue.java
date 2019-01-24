package eu.rtakacs.algorithms.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	
//	private Node<Item> head;
//	private Node<Item> tail;
	private int filledSize;
	private int currentPosition;
	private Item[] items;
	
	class RandomizedQueueIterator implements Iterator<Item> {
		
		RandomizedQueueIterator(){
			
		}
		
		int currentPosition = 0;
		@Override
		public boolean hasNext() {
			
			return RandomizedQueue.this.filledSize <= currentPosition;
		}

		@Override
		public Item next() {
			if(hasNext()) {
				do {
					
				}while(RandomizedQueue.this.item[this.currentPosition++]!=null);
			} else {
				throw new NoSuchElementException(); 
			}
			
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	/**
	 * Construct an empty randomized queue.
	 */
	public RandomizedQueue() {
		items = (Item[])new Object[10];

	} 
	/**
	 * Is the randomized queue empty?
	 * @return boolean
	 */
	public boolean isEmpty() {
		return this.filledSize==0;
	}           
	
	/**
	 * Return the number of items on the randomized queue.
	 * @return int
	 */
	public int size() {
		return this.filledSize;
	}   
	/**
	 * Add the item.
	 * @param item
	 */
	public void enqueue(Item item) {
		resize();
		this.items[this.currentPosition++] = item;
	}
	
	/**
	 * Remove and return a random item.
	 * @return
	 */
	public Item dequeue() {
		int randomPosition;
		do {
			randomPosition = StdRandom.uniform(this.currentPosition);
		}while(	this.items[randomPosition]!=null);
		this.filledSize--;
		Item returnedItem = this.items[randomPosition];
		this.items[randomPosition] = null;
		resize();
		return returnedItem; 
	}
	
	/**
	 * Return a random item (but do not remove it).
	 * @return
	 */
	public Item sample() {
		int randomPosition;
		do {
			randomPosition = StdRandom.uniform(this.currentPosition);
		}while(	this.items[randomPosition]!=null);

		return this.items[randomPosition]; 
	} 
	/**
	 * Return an independent iterator over items in random order.
	 */
	public Iterator<Item> iterator(){
		
	}

	/**
	 *  unit testing (optional)
	 */
	public static void main(String[] args) {
		
	}
	
	private void resize() {
		if (this.filledSize/this.items.length < 0.75 && this.filledSize/this.items.length > 0.25) {
			return;
		}
		int newItemsLength = 0;
		if(this.filledSize/this.items.length >= 0.75) {
			newItemsLength=this.items.length*2;
		}
		
		if(this.filledSize/this.items.length <= 0.25) {
			newItemsLength=this.items.length/2;
		}
		
		int currentPositionNewItem = 0;
		Item[] newItems = (Item[]) new Object[newItemsLength];
		for (Item item : this.items) {
			if(item == null) continue;
			
			newItems[currentPositionNewItem] = item;
			currentPositionNewItem++;
		}
		this.items= newItems;
		this.currentPosition = currentPositionNewItem;
	}
}
