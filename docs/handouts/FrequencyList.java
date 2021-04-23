import structure5.*;
import java.util.Iterator;

public class FrequencyList implements Iterable<Association<Character, Integer>> {

	/* a Vector of Associations, each of which has a key being a
	   single character and a value being the number of times that
	   value has been seen */
	protected Vector<Association<Character, Integer>> list;

	/* total number of letters seen so far */
	protected int total;

	public FrequencyList() {
		list = new Vector<Association<Character, Integer>>();
		total = 0;
	}

	public FrequencyList(String input) {
		this();
		for(int i=0; i < input.length(); i++)
			add(input.charAt(i));
	}

	/** add(char ch) Notes
	 * If we have seen ch before, we increment it's associated count.
	 * If this is the first occurrence of ch, we create a new entry
	 * with count=1
	 */
	public void add(char ch) {
		int index = list.indexOf(new Association<Character, Integer>(ch, null));
		if (index == -1) {
			/* first time we've seen @ch, add a new mapping with count=1 */
			list.add(new Association<Character, Integer>(ch, 1));
		} else {
			/* get existing mapping and update the count +1 */
			Association<Character, Integer> a = list.get(index);
			a.setValue(a.getValue() + 1);
		}

		/* Update our running total (needed for sampling, i.e., pickNext()) */
		total++;
	}

	/** Return frequency of ch */
	public int get(char ch) {
		int loc = list.indexOf(new Association<Character, Integer>(ch,0));
		if(loc == -1) return 0;
		else return list.get(loc).getValue();
	}

	/** Return number of characters in list */
	public int size() { return list.size(); }
	/** Return sum of frequencies */
	public int total() { return total; }

	/**
	 * Yields a string representative of the mappings from string -> frequency
	 */
	public String toString() {
		return list.toString();
	}

	public Iterator<Association<Character,Integer>> iterator() {
		return list.iterator();
	}

	public static void usage() {
	    System.out.println("Usage: FrequencyList string");
			System.exit(0);
 }

	public static void main(String[] args) {
		if(args.length < 1) usage();

		FrequencyList li = new FrequencyList();
		for(int i=0; i < args[0].length(); i++)
			li.add(args[0].charAt(i));
		System.out.println(li);

		FrequencyList li2 = new FrequencyList(args[0]);
		System.out.println(li2);
	}
}
