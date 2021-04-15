import structure5.Association;
import structure5.Vector;

public class NaiveProbing {

	public static void main(String args[]) {
		Hashtable<String, String> map = new Hashtable<String, String>();

		System.out.println("inserting: atlanta, detroit, queens");
		map.put("atlanta", "atlanta");
		map.put("detroit", "detroit");
		map.put("queens", "queens");

		System.out.println("Map after inserting all three values:");
		System.out.println(map);


		System.out.println("Now look up all three values:");
		System.out.println(map.get("atlanta"));
		System.out.println(map.get("detroit"));
		System.out.println(map.get("queens"));

		// The below code breaks because we don't use placeholders
		// when we remove values, and our `get` method gives up
		// when it sees its first empty slot.
		System.out.println("Deleting atlanta from our hashtable:");
		map.remove("atlanta");
		System.out.println(map);

		System.out.println("Now look up queens from our hashtable:");
		System.out.println(map.get("queens"));
	}

}

class Hashtable <K,V> {
	// use default of 8 bins for our toy example above
	public static final int DEFAULT_CAP = 8;
	public Object data[];
	public int elems;

    
	public Hashtable() {
		data = new Object[Hashtable.DEFAULT_CAP];
		elems = 0;
	}

	// tell the compiler not to worry about casting... we
	// are confident we know what we're doing
	@SuppressWarnings("unchecked")
	public V put(K key, V value) {
		int bin = Hashtable.dummyHashcode(key) % data.length;
		while (true) {
			Association<K,V> slot = (Association<K,V>)data[bin];
			if (slot == null)  {
				data[bin] = new Association<K,V>(key, value);
				return null;
			}
	    
			if (slot.getKey().equals(key)) {
				V old = slot.getValue();
				slot.setValue(value);
				return old;
			}
			bin = (bin + 1) % data.length;
		}
	}

	@SuppressWarnings("unchecked")
	public V get(K key) {
		int bin = Hashtable.dummyHashcode(key) % data.length;
		while (true) {
			Association<K,V> slot = (Association<K,V>)data[bin];
			if (slot == null)
				return null;
	    
			if (slot.getKey().equals(key))
				return slot.getValue();

			bin = (bin + 1) % data.length;
		}
	}

	@SuppressWarnings("unchecked")
	public V remove(K key) {
		int bin = Hashtable.dummyHashcode(key) % data.length;
		while (true) {
			Association<K,V> slot = (Association<K,V>)data[bin];
			if (slot == null)
				return null;
	    
			if (slot.getKey().equals(key)) {
				V old = slot.getValue();
				data[bin] = null;
			}

			bin = (bin + 1) % data.length;
		}
	}

	@SuppressWarnings("unchecked")
	public String toString() {
		String s = "";
		for (int i = 0; i < data.length; i++)
			if (data[i] == null)
				s += "[_]";
			else
				s += "[" +  ((Association<K,V>)data[i]).getKey() + "]";
		return s;
	}

	// Simple hash function that uses the index of a String's first
	// character within the alphabet. This is *not* a good hashcode,
	// but it makes our example easy to think about.
	public static <K> int dummyHashcode(K key) {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		return alphabet.indexOf(((String)key).substring(0,1));
	}
    
}
