
/**
 * 
 * @author rtakacs
 *
 */
public class WordNet {

	/**
	 * Constructor takes the name of the two input files
	 * 
	 * @param synsets
	 * @param hypernyms
	 */
	public WordNet(String synsets, String hypernyms) {
		if (synsets == null) {
			throw new IllegalArgumentException("synsets is null");
		}
		if (hypernyms == null) {
			throw new IllegalArgumentException("hypernyms is null");
		}
	}

	/**
	 * 
	 * @return returns all WordNet nouns
	 */
	public Iterable<String> nouns() {
		return null;
	}

	/**
	 * Is the word a WordNet noun?
	 * 
	 * @param word String
	 * @return boolean
	 */
	public boolean isNoun(String word) {
		if (word == null) {
			throw new IllegalArgumentException("word is null");
		}
		return true;
	}

	//
	/**
	 * Distance between nounA and nounB (defined below).
	 * 
	 * @param nounA
	 * @param nounB
	 * @return int distance
	 */
	public int distance(String nounA, String nounB) {
		if (nounA == null) {
			throw new IllegalArgumentException("nounA is null");
		}
		if (nounB == null) {
			throw new IllegalArgumentException("nounB is null");
		}
		return 0;
	}

	/**
	 * a synset (second field of synsets.txt) that is the common ancestor of nounA
	 * and nounB in a shortest ancestral path (defined below)
	 * 
	 * @param nounA
	 * @param nounB
	 * @return
	 */
	public String sap(String nounA, String nounB) {
		if (nounA == null) {
			throw new IllegalArgumentException("nounA is null");
		}
		if (nounB == null) {
			throw new IllegalArgumentException("nounB is null");
		}		
		return "";
	}

	// do unit testing of this class
	public static void main(String[] args) {

	}
}
