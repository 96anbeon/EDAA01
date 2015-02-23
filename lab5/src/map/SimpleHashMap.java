package map;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private final double loadFactor = 0.75;
	private Entry<K,V>[] table;
	private int capacity;
	private int size;
	
	/** Constructs an empty hashmap with the default initial capacity (16)
	and the default load factor (0.75). */
	public SimpleHashMap(){
		capacity = 16;
		table = (Entry<K, V>[]) new Entry[capacity];
		size = 0;
	}
	
	/** Constructs an empty hashmap with the specified initial capacity
	and the default load factor (0.75). */
	public SimpleHashMap(int capacity){
		this.capacity = capacity;
		table = (Entry<K, V>[]) new Entry[capacity];
		size = 0;
	}

	@Override
	public V get(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public V put(K key, V value) {
		int index = index(key);
		if(index == -1){
			if((((double) size)/capacity) > loadFactor){
				rehash();
			}
			index = key.hashCode() % table.length;
			Entry<K, V> e = table[index];
			while(e != null){
				e = e.next;
			}
			
			//TODO
			
		} else{
			return find(index, key).setValue(value);
			
		}
		return null;
	}

	@Override
	public V remove(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return size;
	}
	
	public String show(){
		table[3] = (Entry<K, V>) new Entry<Integer, String>(8, "Erik");
		table[3].next = (Entry<K, V>) new Entry<Integer, String>(9, "Pontus");
		StringBuilder sb = new StringBuilder();
		for ( int i = 0; i < table.length; i++){
			sb.append(i + "\t");
			if(table[i] != null){
				Entry<K,V> e = table[i];
				while(e != null){
					sb.append(e.toString() + ", ");
					e = e.next;
				}
			} else {
				sb.append("empty");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public int index(K key){
		for(int i = 0; i < table.length; i++){
			if(table[i] != null){
				Entry<K, V> e = table[i];
				while(e != null){
					if(e.key == key){
						return i;
					}
					e = e.next;
				}
			}
		}
		return -1;
	}
	
	public Entry<K,V> find(int index, K key){
		Entry<K, V> e = table[index];
		while(e != null){
			if(e.key == key){
				return e;
			}
			e = e.next;
		}
		return null;
	}
	
	public void rehash(){
		capacity *= 2;
		Entry<K, V>[] newArray = (Entry<K, V>[]) new Entry[capacity];
		System.arraycopy(table, 0, newArray, 0, table.length);
		table = newArray;
	}
	
	public static class Entry<K, V> implements Map.Entry<K, V>{
		private K key;
		private V value;
		private Entry<K, V> next;
		
		public Entry(K key, V value){
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			V temp = this.value;
			this.value = value;
			return temp;
		}
		
		@Override
		public String toString(){
			return key + "=" + value;
		}
	}
}
