import structure5.*;
import java.util.Iterator;

public class GenericFrequencyList<E> implements Iterable<Association<E, Integer>> {

	/* a Vector of Associations, each of which has a key being a
	   single character and a value being the number of times that
	   value has been seen */
	protected Vector<Association<E, Integer>> list;

	/* total number of letters seen so far */
	protected int total;

	public GenericFrequencyList() {
		list = new Vector<Association<E, Integer>>();
		total = 0;
	}

	public GenericFrequencyList(Vector<E> input) {
		this();
		for(int i=0; i < input.size(); i++)
			add(input.get(i));
	}

	/** add(char ch) Notes
	 * If we have seen ch before, we increment it's associated count.
	 * If this is the first occurrence of ch, we create a new entry
	 * with count=1
	 */
	public void add(E item) {
		int index = list.indexOf(new Association<E, Integer>(item, null));
		if (index == -1) {
			/* first time we've seen @ch, add a new mapping with count=1 */
			list.add(new Association<E, Integer>(item, 1));
		} else {
			/* get existing mapping and update the count +1 */
			Association<E, Integer> a = list.get(index);
			a.setValue(a.getValue() + 1);
		}

		/* Update our running total (needed for sampling, i.e., pickNext()) */
		total++;
	}

	/** Return frequency of ch */
	public int get(E item) {
		int loc = list.indexOf(new Association<E, Integer>(item,0));
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

	public Iterator<Association<E,Integer>> iterator() {
		return list.iterator();
	}

	public static void usage() {
	    System.out.println("Usage: GenericFrequencyList string");
			System.exit(0);
 }

	public static void main(String[] args) {
		if(args.length < 1) usage();

		GenericFrequencyList<Character> li =
					new GenericFrequencyList<Character>();

		for(int i=0; i < args[0].length(); i++)
			li.add(args[0].charAt(i));
		System.out.println(li);

		Vector<Character> chars = new Vector<Character>(args[0].length());
		for(int i = 0; i < args[0].length(); i++) chars.add(args[0].charAt(i));

		GenericFrequencyList<Character>  li2 =
					new GenericFrequencyList<Character>(chars);
		System.out.println(li2);
	}
}
