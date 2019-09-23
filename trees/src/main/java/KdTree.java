import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {

	private NodeX root;
	private int size;

	private class Node {
		Node parent;
		Node leftChild;
		Node rightChild;
		Point2D point;
		Boolean left;

		public Node(Point2D point, Node parent, Boolean left) {
			this.point = point;
			this.parent = parent;
			this.left = left;
		}

		@Override
		public String toString() {

			StringBuilder sb = new StringBuilder();

			sb.append("Node[leftChild=]");
			if (this.leftChild != null) {
				sb.append(this.leftChild.point.toString());
			} else {
				sb.append("null");
			}

			sb.append(", point=");
			sb.append(this.point.toString());

			sb.append(", rightChild=");
			if (this.rightChild != null) {
				sb.append(this.rightChild.toString());
			} else {
				sb.append("null");
			}

			sb.append(", parent=");
			if (this.parent != null) {
				sb.append(this.parent.toString());
			} else {
				sb.append("null");
			}

			sb.append("]");
			return sb.toString();
		}
	}

	private class NodeY extends Node {

		public NodeY(Point2D point, Node parent, Boolean left) {
			super(point, parent, left);
		}

		@Override
		public String toString() {
			return toString();
		}
	}

	private class NodeX extends Node {

		public NodeX(Point2D point, Node parent, Boolean left) {
			super(point, parent, left);
		}

		@Override
		public String toString() {
			return toString();
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

		if (this.root == null) {
			NodeX rootNodeX = new NodeX(p, null, null);
			this.root = rootNodeX;
//			rootNodeX.parent = this.root;
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
					NodeX nodeX = new NodeX(point, parent, true);
					parent.leftChild = nodeX;
					nodeX.parent = parent;
					return;
				}
				insert(point, parent.leftChild);
				return;
			}
			// rightChild
			if (parent.rightChild == null) {
				NodeX nodeX = new NodeX(point, parent, false);
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
			if (parent.point.x() >= point.x()) {
				if (parent.leftChild == null) {
					NodeY nodeY = new NodeY(point, parent, true);
					parent.leftChild = nodeY;
					nodeY.parent = parent;
					return;
				}
				insert(point, parent.leftChild);
				return;
			}
			if (parent.rightChild == null) {
				NodeY nodeY = new NodeY(point, parent, false);
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
			if (parent.point.x() >= point.x()) {
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
		this.printPreOrder(new DrawTraverser());
	}

	private interface Traverser {
		void execute(Node node);
	}

	private class DrawTraverser implements Traverser {

		public void execute(Node node) {

			StdDraw.setPenColor(StdDraw.BLACK);
//			node.point.draw();
			StdDraw.filledCircle(node.point.x(), node.point.y(), 0.005);

			double from = 0;
			double to = 1;
			if (node instanceof NodeX) {
				StdDraw.setPenColor(StdDraw.RED);
				if (node.parent != null) {
					if (node.left) {
						to = node.parent.point.y();
					} else {
						from = node.parent.point.y();
					}
					if (node.parent != null && node.parent.parent != null && node.parent.parent.parent != null) {
						if (node.left != null) {
							from = node.parent.parent.parent.point.y();
						} else {
							to = node.parent.parent.parent.point.y();
						}
					} else {
						if (node.left) {
							from = 0;
						} else {
							to = 1;
						}
					}
				} else {
					from = 0;
					to = 1;
				}
				StdDraw.line(node.point.x(), from, node.point.x(), to);
			} else {
				StdDraw.setPenColor(StdDraw.BLUE);
				if (node.parent != null) {
					if (node.left) {
						to = node.parent.point.x();
					} else {
						from = node.parent.point.x();
					}
					if (node.parent != null && node.parent.parent != null && node.parent.parent.parent != null) {
						if (node.left != null) {
							from = node.parent.parent.parent.point.x();
						} else {
							to = node.parent.parent.parent.point.x();
						}
					} else {
						if (node.left) {
							from = 0;
						} else {
							to = 1;
						}
					}
				} else {
					from = 0;
					to = 1;
				}
				StdDraw.line(from, node.point.y(), to, node.point.y());
			}
		}
	}

	private void printInOrder(Traverser t) {
		this.printInOrder(this.root, t);
	}

	private void printInOrder(Node node, Traverser t) {
		if (node == null) {
			return;
		}
		printInOrder(node.leftChild, t);
		System.out.print(node.point);
		t.execute(node);
		printInOrder(node.rightChild, t);
	}

	private void printPreOrder(Traverser t) {
		this.printPreOrder(this.root, t);
	}

	private void printPreOrder(Node node, Traverser t) {
		if (node == null) {
			return;
		}
		System.out.print(node.point);
		t.execute(node);
		printPreOrder(node.leftChild, t);
		printPreOrder(node.rightChild, t);
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

		rect.xmin();
		rect.ymin();
		rect.xmax();
		rect.ymax();

		Point2D minP = new Point2D(rect.xmin(), rect.ymin());
		Point2D maxP = new Point2D(rect.xmax(), rect.ymax());

//		TreeSet<Point2D> ts = new TreeSet<>();
		// traverse tree and add relevand Points to ts;

		return null;
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

	private class PointContainer {

		PointContainer(Point2D point, double distance) {
			this.point = point;
			this.distance = distance;
		}

		public Point2D point;
		public double distance;
	}

	private class PointContainerComparator implements Comparator<PointContainer> {

		@Override
		public int compare(PointContainer pc1, PointContainer pc2) {
			return (int) (pc1.distance - pc2.distance);
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
