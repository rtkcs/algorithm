import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.algs4.MinPQ;

public class Solver {
	
	private boolean solvable = true;
	private final List<Board> list = new LinkedList<>();
	
	
	private class NodeComparator implements Comparator<Node> {

		@Override
		public int compare(Node n1, Node n2) {
			int i1 = n1.numberOfMoves + n1.manhattan;
			int i2 = n2.numberOfMoves + n2.manhattan;
			
			return i1 - i2;
		}
	}
	
	private class Node {
		
		private Board board;
		private Node parentNode;
		private int numberOfMoves;
		private int manhattan;
		
		public Node(Board board, int numberOfMoves, Node parentNode) {
			this.board = board;
			this.numberOfMoves = numberOfMoves;
			this.manhattan = board.manhattan();
			this.parentNode = parentNode;
		}
		
		public Board getBoard() {
			return this.board;
		}
		
		public int getNumberOfMoves() {
			return this.numberOfMoves;
		}
		
//		@Override
//		public int hashCode() {
//			return this.manhattan;
//		}
//		
//		@Override
//		public boolean equals(Object obj) {
//			if (obj instanceof Node) {
//				Node that = (Node) obj;
//				return this.board.equals(that.board);
//			}
//			return false;
//		}
	}
	
    /**
     * find a solution to the initial board (using the A* algorithm)
     * @param initial
     */
    public Solver(Board initial) {
    	if (initial == null) {
    		throw new IllegalArgumentException("Initial Board is null");
    	}
    	Node currenNode;
    	Node currenNodeTwin;
    	
    	currenNode = new Node(initial, 0, null);
    	currenNodeTwin = new Node(initial.twin(), 0, null);
    	
    	MinPQ<Node> minPq = new MinPQ<>(new NodeComparator());
    	MinPQ<Node> minPqTwin = new MinPQ<>(new NodeComparator());
    	
    	Node n;
    	Node nt;

    	while (!currenNode.getBoard().isGoal() && !currenNodeTwin.getBoard().isGoal()) {
			for (Board b : currenNode.getBoard().neighbors()) {
				n = new Node(b, currenNode.getNumberOfMoves()+1, currenNode);
				
	//	    			System.out.println("contains " + counter++ + " listSize:"+this.list.size() + " minPQ.size = "+minPq.size());
				
				if (currenNode.parentNode == null) {
					minPq.insert(n);
				} else if (!currenNode.parentNode.getBoard().equals(b)) {
					minPq.insert(n);
				}
			}
			currenNode = minPq.delMin();
			
			for (Board b : currenNodeTwin.getBoard().neighbors()) {
				nt = new Node(b, currenNodeTwin.getNumberOfMoves()+1, currenNodeTwin);
				
	//	    			System.out.println("contains " + counter++ + " listSize:"+this.list.size() + " minPQ.size = "+minPq.size());
				
				if (currenNodeTwin.parentNode == null) {
					minPqTwin.insert(nt);
				} else if (!currenNodeTwin.parentNode.getBoard().equals(b)) {
					minPqTwin.insert(nt);
				}
			}
			currenNodeTwin = minPqTwin.delMin();
		}

    	
    	list.clear();
    	if (currenNode.getBoard().isGoal()) {
	    	this.solvable = true;
	    	n = currenNode;
	    	while (n != null) {
	    		list.add(n.getBoard());
	    		n = n.parentNode;
	    	}
	    	Collections.reverse(this.list);
    	} else {
    		this.solvable = false;
    	}
    }

    /**
     * is the initial board solvable? (see below)
     * @return
     */
    public boolean isSolvable() {
    	return this.solvable;
    }

    /**
     * min number of moves to solve initial board
     * @return
     */
    public int moves() {
    	return this.list.size() - 1;
    }

    /**
     * sequence of boards in a shortest solution
     * @return
     */
    public Iterable<Board> solution() {
    	if (this.solvable) {
    		return this.list;
    	} else {
    		return null;
    	}
    }

    /**
     * test client (see below) 
     * @param args
     */
    public static void main(String[] args) {

    }
}
