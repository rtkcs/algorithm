import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {
	
	private NodeY root;
	private int size;
	
	private class Node {
		Node parent;
		Node leftChild;
		Node rightChild;
		Point2D point;
		
		public Node(Point2D point, Node parent) {
			this.point = point;
			this.parent = parent;
		}
		
		public String toString() {

			StringBuilder sb = new StringBuilder();
			
			sb.append("Node[leftChild=]");
			if (this.leftChild!=null) {
				sb.append(this.leftChild.point.toString());
			} else {
				sb.append("null");
			}
			
			sb.append(", point=");
			sb.append(this.point.toString());
			
			sb.append(", rightChild=");
			if (this.rightChild!=null) {
				sb.append(this.rightChild.toString());
			} else {
				sb.append("null");
			}
			
			sb.append(", parent=");
			if (this.parent!=null) {
				sb.append(this.parent.toString());
			} else {
				sb.append("null");
			}
			
			sb.append("]");
			return sb.toString();
		}
	}
	
	private class NodeY extends Node {

		public NodeY(Point2D point, Node parent) {
			super(point, parent);
		}
	}
	
	private class NodeX extends Node {

		public NodeX(Point2D point, Node parent) {
			super(point, parent);
		}
	}
	
//	private TreeSet<Point2D> set = new TreeSet<>();
	
	/**
	 * Construct an empty set of points.
	 * 
	 * @return
	 */
	public KdTree() {
		this.root = null;
		this.size = 0;
	}

	/**
	 * Is the set empty?
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * Number of points in the set.
	 * 
	 * @return
	 */
	public int size() {
		return this.size;
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
		
		if(this.root == null) {
			 NodeY rootNodeY = new NodeY(p, null);
			this.root = rootNodeY;
			rootNodeY.parent = this.root;
		} else {
			insert(p, this.root);
		}
		this.size++;
	}	
	
	private void insert(Point2D point, Node parent) {
		// parent is NodeY
		if (parent instanceof NodeY) {
			// leftChild
			if (parent.point.y() >= point.y()) {
				
				if (parent.leftChild == null) {
					NodeX nodeX = new NodeX(point, parent);
					parent.leftChild = nodeX;
					nodeX.parent = parent;
					return;
				}
				insert(point, parent.leftChild);
				return;
			} 
			// rightChild
			if (parent.rightChild == null) {
				NodeX nodeX = new NodeX(point, parent);
				parent.rightChild = nodeX;
				nodeX.parent = parent;
				return;
			}
			insert(point, parent.rightChild);
			return;
		} 
		// parent is NodeX
		else {
			// leftChild
			if(parent.point.x() >= point.x()) {
				if(parent.leftChild == null) {
					NodeY nodeY = new NodeY(point, parent);
					parent.leftChild = nodeY;
					nodeY.parent = parent;
					return;
				}
				insert(point, parent.leftChild);
				return;
			}
			if(parent.rightChild == null) {
				NodeY nodeY = new NodeY(point, parent);
				parent.rightChild = nodeY;
				nodeY.parent = parent;
				return;
			}
			insert(point, parent.rightChild);
			return;
		}
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
		
		return contains(p, this.root);
	}
	private boolean contains(Point2D point, Node parent) {
		if (parent == null) {
			return false;
		}
		if (parent.point.equals(point)) {
			return true;
		}
		if (parent instanceof NodeX) {
			if(parent.point.x() >= point.x()) {
				return contains(point, parent.leftChild);
			} else {
				return contains(point, parent.rightChild);
			}
		} else {
			if (parent.point.y() >= point.y()) {
				return contains(point, parent.leftChild);
			} else {
				return contains(point, parent.rightChild);
			}
		}
	}

	/**
	 * Draw all points to standard draw.
	 */
	public void draw() {
		// traverse tree and call Point2D.draw on every Node.point
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
		// traverse tree and add relevand Points to ts;
		
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
		
		PointContainer[] pca = new PointContainer[this.size];
		
		int i = 0;

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
		
	
	}
	
}
