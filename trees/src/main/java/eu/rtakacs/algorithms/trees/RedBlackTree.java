package eu.rtakacs.algorithms.trees;

public class RedBlackTree<K, V> implements IRedBlack<K, V> {

	Node<K, V> root;
	int size;

	RedBlackTree() {
		this.root = null;
		this.size = 0;
	}

	class Node<K, V> {
		K key;
		V value;

		boolean isLeftChild;
		boolean isBlack;
		Node<K, V> parent;
		Node<K, V> left;
		Node<K, V> right;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			this.parent = null;
			this.left = null;
			this.right = null;
			this.isBlack = false;
			this.isLeftChild = false;
		}
	}

	public void add(K key, V value) {
		Node<K, V> node = new Node<>(key, value);
		if (this.root == null) {
			this.root = node;
			node.isBlack = true;
			return;
		}
		add(root, node);
		this.size++;
	}

	private void add(Node<K, V> parent, Node<K, V> newNode) {
		///
		/// -- this comparison (greater than) should be consistent in all methods
		/// -- how to handle duplicates
		///
		if (((Comparable<K>) newNode.key).compareTo(parent.key) > 0) {
			if (parent.right == null) {
				parent.right = newNode;
				newNode.parent = parent;
				newNode.isLeftChild = false;
				newNode.isBlack = false;// new nodes are always red
			}
			add(parent.right, newNode);

		} else {

			if (parent.left == null) {
				parent.left = newNode;
				newNode.parent = parent;
				newNode.isLeftChild = true;
				newNode.isBlack = false;

			}
			add(parent.left, newNode);
		}
		checkColor(newNode);
	}

	public void checkColor(Node<K, V> node) {

		if (node == root) {
			return;
		}

		// no 2 consecutive reds
		if (!node.isBlack && !node.parent.isBlack) {
			correctTree(node);
		}

		checkColor(node.parent);
	}

	public void correctTree(Node<K, V> node) {
		// aunt is node.parent.parent.right
		if (node.parent.isLeftChild) {
			if (node.parent.parent.right == null || node.parent.parent.right.isBlack) {
				rotate(node);
				return;
			}

			// --- red aunt -> color flip
			// --- set aunt = black, grandparent = red, parent = black
			//
			if (node.parent.parent.right != null) {
				node.parent.parent.right.isBlack = true;
			}
			node.parent.parent.isBlack = false;
			node.parent.isBlack = true;
			return;
		}

		///
		/// --- aunt is node.parent.parent.left (grandparent.left)
		/// --- code for aunt = node.parent.parent.left
		///
		if (node.parent.parent.left == null || node.parent.parent.left.isBlack) {
			rotate(node);
			return;
		}

		// --- red aunt -> color flip
		// --- set aunt = black, grandparent = red, parent = black
		//

		if (node.parent.parent.left != null) {
			node.parent.parent.left.isBlack = true;
		}

		node.parent.parent.isBlack = false;
		node.parent.isBlack = true;
		return;

	}

	public void rotate(Node<K, V> node) {

		///
		/// --- we are left child
		///
		if (node.isLeftChild) {
			if (node.parent.isLeftChild) {
				rightRotate(node.parent.parent);
				node.isBlack = false;
				node.parent.isBlack = true;

				if (node.parent.right != null) {
					node.parent.right.isBlack = false;
				}
				return;
			}

			rightLeftRotate(node.parent.parent);
			node.isBlack = true; // the node we start at becomes a parent
			node.right.isBlack = false;
			node.left.isBlack = false;
			return;
		}

		///
		/// --- we are right child
		///
		if (!node.parent.isLeftChild) {
			leftRotate(node.parent.parent);
			node.isBlack = false;
			node.parent.isBlack = true;

			if (node.parent.left != null) {
				node.parent.left.isBlack = false;
			}
			return;
		}
		leftRightRotate(node.parent.parent);
	}

	/**
	 ** node grandparent
	 **/

	/**
	 * Makes a left rotate of a passed grandparent node.
	 * @param node grandparent
	 */
	public void leftRotate(Node<K, V> node) {

		Node<K, V> temp = node.right;
		node.right = temp.left;

		if (node.right != null) {
			node.right.parent = node;
			node.right.isLeftChild = false;
		}

		if (node.parent == null) {
			// we are the root node
			this.root = temp;
			temp.parent = null;

		} else {

			temp.parent = node.parent;

			if (node.isLeftChild) {
				temp.isLeftChild = true;
				temp.parent.left = temp;

			} else {
				temp.isLeftChild = false;
				temp.parent.right = temp;

			}

			temp.left = node;
			node.isLeftChild = true;
			node.parent = temp;
		}
	}


	/**
	 * Makes a right rotate of a passed grandparent node.
	 * @param node grandparent
	 */
	public void rightRotate(Node<K, V> node) {
		Node<K, V> temp = node.left;
		node.left = temp.right;

		if (node.left != null) {
			node.left.parent = node;
			node.left.isLeftChild = true;
		}

		if (node.parent == null) {
			// we are the root node
			this.root = temp;
			temp.parent = null;

		} else {

			temp.parent = node.parent;

			if (node.isLeftChild) {
				temp.isLeftChild = true;
				temp.parent.left = temp;

			} else {
				temp.isLeftChild = false;
				temp.parent.right = temp;

			}

			temp.left = node;
			node.isLeftChild = true;
			node.parent = temp;
		}
	}

	/**
	 * Makes a left right rotate of a passed grandparent node.
	 * @param node grandparent
	 */
	public void leftRightRotate(Node<K, V> node) {
		leftRotate(node.left);
		rightRotate(node);
	}

	/**
	 * Makes a right left rotate of a passed grandparent node.
	 * @param node grandparent
	 */
	public void rightLeftRotate(Node<K, V> node) {
		rightRotate(node.right);
		leftRotate(node);
	}
	
	
	/**
	 * Returns a height of a tree.
	 * @return int
	 */
	public int height() {

		if (root == null) {
			return 0;
		}
		return height(root) - 1;
	}
	
	/**
	 * Computes a height of a tree.
	 * @param node
	 * @return
	 */
	private int height(Node<K, V> node) {

		if (node == null) {
			return 0;
		}

		int leftHeight = height(node.left) + 1;
		int rightHeight = height(node.right) + 1;

		if (leftHeight > rightHeight) {
			return leftHeight;
		}
		return rightHeight;

	}

}
