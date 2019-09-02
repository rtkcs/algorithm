import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
	
	private boolean solvable = true;
	private Node currenNode;
	private Node currenNodeTwin;
	private final List<Board> list = new LinkedList<>();
	private final List<Node> previousSolutions = new ArrayList<Node>();
	private final List<Node> previousSolutionsTwin = new ArrayList<Node>();
	
	
	private class BoardComparator implements Comparator<Board> {

		@Override
		public int compare(Board b1, Board b2) {
			
			int i1 = b1.manhattan()+list.size();
			int i2 = b2.manhattan()+list.size();
			return i1 - i2;
		}
		
	}
	
	private class NodeComparator implements Comparator<Node> {

		@Override
		public int compare(Node n1, Node n2) {
			int i1 = n1.getPriorityFunction();
			int i2 = n2.getPriorityFunction();
			
			return i1 - i2;
		}
	}
	
	private class Node {
		
		private Board board;
		private Node parentNode;
		private int numberOfMoves;
		private int priorityFunction;
		private List<Board> boardChildren = new LinkedList<>();
		private int manhattan;
		private int hashCode;
		
		public Node(Board board, int numberOfMoves, Node parentNode) {
			this.board = board;
			this.numberOfMoves = numberOfMoves;
			this.manhattan = board.manhattan();
			this.priorityFunction = this.numberOfMoves + this.manhattan;
			this.parentNode = parentNode;
			this.hashCode = this.manhattan;
		}
		
		public int getPriorityFunction() {
			return this.priorityFunction;
		}
		
		public Board getBoard() {
			return this.board;
		}
		
		public int getNumberOfMoves() {
			return this.numberOfMoves;
		}
		
		public void addToChilden(Board childBoard) {
			this.boardChildren.add(childBoard);
		}
		
		@Override
		public int hashCode() {
			return this.hashCode;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Node) {
				Node that = (Node) obj;
				return this.board.equals(that.board);
			}
			return false;
		}
	}
	
    /**
     * find a solution to the initial board (using the A* algorithm)
     * @param initial
     */
    public Solver(Board initial) {
    	if (initial == null) {
    		throw new IllegalArgumentException("Initial Board is null");
    	}
    	
    	this.currenNode = new Node(initial, 0, null);
    	this.currenNodeTwin = new Node(initial.twin(), 0, null);
    	
    	MinPQ<Node> minPq = new MinPQ<>(new NodeComparator());
    	MinPQ<Node> minPqTwin = new MinPQ<>(new NodeComparator());
    	this.previousSolutions.add(this.currenNode);
    	this.previousSolutionsTwin.add(this.currenNodeTwin);
    	
    	Node n;
    	Node nt;
    	try {
	    	while (!this.currenNode.getBoard().isGoal() && !this.currenNodeTwin.getBoard().isGoal()) {
	    		for (Board b :this.currenNode.getBoard().neighbors()) {
	    			n = new Node(b, this.currenNode.getNumberOfMoves()+1, this.currenNode);
	    			
//	    			System.out.println("contains " + counter++ + " listSize:"+this.list.size() + " minPQ.size = "+minPq.size());
	    			
	    			if (!this.previousSolutions.contains(n)) {
	    				minPq.insert(n);
	    				this.previousSolutions.add(n);
	    			}
	    		}
	    		this.currenNode = minPq.delMin();
	    		
	    		for (Board b :this.currenNodeTwin.getBoard().neighbors()) {
	    			nt = new Node(b, this.currenNodeTwin.getNumberOfMoves()+1, this.currenNodeTwin);
	    			
//	    			System.out.println("contains " + counter++ + " listSize:"+this.list.size() + " minPQ.size = "+minPq.size());
	    			
	    			if (!this.previousSolutionsTwin.contains(nt)) {
	    				minPqTwin.insert(nt);
	    				this.previousSolutionsTwin.add(nt);
	    			}
	    		}
	    		this.currenNodeTwin = minPqTwin.delMin();
	    	}
    	} catch (NoSuchElementException e) {
    		this.solvable = false;
    	}
    	
    	list.clear();
    	if(this.currenNode.getBoard().isGoal()) {
	    	this.solvable = true;
	    	n = this.currenNode;
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
    	return this.list;
    }

    /**
     * test client (see below) 
     * @param args
     */
    public static void main(String[] args) {
    	// create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
