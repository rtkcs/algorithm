
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class PointSET {
	
	private TreeSet<Point2D> set = new TreeSet<>();
	
	/**
	 * Construct an empty set of points.
	 * 
	 * @return
	 */
	public PointSET() {

	}

	/**
	 * Is the set empty?
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return set.isEmpty();
	}

	/**
	 * Number of points in the set.
	 * 
	 * @return
	 */
	public int size() {
		return set.size();
	}

	/**
	 * Add the point to the set (if it is not already in the set).
	 * 
	 * @param p
	 */
	public void insert(Point2D p) {
		if (p == null) {
			throw new IllegalArgumentException();
		}
		
		set.add(p);
	}

	/**
	 * Does the set contain point p?
	 * 
	 * @param p
	 * @return
	 */
	public boolean contains(Point2D p) {
		if (p == null) {
			throw new IllegalArgumentException();
		}
		return set.contains(p);
	}

	/**
	 * Draw all points to standard draw.
	 */
	public void draw() {
		for (Point2D p : set) {
			p.draw();
		}
	}

	/**
	 * All points that are inside the rectangle (or on the boundary).
	 * 
	 * @param rect
	 * @return
	 */
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) {
			throw new IllegalArgumentException();
		}		
		
		TreeSet<Point2D> ts = new TreeSet<>();
		for(Point2D p : set) {
			if(rect.contains(p)) {
				ts.add(p);
			}
		}
		return ts;
	}

	/**
	 * A nearest neighbor in the set to point p; null if the set is empty.
	 * 
	 * @param p
	 * @return
	 */
	public Point2D nearest(Point2D p) {
		if (p == null) {
			throw new IllegalArgumentException();
		}
		
		PointContainer[] pca = new PointContainer[set.size()];
		
		int i = 0;
		for (Point2D ps : set) {
			PointContainer pc = new PointContainer(ps,ps.distanceTo(p));
			pca[i++] = pc;
		}
		Arrays.sort(pca, new PointContainerComparator());
		
		return pca[0].point;
	}
	
	private class PointContainer{
		
		PointContainer(Point2D point, double distance){
			this.point = point;
			this.distance = distance;
		}
		
		public Point2D point;
		public double distance;
	}
	
	private class PointContainerComparator implements Comparator<PointContainer>{

		@Override
		public int compare(PointContainer pc1, PointContainer pc2) {
			
			return (int)(pc1.distance - pc2.distance);
		}
	}

	/**
	 * Unit testing of the methods (optional).
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
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

}
