import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

	private final List<LineSegment> llLineSegment = new LinkedList<>();
	
	/**
	 * Finds all line segments containing 4 or more points.
	 * @param points
	 */
	public FastCollinearPoints(Point[] points) {
		
		
		
		if (points == null) {
			throw new IllegalArgumentException("Points[] can not be null.");
		}
		
		
		for (int i = 0; i < points.length; i++) {
			PointContainer[] pcArray = new PointContainer[points.length];
			
			Point point = points[i];
			if (point == null) {
				throw new IllegalArgumentException("One of the points is null.");
			}
			
			for (int j = 0; j < points.length; j++) {
				
//				if(i == j) {
//					continue;
//				}
				
				Point pointCompare = points[j];
				if (point.equals(pointCompare) && i != j) {
					throw new IllegalArgumentException("Two points are equal.");
				}
				
				double slope = point.slopeTo(pointCompare);
				PointContainer pc = new PointContainer(pointCompare, slope);
				pcArray[j] = pc;
			}
			
			// sort PointContainerArray  by their slopes
			// -infinite slope is the refference point, it is first in the array
			Arrays.sort(pcArray, new PointContainerSlopeComparator());


			

			double initSlope = pcArray[1].getSlope();
			int startIndex = 1;
			int endIndex = 1;
			for (int x = 2; x < points.length; x++) {
				
				while(x < points.length && initSlope == pcArray[x].getSlope()) {
					x++;
				}
				endIndex = x - 1;
				// Check if any 3 (or more) adjacent points in the sorted order have equal 
				// slopes with respect to p. If so, these points, together with p, are collinear.
				int segmentLength = endIndex - startIndex + 2; 
				if (segmentLength > 3) {
					// copy points from 0, startIndex - endIndex in separate array and sort it
					// the 1. is the start of LineSegment, the last point is the end of LineSegment
					Point[] segmentPC = new Point[segmentLength];
					segmentPC[0] = pcArray[0].getPoint();
					int segmentPCPointer = 1;
					while (startIndex <= endIndex) {
						segmentPC[segmentPCPointer] = pcArray[startIndex].getPoint();
						startIndex++;
						segmentPCPointer++;
					}
					Arrays.sort(segmentPC);
					LineSegment ls = new LineSegment(segmentPC[0], segmentPC[segmentLength-1]);
					this.llLineSegment.add(ls);
					
				}
				if(x < points.length) {
					initSlope = pcArray[x].getSlope();
				}
				startIndex = x;
				endIndex = x;

			} // for
			
			
		} // for
		
	}
	
//	private class SlopeComparator implements Comparator<Point> {
//		
//		@Override
//		public int compare(Point p1, Point p2) {
//			double slope = p1.slopeTo(p2);
//		}
//	} 
	
	private class PointContainer {
		private final Point point;
		private final double slope;
		
		PointContainer(Point point, double slope) {
			this.point = point;
			this.slope = slope;
		}
		
		double getSlope() {
			return this.slope;
		}
		
		Point getPoint() {
			return this.point;
		}
		
		@Override
		public int hashCode() {
			return (int) this.slope;
		}
		
		@Override 
		public boolean equals(Object obj) {
			if (obj instanceof PointContainer) {
				PointContainer that = (PointContainer) obj;

				if (that.getSlope() == this.getSlope()) {
					return true;
				} else {
					return false;
				}
				
			} else {
				return false;
			}
			
		}
		
		public String toString() {
			return "slope=" + this.slope + ", point=" + this.point;
		}
	}
	
	private class PointContainerSlopeComparator implements Comparator<PointContainer> {

		@Override
		public int compare(PointContainer o1, PointContainer o2) {
			return Double.compare(o1.slope, o2.slope);
		}
	}
	
	/**
	 * The number of line segments.
	 * @return
	 */
	public int numberOfSegments() {
		
		return this.llLineSegment.size();
	} 
	
	/**
	 * The line segments.
	 * @return
	 */
	public LineSegment[] segments() {
		
		LineSegment[] ls = new LineSegment[this.llLineSegment.size()];
		for (int i = 0; i < this.llLineSegment.size(); i++) {
			ls[i] = this.llLineSegment.get(i);
		}
		return ls;
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
	    FastCollinearPoints collinear = new FastCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
	}
}