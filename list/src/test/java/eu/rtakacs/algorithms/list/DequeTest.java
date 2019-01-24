/**
 * 
 */
package eu.rtakacs.algorithms.list;

import java.util.Iterator;

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
		list.removeFirst();
	}

	/**
	 * Test method for {@link eu.rtakacs.algorithms.list.LinkedList#removeLast()}.
	 */
	public void testRemoveLast() {
		Deque<Integer> list = new Deque<Integer>();
		list.removeLast();
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
		fail("Not yet implemented");
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
