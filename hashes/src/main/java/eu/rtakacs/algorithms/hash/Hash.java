package eu.rtakacs.algorithms.hash;

import java.util.Iterator;
import java.util.LinkedList;

public class Hash<K,V> implements IHash<K,V>, Iterable<K> {
	
	class HashElement<K,V> implements Comparable<Hash<K,V>.HashElement<K,V>>{
		
		K key;
		V value;
		
		public HashElement(K key, V value){
			this.key = key;
			this.value = value;
		}
		
		@Override
		public int compareTo(Hash<K,V>.HashElement<K,V> o) {
			return (((Comparable<K>)this.key).compareTo(o.key));
		}
	}
	
	int numElements;
	int tableSize;
	double maxLoadFactor;
	LinkedList<HashElement<K,V>>[] hashArray;
	
	public Hash(int tableSize) {
		this.tableSize = tableSize;
		this.maxLoadFactor = 0.75;
		this.numElements = 0;
		
		hashArray = (LinkedList<HashElement<K,V>>[]) new LinkedList[this.tableSize];
		for(int i=0; i<this.tableSize;i++) {
			hashArray[i] = new LinkedList<HashElement<K,V>>();
		}
	}
	
	public boolean add(K key, V value) {
		///
		///--- resizing
		///
		if( getLoadFactor() > this.maxLoadFactor) {
			resize(this.tableSize * 2);
		}

		///
		///--- creating new object
		///
		HashElement<K, V> he = new HashElement<>(key, value);

		///
		///--- compute hashValue, the index
		///
		int hashValue = this.getHashValue(key);
		
		///
		///--- add to LinkedList
		///
		hashArray[hashValue].add(he);
		this.numElements++;
		return true;
	}
	
	public boolean remove(K key, V value) {
		///
		///--- creating new object
		///
		HashElement<K, V> he = new HashElement<>(key, value);

		///
		///--- compute hashValue, the index
		///
		int hashValue = this.getHashValue(key);

		///
		///--- remove from LinkedList
		///
		hashArray[hashValue].remove(he);
		this.numElements--;
		return true;		
	}
	
	public V getValue(K key) {
		int hashValue = this.getHashValue(key);
		for(HashElement<K,V> he : hashArray[hashValue]) {
			if(0 == ((Comparable<K>)he.key).compareTo(key)) {
				return he.value;
			}
		}
		return null;
	}
	
	private int getHashValue(K key) {
		int hashValue = key.hashCode();
		hashValue = hashValue & 0x7FFFFFFF;
		hashValue = hashValue % this.tableSize;		
		return hashValue;
	}
	
	private int getLoadFactor() {
		return this.numElements / this.tableSize;
	}
	
	private void resize(int newTableSize) {
		LinkedList<HashElement<K,V>>[] newArray = (LinkedList<HashElement<K,V>>[]) new LinkedList[newTableSize];
		for(int i=0; i< newTableSize; i++) {
			newArray[i] = new LinkedList<HashElement<K,V>>();
		}
		
		for(K key : this) {
			V value = this.getValue(key);
			HashElement<K,V> he = new HashElement<K,V>(key,value);
			int hashValue = (key.hashCode() & 0x7FFFFFFF) % newTableSize;
			newArray[hashValue].add(he);
		}
		this.hashArray = newArray;
		this.tableSize = newTableSize;
	}
	
	class IteratorHelper<T> implements Iterator<T>{
		
		T[] keys;
		int position;
		
		public IteratorHelper(){
			keys = (T[]) new Object[numElements];
			int p = 0;
			for(int i = 0; i < tableSize; i++) {
				LinkedList<HashElement<K,V>> list = hashArray[i];
				for(HashElement<K,V> he : list) {
					keys[p++] = (T) he.key;
				}
			}
			position = 0;
		}
		
		@Override
		public boolean hasNext() {
			return position < keys.length;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				return null;
			}
			return keys[position++];
		}
		
	}

	@Override
	public Iterator<K> iterator() {
		return new IteratorHelper<K>();
	}
	
}
