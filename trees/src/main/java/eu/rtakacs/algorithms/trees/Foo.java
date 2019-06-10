package eu.rtakacs.algorithms.trees;

public class Foo implements Comparable<Foo>{
	
	int i;
	public Foo(int i) {
		this.i = i;
	}
	
	@Override
	public String toString(){
		return "Foo[i="+i+"]";
		
	}

	@Override
	public int compareTo(Foo other) {
		return this.i - other.i;
	}

}
