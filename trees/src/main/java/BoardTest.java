
public class BoardTest {

	public static void main(String[] args) {
		int[][] tiles = {
	    		{1, 0, 3},
	    		{4, 2, 5},
	    		{7, 8, 6}
	    	};
	    	Board board = new Board(tiles);
	    	printout(board);
	    	
	    	System.out.println("Twin");
	    	printout(board.twin());
	    	
	    	System.out.println("Neighbors");
	    	for (Board b : board.neighbors()) {
	    		printout(b);
	    	}
	    	
	    	System.out.println("-------new board-------");
	    	System.out.println("");
	    	int[][] tiles2 = {
	    		{8, 1, 3},
	    		{4, 0, 2},
	    		{7, 6, 5}
	    	};
	    	Board board2 = new Board(tiles2);
	    	printout(board2);
	    	
	    	int[][] tiles3 = {
	    		{0, 1, 3},
	    		{4, 2, 5},
	    		{7, 8, 6}
	    	};
	    	Board board3 = new Board(tiles3);
	    	printout(board3);
	}
	
    private static void printout(Board board) {
    	System.out.println("--- Board ---");
     	System.out.println(board.dimension());
     	System.out.println(board.toString());
     	System.out.println("Hamming = " + board.hamming());
     	System.out.println("Manhattan = " + board.manhattan());
     	System.out.println("Is Goal = " + board.isGoal());
     }

}
