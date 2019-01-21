package eu.rtakacs.algorithms.list;

/**
 * Boundary conditions:
 * <ul>
 * <li>Empty data structure</li>
 * <li>Single element in the data structure</li>
 * <li>Adding / removing at the beginning of data structure</li>
 * <li>adding / removing at the end of a data structure</li>
 * <li>Working in the middle</li>
 * </ul>
 * @author TAK10892
 *
 * @param <V>
 */
public class LinkedList<V> {
		
	private Node<V> head;
	private Node<V> tail;
	private int currentSize;
	
	class Node<V> implements Comparable<Node<V>>{

		private V v;
		private Node<V> next;
		private Node<V> previous;
		
		Node(V v){
			this.v = v;
			this.next = null;
			this.previous = null;
		}
		
		public V getV() {
			return v;
		}

		public void setV(V v) {
			this.v = v;
		}

		public Node<V> getNext() {
			return next;
		}

		public void setNext(Node<V> next) {
			this.next = next;
		}

		public Node<V> getPrevious() {
			return previous;
		}

		public void setPrevious(Node<V> previous) {
			this.previous = previous;
		}

		public int compareTo(LinkedList<V>.Node<V> o) {
			return ((Comparable<V>)this.v).compareTo(o.getV()); 
		}

		@Override
		public String toString() {
			return "Node [v=" + v + "]";
		}

	}
	
	LinkedList(){
		this.head = null;
		this.currentSize = 0;
	}
	
	public void addFirst(V v) {
		Node<V> newFirst = new Node<V>(v);
		newFirst.setNext(this.head);
		this.head.setPrevious(newFirst);
		this.head = newFirst;
		newFirst.setPrevious(this.head);
		this.currentSize++;
	}
	
	public void addLast(V v) {
		Node<V> newLast = new Node<V>(v);
		this.tail.setNext(newLast);
		newLast.setPrevious(this.tail);
		this.tail = newLast;
		
	}
	
	public Node removeFirst() {
		return null;
	}
	
	public Node removeLast() {
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
