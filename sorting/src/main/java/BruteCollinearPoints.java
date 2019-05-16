import java.util.LinkedList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
	
	/**
	 * Finds all line segments containing 4 points.
	 * @param points
	 */
	public BruteCollinearPoints(Point[] points) {
		
		if (points == null) {
			throw new IllegalArgumentException("Points[] can not be null.");
		}
		
		double[] slopes = new double[points.length];
		LinkedList<Point> collinearPoints = new LinkedList<>();
		for(int i = 0; i < points.length;i++) {
			
			Point point = points[i];
			if (point == null) {
				throw new IllegalArgumentException("One of the points is null.");
			}
			
			for(int j = i; j > -1; j--) {
				Point pointCompare = points[j];
				if (point.equals(pointCompare)) {
					throw new IllegalArgumentException("Two points are equal.");
				}
				
				double slope = point.slopeTo(pointCompare);
				slopes[j] = slope;
			}
			
			double slope = slopes[i];
			collinearPoints = new LinkedList<>();
			collinearPoints.add(points[i]);
			for (int x = 0; x < slopes.length; x++) {
				if (slopes[x] == 0) {
					continue;
				}
				
				if(slope == slopes[x]) {
					collinearPoints.add(points[x]);
				}
			}
		}
		
		
		
	} 
	
	/**
	 * the number of line segments.
	 * 
	 * @return int
	 */
	public int numberOfSegments() {
		
		
		return 0;
	} 
	
	/**
	 * The line segments.
	 * @return
	 */
	public LineSegment[] segments() {
		
		return null;
	}
	
	public static void main(String[] args) {

	    // read the n points from a file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }

	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();

	    // print and draw the line segments
	    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
	}
	
}
