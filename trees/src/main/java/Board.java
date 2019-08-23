import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;

public class Board {
	
	private int[][] goalBoard;
	private int[][] currentBoard;
	
    /**
     * Creates a board from an n-by-n array of tiles, where tiles[row][col] = tile at (row, col)
     * @param tiles
     */
    public Board(int[][] tiles) {
    	
    	this.currentBoard = tiles;
    	//
    	// --- init goalBoard
    	//
    	goalBoard = new int[tiles.length][tiles.length];
    	int key = 1;
    	for(int i=0; i < tiles.length; i++) {
    		for(int j=0; j < tiles.length; j++) {
    			
    			goalBoard[i][j] = key++;  
    		}
    	}
    	goalBoard[tiles.length-1][tiles.length-1] = 0;
    }
                                           
    /**
     * String representation of this board.
     * 
     */
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append(this.currentBoard.length);
    	sb.append(System.lineSeparator());
    	
    	for(int i=0; i < this.currentBoard.length; i++) {
    		for(int j=0; j<this.currentBoard.length; j++) {
    			sb.append(this.currentBoard[i][j]);
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
    	return this.currentBoard.length;
    }

    /**
     * Number of tiles out of place.
     * @return int
     */
    public int hamming() {
    	int hamming = 0;
    	for(int i = 0; i < this.currentBoard.length; i++) {
    		for(int j = 0; j < this.currentBoard.length; j++ ) {
    			if(this.goalBoard[i][j]==0) {
    				continue;
    			}
    			if(this.goalBoard[i][j] != this.currentBoard[i][j]) {
    				hamming++;
    			}
    		}
    	}
    	return hamming;
    }

    /**
     * sum of Manhattan distances between tiles and goal
     * @return
     */
    public int manhattan() {
    	int manhattan = 0;
    	int numberOfMoves = 0;
    	
    	for(int i=0; i<this.currentBoard.length; i++) {
    		for(int j=0; j<this.currentBoard.length; j++) {
    				
    			if(this.currentBoard[i][j] == 0) {
    				continue;
    			}
    			
    			if(this.currentBoard[i][j] != this.goalBoard[i][j]) {
    				
    				int currentItem = this.currentBoard[i][j];
    				int row = currentItem / this.currentBoard.length;
    				int coll = (currentItem % this.currentBoard.length) - 1;
    				
    				numberOfMoves = Math.abs((i - row)) + Math.abs((j - coll));
    				manhattan += numberOfMoves; 
    			}
    		}
    	}
    	return manhattan;
    }

    /**
     * Is this board the goal board?
     * @return If this board is the goal board.
     */
    public boolean isGoal() {
    	return Arrays.deepEquals(this.currentBoard, this.goalBoard);
    }

    
    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof int[][]) {
    		int[][] that = (int[][])obj;
    		return Arrays.deepEquals(this.currentBoard, that);
    	}
    	return false;
    }

    /**
     * all neighboring boards
     * @return
     */
    public Iterable<Board> neighbors(){
    	//search for 0 - empty cell
    	int row0;
    	int cell0;
    	for(int i=0; i<this.currentBoard.length; i++) {
    		for(int j=0; j<this.currentBoard.length; j++) {
    			if(this.currentBoard[i][j] == 0) {
    				row0 = i;
    				cell0 = j;
    			}
    		}
    	}
    	// create new boards
    	List<Board> newBoards = new ArrayList<>();
    	int[][] newTiles = new int[this.currentBoard.length][this.currentBoard.length];
    	for(int i = 0; i < this.currentBoard.length; i++) {
    		int[] newRow = Arrays.copyOf(this.currentBoard[i], this.currentBoard.length);
    		newTiles[i] = newRow;
    	}
    	newBoards.add(new Board(newTiles));
    	
    	return newBoards;
    }
    
    /**
     * 
     * @param inta Array of int[][]
     * @param from ImmutablePair < Integer, Integer >
     * @param to ImmutablePair < Integer, Integer >
     */
    private void swap(int[][] inta, ImmutablePair<Integer,Integer> from, ImmutablePair<Integer, Integer> to) {
    	int temp = inta[to.getLeft()][to.getRight()];
    	inta[to.getLeft()][to.getRight()] = inta[from.getLeft()][from.getRight()];
    	inta[from.getLeft()][from.getRight()] = temp;
    }

    /**
     * a board that is obtained by exchanging any pair of tiles
     * @return
     */
    public Board twin() {
    	return null;
    }

    /**
     * unit testing (not graded)
     * @param args
     */
    public static void main(String[] args) {
    	int[][] tiles = new int[][] {
    		{1,0,3},
    		{4,2,5},
    		{7,8,9}
    	};
    	Board board = new Board(tiles);
    	board.printout(board);
    	
    	int[][] tiles2 = new int[][] {
    		{8,1,3},
    		{4,0,2},
    		{7,6,5}
    	};
    	Board board2 = new Board(tiles2);
    	board2.printout(board2);
    }
     private void printout(Board board) {
     	System.out.println(board.dimension());
     	System.out.println(board.toString());
     	System.out.println("Hamming = " + board.hamming());
     	System.out.println("Manhattan = " + board.manhattan());
     	System.out.println("Is Goal = " + board.isGoal());
     }

}