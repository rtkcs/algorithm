

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
		
	private static final String ERROR_MESSAGE = "Take an integer as a command-line argument.";
	
	public static void main(String[] args) {
		
		if (args.length != 1) {
			throw new IllegalArgumentException(ERROR_MESSAGE);
		}
		int n;
		try {
			n = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(ERROR_MESSAGE, e);
		}
		
		String s;
		RandomizedQueue<String> rq = new RandomizedQueue<>();
		
		while (!StdIn.isEmpty()) {
			s = StdIn.readString();
			rq.enqueue(s);
		}
		
		Iterator<String> iterator = rq.iterator();
		if (n < 0 || rq.size() < n) {
			throw new IllegalArgumentException("Number of elements is less than expected.");
		}
		
		for (int i=0; i < n; i++) {
			System.out.println(iterator.next());
		}
	}
}
