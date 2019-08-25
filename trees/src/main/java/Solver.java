import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
	
	private boolean solvable = true;
	private Board currenBoard;
	private final List<Board> list = new LinkedList<>();
	private final List<Board> previousSolutions = new ArrayList<>();
	
	
	private class BoardComparator implements Comparator<Board>{

		@Override
		public int compare(Board b1, Board b2) {
			
			int i1 = b1.manhattan();// + b1.hamming();
			int i2 = b2.manhattan();// + b2.hamming();
			
			return i1 - i2;
		}
		
	}
	
    /**
     * find a solution to the initial board (using the A* algorithm)
     * @param initial
     */
    public Solver(Board initial) {
    	if(initial == null) {
    		throw new IllegalArgumentException("Initial Board is null");
    	}
    	
    	this.currenBoard = initial;
    	
    	MinPQ<Board> minPq = new MinPQ<>(4, new BoardComparator());
    	this.list.add(initial);
    	this.previousSolutions.add(initial);
    	
    	try {
	    	while(!this.currenBoard.isGoal()) {
	    		this.currenBoard.neighbors().forEach(board -> {
	    			 
	    			if(!this.previousSolutions.contains(board)) {
	    				minPq.insert(board);
	    				this.previousSolutions.add(board);
	    			}
	    		});
	    		this.currenBoard = minPq.min();
	    		this.list.add(this.currenBoard);
	    		while(!minPq.isEmpty()) {
	    			minPq.delMin();
	    		}
	    	}
    	} catch(NoSuchElementException e) {
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
    public Iterable<Board> solution(){
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
