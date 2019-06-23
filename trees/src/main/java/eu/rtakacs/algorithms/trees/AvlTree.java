package eu.rtakacs.algorithms.trees;

import eu.rtakacs.algorithms.trees.Tree.Node;

public class AvlTree<T> extends Tree<T> {

	public AvlTree() {
		super();
	}

	@Override
	public void add(T obj) {
		Node<T> node = new Node<>(obj);
		if (this.root == null) {
			this.root = node;
			this.currentSize++;
			return;
		}
		add(root, node);
	}

	private void add(Node<T> parent, Node<T> newNode) {
		// newNode > parent -> right hand side
		if (((Comparable<T>) newNode.data).compareTo(parent.data) > 0) {
			if (parent.right == null) {
				parent.right = newNode;
				newNode.parent = parent;
				this.currentSize++;
			} else {
				add(parent.right, newNode);
			}
		} else { // newNode <= parent -> left hand side
			if (parent.left == null) {
				parent.left = newNode;
				newNode.parent = parent;
				this.currentSize++;
			} else {
				add(parent.left, newNode);
			}
		}
		checkBalance(newNode);
	}

	private void checkBalance(Node<T> node) {

		int hLeft = height(node.left);
		int hRight = height(node.right);
		if ((hLeft - hRight > 1) || (hLeft - hRight < -1)) {
			node = rebalance(node);
		}
		// we are at the top of the tree
		if (node.parent == null) {
			return;
		}
		checkBalance(node.parent);
	}

	private Node<T> rebalance(Node<T> node) {
		// left subtree is longer than right subtree
		int hLeft = height(node.left);
		int hRight = height(node.right);

		if (hLeft - hRight > 1) {
			int hLeftLeft = height(node.left.left);
			int hLeftRight = height(node.left.right);

			if (hLeftLeft > hLeftRight) {
				node = rightRotate(node);
			} else {
				node = leftRightRotate(node);
			}
		} else {
			int hRigthLeft = height(node.right.left);
			int hRightRight = height(node.right.right);

			if (hRigthLeft > hRightRight) {
				node = rightLeftRotate(node);
			} else {
				node = leftRotate(node);
			}
		}

		if (node.parent == null) {
			this.root = node;
		}
		return node;
	}

	private int height(Node<T> node) {

		if (node == null) {
			return 0;
		}

		int leftHeight = 0;
		int rightHeight = 0;

		leftHeight = height(node.left);
		;
		rightHeight = height(node.right);

		if (leftHeight > rightHeight) {
			return ++leftHeight;
		}
		return ++rightHeight;
	}

	public Node<T> leftRotate(Node<T> node) {
		Node<T> tmp = node.right;
		node.right = tmp.left;
		tmp.left = node;

		Node<T> tmpParent = node.parent;
		node.parent = tmp;
		tmp.parent = tmpParent;

		setParent(tmp.parent, tmp, node);
		return tmp;
	}

	public Node<T> rightRotate(Node<T> node) {
		Node<T> tmp = node.left;
		node.left = tmp.right;
		tmp.right = node;

		Node<T> tmpParent = node.parent;
		node.parent = tmp;
		tmp.parent = tmpParent;

		setParent(tmp.parent, tmp, node);
		return tmp;
	}

	/**
	 * We pass in a grandparent node.
	 * 
	 * @param node grandparent node
	 * @return
	 */
	public Node<T> rightLeftRotate(Node<T> node) {
		node.right = rightRotate(node.right);
		return leftRotate(node);
	}

	/**
	 * We pass in a grandparent node.
	 * 
	 * @param node grandparent node
	 * @return
	 */
	public Node<T> leftRightRotate(Node<T> node) {
		node.left = leftRotate(node.left);
		return rightRotate(node);
	}

	private void setParent(Node<T> parent, Node<T> newChild, Node<T> oldChild) {
		if (parent == null) {
			return;
		}
		// I am the right node
		if (parent.right != null && ((Comparable) parent.right.data).compareTo(oldChild.data) == 0) {
			parent.right = newChild;
		} else {
			parent.left = newChild;
		}
	}
}
