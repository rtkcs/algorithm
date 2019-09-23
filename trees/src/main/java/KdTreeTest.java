import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTreeTest {

	public static void main(String[] args) {
//		test1();
		test2();
	}
	
	private static void test1() {
		KdTree kdTree = new KdTree();
		kdTree.insert(new Point2D(0.5, 0.5));
		kdTree.insert(new Point2D(0.4, 0.4));
		kdTree.insert(new Point2D(0.6, 0.6));
		kdTree.insert(new Point2D(0.4, 0.5));
		kdTree.insert(new Point2D(0.4, 0.6));
		kdTree.insert(new Point2D(0.5, 0.4));
		kdTree.insert(new Point2D(0.5, 0.6));
		kdTree.insert(new Point2D(0.6, 0.4));
		kdTree.insert(new Point2D(0.6, 0.5));
		
		
		boolean b = kdTree.contains(new Point2D(0.6, 0.6));
		System.out.println("KdTree contains [0.6, 0.6] : " + b);
		
		b = kdTree.contains(new Point2D(0.4, 0.4));
		System.out.println("KdTree contains [0.4, 0.4] : " + b);
		
		b = kdTree.contains(new Point2D(0.4, 0.5));
		System.out.println("KdTree contains [0.4, 0.5] : " + b);
		
		b = kdTree.contains(new Point2D(0.4, 0.6));
		System.out.println("KdTree contains [0.4, 0.6] : " + b);
		
		b = kdTree.contains(new Point2D(0.6, 0.7));
		System.out.println("KdTree contains [0.6, 0.7] : " + b);
		
		b = kdTree.contains(new Point2D(-0.6, -0.7));
		System.out.println("KdTree contains [-0.6,.-0.7] : " + b);
		
		System.out.println();
//		System.out.println("In Order Traversal:");
//		kdTree.printInOrder(kdTree.new DrawTraverser());
		System.out.println("Pre Order Traversal:");
//		kdTree.printPreOrder(kdTree.new DrawTraverser());
		kdTree.draw();
	}
	
	private static void test2() {
		KdTree kdTree = new KdTree();
		kdTree.insert(new Point2D(0.2, 0.3));
		kdTree.insert(new Point2D(0.4, 0.2));
		kdTree.insert(new Point2D(0.4, 0.5));
		kdTree.insert(new Point2D(0.3, 0.3));
		kdTree.insert(new Point2D(0.4, 0.4));
		kdTree.insert(new Point2D(0.1, 0.5));
		kdTree.insert(new Point2D(0.3, 0.1));
		kdTree.insert(new Point2D(0.25, 0.1));
		kdTree.draw();
		
		System.out.println();
		System.out.println();
		System.out.println("--- Range ---");
		RectHV rect = new RectHV(0.5, 0.5, 8, 8);
		Iterable<Point2D> it = kdTree.range(rect);
		it.forEach(point -> {System.out.println("["+point.x()+", "+point.y()+"]");});
	}
}
