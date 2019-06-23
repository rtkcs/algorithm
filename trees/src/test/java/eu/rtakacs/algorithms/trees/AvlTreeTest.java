package eu.rtakacs.algorithms.trees;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AvlTreeTest {

	@Test
	void testAddNode() {
		AvlTree<Integer> avl = new AvlTree<>();
		avl.add(1);
		avl.add(2);
		avl.add(3);
		avl.add(4);
		avl.add(5);
		avl.add(6);
		avl.add(7);
		
		avl.add(10);
		avl.add(9);
	}

}
