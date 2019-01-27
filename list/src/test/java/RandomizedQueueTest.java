/**
 * 
 */


import java.util.Iterator;
import java.util.NoSuchElementException;

import junit.framework.TestCase;

/**
 * @author Robert Takacs
 *
 */
public class RandomizedQueueTest extends TestCase {
	

	/**
	 * Test method for {@link eu.rtakacs.algorithms.list.RandomizedQueue#addFirst(java.lang.Object)}.
	 */
	public void testEnqueue() {
		
		RandomizedQueue<Integer> list = new RandomizedQueue<Integer>();
		for(int i=0;i<100;i++) {
			list.enqueue(i);
		}
		assertEquals(100, list.size());
		
		for(int i=0;i<100;i++) {
			list.dequeue();
		}
		assertEquals(0, list.size());
	}

	/**
	 * Test method for {@link eu.rtakacs.algorithms.list.RandomizedQueue#addLast(java.lang.Object)}.
	 */
	public void testDequeue() {
		RandomizedQueue<Integer> list = new RandomizedQueue<Integer>();
		for(int i=0;i<100;i++) {
			list.enqueue(i);
		}
		assertEquals(100, list.size());
		
		for(int i=0;i<100;i++) {
			list.dequeue();
		}
		assertEquals(0, list.size());
	}

	/**
	 * Test method for {@link eu.rtakacs.algorithms.list.RandomizedQueuet#dequeue()}.
	 */
	public void testDequeueNoElement() {
		RandomizedQueue<Integer> list = new RandomizedQueue<Integer>();
		try {
			list.dequeue();
			fail("Missing exception");
		} catch(NoSuchElementException e) {
			assertEquals("RandomizeQueue is empty, can not remove element.", e.getMessage());
		}
	}

	/**
	 * Test method for {@link eu.rtakacs.algorithms.list.RandomizedQueue#isEmpty()}.
	 */
	public void testIsEmpty() {
		RandomizedQueue<Integer> list = new RandomizedQueue<Integer>();
		assertEquals(true, list.isEmpty());
		
		for(int i=0;i<100;i++) {
			list.enqueue(i);
		}
		assertEquals(false, list.isEmpty());
		
		for(int i=0;i<100;i++) {
			list.dequeue();
		}
		assertEquals(true, list.isEmpty());
	}

	/**
	 * Test method for {@link eu.rtakacs.algorithms.list.RandomizedQueue#size()}.
	 */
	public void testSize() {
		RandomizedQueue<Integer> list = new RandomizedQueue<>();
		list.enqueue(1);
		assertEquals(1, list.size());
		list.enqueue(2);
		assertEquals(2, list.size());
	}

	/**
	 * Test method for {@link eu.rtakacs.algorithms.list.RandomizedQueue#iterator()}.
	 */
	public void testIterator() {
		
		RandomizedQueue<Integer> list = new RandomizedQueue<Integer>();
		
		for(int i=0;i<100;i++) {
			list.enqueue(i);
		}
		
		int size = 0;
		Iterator<Integer> iterator = list.iterator();
		while(iterator.hasNext()) {
			iterator.next();
			++size;
		}
		assertEquals(100, size);
	}

}
