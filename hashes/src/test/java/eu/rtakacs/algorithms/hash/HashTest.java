package eu.rtakacs.algorithms.hash;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HashTest {
	
	@Test
	void test() {
		Hash<Integer,String> hash = new Hash<>(3);
		hash.add(1, "one");
		hash.add(2, "two");
		hash.add(3, "three");
		hash.add(4, "four");
		hash.add(5, "five");
		hash.add(6, "six");
		hash.add(7, "seven");
		
		System.out.println("Number of Elements = " + hash.numElements);
		System.out.println("Table size = " + hash.tableSize);
		
		Iterator<Integer> it = hash.iterator();
		while(it.hasNext()) {

			Integer key = it.next();
			String value = hash.getValue(key);
			System.out.println(key + " " + value);
		}
		System.out.println();
	}

}
