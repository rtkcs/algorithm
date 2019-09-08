import edu.princeton.cs.algs4.Point2D;

public class KdTreeTest {

	public static void main(String[] args) {
		KdTree kdTree = new KdTree();
		kdTree.insert(new Point2D(0, 0));
		kdTree.insert(new Point2D(0, 0.1));
		kdTree.insert(new Point2D(0, 0.2));
		kdTree.insert(new Point2D(0.1, 0));
		kdTree.insert(new Point2D(0.1, 0.1));
		kdTree.insert(new Point2D(0.1, 0.2));
		kdTree.insert(new Point2D(0.2, 0));
		kdTree.insert(new Point2D(0.2, 0.1));
		kdTree.insert(new Point2D(0.2, 0.2));
		
		
		boolean b = kdTree.contains(new Point2D(0.2, 0.2));
		System.out.println("KdTree contains [0.2, 0.2] : " + b);
		
		b = kdTree.contains(new Point2D(0, 0));
		System.out.println("KdTree contains [0, 0] : " + b);
		
		b = kdTree.contains(new Point2D(0, 0.1));
		System.out.println("KdTree contains [0, 0.1] : " + b);
		
		b = kdTree.contains(new Point2D(0, 0.2));
		System.out.println("KdTree contains [0, 0.2] : " + b);
		
		b = kdTree.contains(new Point2D(0.2, 0.3));
		System.out.println("KdTree contains [0.2, 0.3] : " + b);
		
		b = kdTree.contains(new Point2D(-0.2, -0.3));
		System.out.println("KdTree contains [-0.2,.-0.3] : " + b);
		
		System.out.println();
//		System.out.println("In Order Traversal:");
//		kdTree.printInOrder(kdTree.new DrawTraverser());
		System.out.println("Pre Order Traversal:");
		kdTree.printPreOrder(kdTree.new DrawTraverser());
	}

}
