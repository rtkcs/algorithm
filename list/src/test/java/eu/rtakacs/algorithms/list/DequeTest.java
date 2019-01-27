/**
 * 
 */
package eu.rtakacs.algorithms.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

import junit.framework.TestCase;

/**
 * @author Robert Takacs
 *
 */
public class DequeTest extends TestCase {
	

	/**
	 * Test method for {@link eu.rtakacs.algorithms.list.LinkedList#addFirst(java.lang.Object)}.
	 */
	public void testAddFirst() {
		
		Deque<Integer> list = new Deque<Integer>();
		for(int i=0;i<100;i++) {
			list.addFirst(i);
		}
		assertEquals(100, list.size());
		
		for(int i=0;i<100;i++) {
			list.removeFirst();
		}
		assertEquals(0, list.size());
	}

	/**
	 * Test method for {@link eu.rtakacs.algorithms.list.LinkedList#addLast(java.lang.Object)}.
	 */
	public void testAddLast() {
		Deque<Integer> list = new Deque<Integer>();
		for(int i=0;i<100;i++) {
			list.addLast(i);
		}
		assertEquals(100, list.size());
		
		for(int i=0;i<100;i++) {
			list.removeLast();
		}
		assertEquals(0, list.size());
	}

	/**
	 * Test method for {@link eu.rtakacs.algorithms.list.LinkedList#removeFirst()}.
	 */
	public void testRemoveFirst() {
		Deque<Integer> list = new Deque<Integer>();
		try {
			list.removeFirst();
			fail("Missing exception");
		} catch(NoSuchElementException e) {
			assertEquals("Deque is empty, can not remove element.", e.getMessage());
		}
	}

	/**
	 * Test method for {@link eu.rtakacs.algorithms.list.LinkedList#removeLast()}.
	 */
	public void testRemoveLast() {
		Deque<Integer> list = new Deque<Integer>();
		try {		
			list.removeLast();
			fail("Missing exception");
		} catch(NoSuchElementException e) {
			assertEquals("Deque is empty, can not remove element.", e.getMessage());
		}
	}

	/**
	 * Test method for {@link eu.rtakacs.algorithms.list.LinkedList#isEmpty()}.
	 */
	public void testIsEmpty() {
		Deque<Integer> list = new Deque<Integer>();
		assertEquals(true, list.isEmpty());
		
		for(int i=0;i<100;i++) {
			list.addLast(i);
		}
		assertEquals(false, list.isEmpty());
		
		for(int i=0;i<100;i++) {
			list.removeLast();
		}
		assertEquals(true, list.isEmpty());
	}

	/**
	 * Test method for {@link eu.rtakacs.algorithms.list.LinkedList#size()}.
	 */
	public void testSize() {
		Deque<Integer> list = new Deque<>();
		list.addFirst(1);
		assertEquals(1, list.size());
		list.addLast(2);
		assertEquals(2, list.size());
	}

	/**
	 * Test method for {@link eu.rtakacs.algorithms.list.LinkedList#iterator()}.
	 */
	public void testIterator() {
		
		Deque<Integer> list = new Deque<Integer>();
		
		for(int i=0;i<100;i++) {
			list.addLast(i);
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
