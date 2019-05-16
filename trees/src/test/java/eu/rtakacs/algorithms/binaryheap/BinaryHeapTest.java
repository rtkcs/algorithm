package eu.rtakacs.algorithms.binaryheap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BinaryHeapTest {

	@Test
	void test1() {
		BinaryHeap<Integer> binaryHeap = new BinaryHeap<>(15);
		System.out.println("Added these integers: ");
		for(int i=0; i< 15; i++) {
			int num = (int)(Math.random() * 100);
			System.out.println(num);
			binaryHeap.addNode(num);
		}
		
		System.out.println();
		System.out.println("Removed these integers: ");
		for(int i=0; i<15; i++) {
			System.out.println(binaryHeap.removeNode());
			
		}
		
	}
	
	@Test
	void test2() {
		BinaryHeap<Integer> binaryHeap = new BinaryHeap<>(15);
		System.out.println("Added these integers: ");
		for(int i=0; i< 15; i++) {
			int num = (int)(Math.random() * 100);
			System.out.println(num);
			binaryHeap.addNode(num);
		}
		
		System.out.println();
		System.out.println("Testing heap sort: ");
		Object[] sorted = binaryHeap.getSortedHeap();
		for(int x=0; x<sorted.length; x++) {
			System.out.println(sorted[x]);
		}
		
		
	}

}
