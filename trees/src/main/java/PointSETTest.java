import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class PointSETTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test2();
	}

	private static void test1() {
		PointSET ps = new PointSET();
		ps.insert(new Point2D(1, 1));
		ps.insert(new Point2D(1, 2));
		ps.insert(new Point2D(1, 3));
		ps.insert(new Point2D(2, 2));
		ps.insert(new Point2D(2, 3));
		ps.insert(new Point2D(3, 3));

		System.out.println("Range:");
		RectHV rect = new RectHV(1, 1, 2, 2);
		Iterable<Point2D> it = ps.range(rect);
		for (Point2D p : it) {
			System.out.println(p.toString());
		}

		Point2D p = new Point2D(1.1, 1.0);
		p = ps.nearest(p);
		System.out.println("Nearest:");
		System.out.println(p);
	}
	
	private static void test2() {
		PointSET ps = new PointSET();
		ps.insert(new Point2D(0.0, 0.0));
		ps.insert(new Point2D(0.75, 0.75));
		ps.insert(new Point2D(0.5, 0.25));
		ps.insert(new Point2D(0.5, 0.0));
		ps.insert(new Point2D(0.75, 0.25));
		ps.insert(new Point2D(0.25, 0.75));
		ps.insert(new Point2D(1.0, 0.75));
		ps.insert(new Point2D(0.75, 0.5));
		ps.insert(new Point2D(1.0, 1.0));
		ps.insert(new Point2D(0.0, 0.5));
		
		Point2D p = new Point2D(1.0, 0.75);
		p = ps.nearest(p);
		System.out.println("Nearest:");
		System.out.println(p);

	}
}
