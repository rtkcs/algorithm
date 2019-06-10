package eu.rtakacs.algorithms.trees;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TreeTest {

	@Test
	void testAdd() {
		Tree<Integer> tree = new Tree<>();
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		
		System.out.println( "contains 1: " + tree.contains(1));
		System.out.println( "contains 2: " + tree.contains(2));
		System.out.println( "contains 3: " + tree.contains(3));
		System.out.println( "contains 4: " + tree.contains(4));
		System.out.println( "contains 5: " + tree.contains(5));
		System.out.println( "contains 0: " + tree.contains(0));
		System.out.println( "contains 6: " + tree.contains(6));
	}

	@Test
	void testContains() {
		Tree<Foo> fooTree = new Tree<>();
		fooTree.add(new Foo(1));
		fooTree.add(new Foo(2));
		fooTree.add(new Foo(3));
		
		System.out.println("contains Foo(1)" + fooTree.contains(new Foo(1)));
	}

	@Test
	void testRemoveNode() {
		Tree<Foo> tree = new Tree<>();
		tree.add(new Foo(6));
		tree.add(new Foo(4));
		tree.add(new Foo(8));
		tree.add(new Foo(2));
		tree.add(new Foo(10));
		tree.add(new Foo(5));
		tree.add(new Foo(7));
		
		Foo foo1 = tree.removeNode(new Foo(2));
		System.out.println("removed node Foo(2) " + foo1.i);
		
		Foo foo4 = tree.removeNode(new Foo(4));
		System.out.println("removed node Foo(4) " + foo4.i);
		
		Foo foo6 = tree.removeNode(new Foo(6));
		System.out.println("remove node Foo(6) " + foo6.i);
	}

}
