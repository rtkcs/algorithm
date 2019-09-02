import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




public class Board {
	
//	private static int[][] goalTiles;
	private int[][] currentTiles;
	private final int dimension;
	private int row0;
	private int column0;
	private int hamming = -1;
	private int manhattan = -1;
	private List<Board> newBoards = new ArrayList<>();
	
	private class Pair<T, U> {
		
		private final T t;
		private final U u;
		Pair(T t, U u) {
			this.t = t;
			this.u = u;
		}
		public T getLeft() {
			return t;
		}

		public U getRight() {
			return u;
		}
	}
	
    /**
     * Creates a board from an n-by-n array of tiles, where tiles[row][col] = tile at (row, col)
     * @param tiles
     */
    public Board(int[][] tiles) {
    	
    	this.currentTiles = tiles;
    	this.dimension = tiles.length;
    	
    	//
    	// --- init goalBoard
    	//
//    	goalTiles = new int[this.dimension][this.dimension];
    	int hammingTemp = 0;
    	int numberOfMoves = 0;
    	int manhattanTemp = 0;
    	int key = 1;
    	for (int i = 0; i < this.dimension; i++) {
    		for (int j = 0; j < this.dimension; j++) {
    			
//    			goalTiles[i][j] = key++;  
    			
    	    	//
    	    	// --- search for 0 - empty cell
    	    	//
    			if (this.currentTiles[i][j] == 0) {
    				this.row0 = i;
    				this.column0 = j;
    			}
    			
    			if ((i==this.dimension-1) && (j==this.dimension-1)) {
    				continue;
    			}
    			
    			if (this.currentTiles[i][j]!=key++) {
        	    	//
        	    	// --- hamming
        	    	//    		
    				hammingTemp++;
    				
    		    	//
    		    	// --- manhattan
    		    	//
    				int currentItem = this.currentTiles[i][j];
    				int row = (currentItem - 1) / this.dimension;
    				int coll = (currentItem % this.dimension);
    				if (coll == 0) {
    					coll = this.dimension -1;
    				} else {
    					coll--;
    				}
    				
    				numberOfMoves = Math.abs((i - row)) + Math.abs((j - coll));
    				manhattanTemp += numberOfMoves; 
    			}
    		}
    	}
    	this.hamming = hammingTemp;
    	this.manhattan = manhattanTemp;
//    	goalTiles[this.dimension-1][this.dimension-1] = 0;
    	
    	

//    	for (int i = 0; i < this.dimension; i++) {
//    		for (int j = 0; j < this.dimension; j++) {
//    		
//    		}
//    	}
    	

    	
		
//    	for (int i = 0; i < this.dimension; i++) {
//    		for (int j = 0; j < this.dimension; j++) {
//    			if (this.goalTiles[i][j] == 0) {
//    				continue;
//    			}
//    			
//    		}
//    	}
    	
    	


    	
//    	for (int i = 0; i < this.dimension; i++) {		// roww
//    		for (int j = 0; j < this.dimension; j++) {	// column
//    				
//    			if (this.currentTiles[i][j] == 0) {
//    				continue;
//    			}
//    			
//    			if (this.currentTiles[i][j] != this.goalTiles[i][j]) {
//    				
//
//    			}
//    		}
//    	}
    	
    	
    }
                                           
    /**
     * String representation of this board.
     * 
     */
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append(this.currentTiles.length);
    	sb.append(System.lineSeparator());
    	
    	for (int i = 0; i < this.dimension; i++) {
    		for (int j = 0; j < this.dimension; j++) {
    			sb.append(this.currentTiles[i][j]);
    			sb.append(" ");
    		}
    		sb.append(System.lineSeparator());
    	}
    	sb.append(System.lineSeparator());
    	
    	return sb.toString();
    }

    /**
     * Returns the board dimension n.
     * @return int
     */
    public int dimension() {
    	return this.dimension;
    }

    /**
     * Number of tiles out of place.
     * @return int
     */
    public int hamming() {
    	return this.hamming;
    }

    /**
     * sum of Manhattan distances between tiles and goal
     * @return
     */
    public int manhattan() {
    	return this.manhattan;
    }

    /**
     * Is this board the goal board?
     * @return If this board is the goal board.
     */
    public boolean isGoal() {
//    	return Arrays.deepEquals(this.currentTiles, this.goalTiles);
		int key = 1;
		if(this.currentTiles[this.dimension-1][this.dimension-1] != 0) {
			return false;
		}
		for(int i = 0; i < this.dimension; i++) {
			for(int j = 0; j < this.dimension; j++) {
				
				if((this.dimension-1 == i) && (this.dimension-1 == j)) {
					continue;
				}
				
				if(this.currentTiles[i][j] != key++) {
					return false;
				}
			}
		}
		return true;
    }

    
    @Override
    public boolean equals(Object obj) {
    	if (obj instanceof Board) {
    		Board that = (Board) obj;
    		if (this.dimension() != that.dimension()) {
    			return false;
    		}
    		return Arrays.deepEquals(this.currentTiles, that.currentTiles);
    	}
    	return false;
    }
    

    /**
     * Returns all neighboring boards from the current board.
     * @return Iterable < Board >
     */
    public Iterable<Board> neighbors() {

    	// create new board from copy of the current board
    	if (newBoards.size() == 0) {
    	
	    	int[][] newTiles = null;
	    	// row0, column0
	    	if ((row0 - 1) > -1) {
	    		newTiles = this.getCopyOfCurrentTiles();
	    		this.swap(newTiles, new Pair<Integer, Integer>(row0, column0), new Pair<Integer, Integer>(row0 - 1, column0));
	    		newBoards.add(new Board(newTiles));
	    	}
	    	if ((row0 + 1) < this.dimension) {
	    		newTiles = this.getCopyOfCurrentTiles();
	    		this.swap(newTiles, new Pair<Integer, Integer>(row0, column0), new Pair<Integer, Integer>(row0 + 1, column0));
	    		newBoards.add(new Board(newTiles));
	    	}
	    	if (column0 - 1 > -1) {
	    		newTiles = this.getCopyOfCurrentTiles();
	    		this.swap(newTiles, new Pair<Integer, Integer>(row0, column0), new Pair<Integer, Integer>(row0, column0 - 1));
	    		newBoards.add(new Board(newTiles));
	    	}
	    	if (column0 + 1 < this.dimension) {
	    		newTiles = this.getCopyOfCurrentTiles();
	    		this.swap(newTiles, new Pair<Integer, Integer>(row0, column0), new Pair<Integer, Integer>(row0, column0 + 1));
	    		newBoards.add(new Board(newTiles));
	    	}
    	}
    	return newBoards;
    }
    
    private int[][] getCopyOfCurrentTiles() {
    	int[][] newTiles = new int[this.dimension][this.dimension];
    	for (int i = 0; i < this.currentTiles.length; i++) {
    		int[] newRow = Arrays.copyOf(this.currentTiles[i], this.dimension);
    		newTiles[i] = newRow;
    	}
    	return newTiles;
    }
    
    /**
     * 
     * @param inta Array of int[][]
     * @param from ImmutablePair < Integer, Integer > row, column
     * @param to ImmutablePair < Integer, Integer > row, column
     */
    private void swap(int[][] inta, Pair<Integer, Integer> from, Pair<Integer, Integer> to) {
    	int temp = inta[to.getLeft()][to.getRight()];
    	inta[to.getLeft()][to.getRight()] = inta[from.getLeft()][from.getRight()];
    	inta[from.getLeft()][from.getRight()] = temp;
    }

    /**
     * A board that is obtained by exchanging any pair of tiles.
     * @return Board
     */
    public Board twin() {
    	int[][] newTiles = this.getCopyOfCurrentTiles();
    	if (newTiles[0][0] != 0 && newTiles[1][0] != 0) {
    		this.swap(newTiles, new Pair<Integer, Integer>(0, 0), new Pair<Integer, Integer>(1, 0));
    	} else {
    		this.swap(newTiles, new Pair<Integer, Integer>(0, 1), new Pair<Integer, Integer>(1, 1));
    	}
    	return new Board(newTiles);
    }

    /**
     * unit testing (not graded)
     * @param args
     */
    public static void main(String[] args) {
    	int[][] tiles = {
    		{1, 0, 3},
    		{4, 2, 5},
    		{7, 8, 6}
    	};
    	Board board = new Board(tiles);
    	board.printout(board);
    	
    	System.out.println("Twin");
    	board.printout(board.twin());
    	
    	System.out.println("Neighbors");
    	for (Board b : board.neighbors()) {
    		b.printout(b);
    	}
    	
    	System.out.println("-------new board-------");
    	System.out.println("");
    	int[][] tiles2 = {
    		{8, 1, 3},
    		{4, 0, 2},
    		{7, 6, 5}
    	};
    	Board board2 = new Board(tiles2);
    	board2.printout(board2);
    	
    	int[][] tiles3 = {
    		{0, 1, 3},
    		{4, 2, 5},
    		{7, 8, 6}
    	};
    	Board board3 = new Board(tiles3);
    	board3.printout(board3);
    }
    private void printout(Board board) {
    	System.out.println("--- Board ---");
     	System.out.println(board.dimension());
     	System.out.println(board.toString());
     	System.out.println("Hamming = " + board.hamming());
     	System.out.println("Manhattan = " + board.manhattan());
     	System.out.println("Is Goal = " + board.isGoal());
     }

}