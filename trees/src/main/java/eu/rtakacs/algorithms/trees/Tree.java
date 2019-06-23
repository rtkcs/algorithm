package eu.rtakacs.algorithms.trees;

public class Tree<E> {

	Node<E> root;
	int currentSize;
	
	public Tree() {
		this.root = null;
		this.currentSize = 0;
	}

	class Node<E> {
		E data;
		Node<E> left;
		Node<E> right;
		Node<E> parent;

		public Node(E obj) {
			this.data = obj;
			this.left = this.right = this.parent = null;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("Node[left=");
			if(this.left!=null) {
				sb.append(this.left.data);
			}else {
				sb.append("null");
			}
			sb.append(", data=");
			sb.append(this.data);
			sb.append(", right=");
			if(this.right!=null) {
				sb.append(this.right.data);
			}else {
				sb.append("null");
			}
			sb.append(", parent=");
			if(this.parent!=null) {
				sb.append(this.parent.data);
			}else {
				sb.append("null");
			}
			sb.append("]");
			return sb.toString();
		}
	}

	public void add(E obj) {
		if (root == null) {
			Node<E> rootNode = new Node<>(obj);
			rootNode.parent = this.root;
			this.root = rootNode; 
		} else {
			add(obj, root);
		}
		currentSize++;
	}

	@SuppressWarnings("unchecked")
	private void add(E obj, Node<E> node){
        //go to the right
        if(((Comparable<E>) obj).compareTo(node.data) > 0) {
	        if( node.right == null) {
	        	Node<E> addedNode = new Node<E>(obj);
	            node.right = addedNode;
	            addedNode.parent = node;
	            return;
	        }
	        add(obj, node.right);
	        return;
        }
        
        // go to left
        if(node.left == null){
        	Node<E> addedNode = new Node<E>(obj);
	        node.left = addedNode;
	        addedNode.parent = node;
	        return;
        }
        add(obj, node.left);
        return;
	}
	
	
	public boolean contains(E obj){
		
		Node<E> returnObj = contains(obj, root);
		if(null == returnObj) {
			return false;
		} 
		return true;
	}
		 
	private Node<E> contains( E obj, Node<E> node ) {
		if(node == null) return null;
		 
		if( ((Comparable<E>)obj).compareTo(node.data) == 0 ){
			return node;
		}
		if( ((Comparable<E>)obj).compareTo(node.data) > 0 ){
			return contains(obj, node.right);
		}
			return contains(obj, node.left);
	}
	
	/**
	 * 	1.	If we removing a leaf node set parent pointers to null<br>
	 *	2.	If we are deleting a node with one child - set parent pointer to the child<br>
	 *	3.	If we are deleting a node with two children - swap with in order successor of predecessor & delete that leaf<br>
	 * @param obj
	 * @return
	 */
	public E removeNode(E obj) {
		Node<E> returnNode = contains(obj, root);
		if(returnNode == null) {
			return null;
		}
		//1. leaf node
		E returnValue = removeLeafNode(returnNode); 
		if(returnValue==null) {
			
			//2. node with one child
			returnValue = removeNodeWithOneChild(returnNode);
			if(returnValue==null) {
				
				// 3. node with two children, swap with order predecessor
				returnValue = removeNodeWithChildren(returnNode);
			}
		}
		this.currentSize--;
		return returnValue;
	}
	
	private E removeLeafNode(Node<E> returnNode) {
		if(returnNode.right==null && returnNode.left==null) {
			
			if(	returnNode.parent.right!=null &&
				((Comparable<E>) returnNode.data).compareTo( returnNode.parent.right.data ) == 0) {
				returnNode.parent.right = null;
			} else {
				returnNode.parent.left = null;
			}
			returnNode.parent = null;
			return returnNode.data;
		} 
		return null;
	}
	
	private E removeNodeWithOneChild(Node<E> returnNode) {
		//left node has a child
		if(returnNode.right==null && returnNode.left!=null) {
			
			if(	returnNode.parent.right!=null &&
				((Comparable<E>) returnNode.data).compareTo( returnNode.parent.right.data ) == 0) {
				returnNode.parent.right = returnNode.left;
			} else {
				returnNode.parent.left = returnNode.left;
			}
			returnNode.left.parent = returnNode.parent;
			return returnNode.data;
		}
		// right node has a child
		else if(returnNode.right!=null && returnNode.left==null) {
			
			if(	returnNode.parent.right!=null &&
					((Comparable<E>) returnNode.data).compareTo( returnNode.parent.right.data ) == 0) {
					returnNode.parent.right = returnNode.right;
				} else {
					returnNode.parent.left = returnNode.right;
					
				}
				returnNode.right.parent = returnNode.parent;
				return returnNode.data;
		}
		return null;
	}
	
	private E removeNodeWithChildren(Node<E> returnNode) {
		if(returnNode.right!=null && returnNode.left!=null) {
			
			// go 1x right, and then always right, swap the last node with returnNode
			Node<E> lastNode = returnNode.right;
			while (lastNode.left != null){
				lastNode = lastNode.left;
			}
			//swap lastNode and returnNode
			swapNodes(lastNode, returnNode);
			
			//handleLeafNode
			//haldleNodeWithOneChildren
			E data = removeLeafNode(returnNode);
			if(data==null) {
				data = removeNodeWithChildren(returnNode);
			}
			return data;
		}
		return null;
	}
	
	private void swapNodes(Node<E> lastNode, Node<E> returnNode) {
		
		if(this.root == returnNode) {
			this.root = lastNode;
		}
		
		Node<E> tmpParent = lastNode.parent;
		lastNode.parent = returnNode.parent;
		returnNode.parent = tmpParent;
		returnNode.parent.left = returnNode;
		
		Node<E> tmpChild = returnNode.right;
		returnNode.right = lastNode.right;
		if(returnNode.right!=null) {
			returnNode.right.parent = lastNode;
		}
		lastNode.right = tmpChild;
		lastNode.right.parent = lastNode;
		
		tmpChild = returnNode.left;
		returnNode.left = lastNode.left;
		if(returnNode.left!=null) {
			returnNode.left.parent = lastNode;
		}
		lastNode.left = tmpChild;
		lastNode.left.parent = lastNode;
	}
}
