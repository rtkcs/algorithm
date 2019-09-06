import edu.princeton.cs.algs4.Point2D;

public class KdTreeTest {

	public static void main(String[] args) {
		KdTree kdTree = new KdTree();
		kdTree.insert(new Point2D(0, 0));
		kdTree.insert(new Point2D(0, 1));
		kdTree.insert(new Point2D(0, 2));
		kdTree.insert(new Point2D(1, 0));
		kdTree.insert(new Point2D(1, 1));
		kdTree.insert(new Point2D(1, 2));
		kdTree.insert(new Point2D(2, 0));
		kdTree.insert(new Point2D(2, 1));
		kdTree.insert(new Point2D(2, 2));
		
		
		boolean b = kdTree.contains(new Point2D(2, 2));
		System.out.println("KdTree contains [2,2] : " + b);
		
		b = kdTree.contains(new Point2D(0, 0));
		System.out.println("KdTree contains [0,0] : " + b);
		
		b = kdTree.contains(new Point2D(0, 1));
		System.out.println("KdTree contains [0,1] : " + b);
		
		b = kdTree.contains(new Point2D(0, 2));
		System.out.println("KdTree contains [0,2] : " + b);
		
		b = kdTree.contains(new Point2D(2, 3));
		System.out.println("KdTree contains [2,3] : " + b);
		
		b = kdTree.contains(new Point2D(-2, -3));
		System.out.println("KdTree contains [-2,-3] : " + b);
	}

}
