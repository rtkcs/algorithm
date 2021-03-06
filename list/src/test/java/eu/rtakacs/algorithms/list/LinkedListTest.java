/**
 * 
 */
package eu.rtakacs.algorithms.list;

import java.util.Iterator;

import junit.framework.TestCase;

/**
 * @author rober
 *
 */
public class LinkedListTest extends TestCase {
	

	/**
	 * Test method for {@link eu.rtakacs.algorithms.list.LinkedList#addFirst(java.lang.Object)}.
	 */
	public void testAddFirst() {
		
		LinkedList<Integer> list = new LinkedList<Integer>();
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
		LinkedList<Integer> list = new LinkedList<Integer>();
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
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.removeFirst();
	}

	/**
	 * Test method for {@link eu.rtakacs.algorithms.list.LinkedList#removeLast()}.
	 */
	public void testRemoveLast() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.removeLast();
		
	}

	/**
	 * Test method for {@link eu.rtakacs.algorithms.list.LinkedList#isEmpty()}.
	 */
	public void testIsEmpty() {
		LinkedList<Integer> list = new LinkedList<Integer>();
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
		LinkedList<Integer> list = new LinkedList<>();
		list.addFirst(1);
		assertEquals(1, list.size());
		list.addLast(2);
		assertEquals(2, list.size());
	}

	/**
	 * Test method for {@link eu.rtakacs.algorithms.list.LinkedList#iterator()}.
	 */
	public void testIterator() {
		
		LinkedList<Integer> list = new LinkedList<Integer>();
		
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
