

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	
//	private Node<Item> head;
//	private Node<Item> tail;
	private int filledSize;
	private int currentPosition;
	private Item[] items;
	
	private class RandomizedQueueIterator implements Iterator<Item> {
		
		private int currentPosition;
		private final Item[] itemsIterator;
		
		RandomizedQueueIterator(){
			this.currentPosition = 0;
			itemsIterator = (Item[])new Object[RandomizedQueue.this.filledSize];
			
			for(Item item : RandomizedQueue.this.items) {
				if(item==null) {
					continue;
				}
				itemsIterator[this.currentPosition++] = item;
			}
			StdRandom.shuffle(this.itemsIterator);
			this.currentPosition = 0;
		}
		
		@Override
		public boolean hasNext() {
			return  currentPosition < this.itemsIterator.length;
		}

		@Override
		public Item next() {
			Item returningItem;
			if(hasNext()) {
				do {
					returningItem = this.itemsIterator[this.currentPosition++];
				}while(returningItem==null);
			} else {
				throw new NoSuchElementException(); 
			}
			return returningItem;
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
		if(item == null) {
			throw new IllegalArgumentException("null element can not be enqued");
		}
		resize();
		this.items[this.currentPosition++] = item;
		this.filledSize++;
	}
	
	/**
	 * Remove and return a random item.
	 * @return
	 */
	public Item dequeue() {
		if(this.filledSize == 0) {
			throw new NoSuchElementException("RandomizeQueue is empty, can not remove element.");
		}
		
		int randomPosition;
		do {
			randomPosition = StdRandom.uniform(this.currentPosition);
		}while(	this.items[randomPosition]==null);
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
		if(this.filledSize == 0) {
			throw new NoSuchElementException("null element can not be enqued");
		}
		
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
		return new RandomizedQueueIterator();
	}

	/**
	 *  unit testing (optional)
	 */
	public static void main(String[] args) {
		
	}
	
	private void resize() {
		double fillFactor = (double)this.filledSize/(double)this.items.length;
		if (this.filledSize==0 || (fillFactor < 0.75 /*&& this.filledSize/this.items.length > 0.25*/)) {
			return;
		}
		int newItemsLength = 0;
		if(fillFactor >= 0.75) {
			newItemsLength=this.items.length*2;
		}
		
		if(fillFactor <= 0.25) {
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
