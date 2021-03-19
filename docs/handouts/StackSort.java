import structure5.*;
import java.util.Comparator;
public class StackSort {

	// Insert a value into its proper position in the (sorted)
	// destination stack dst
	public static <T> void insertVal(T val,
					 Stack<T> dst,
					 Comparator<T> c) {

		StackList<T> tmp = new StackList<T>();
		// pop of everything that is larger than val
		while (!dst.empty() && c.compare(val, dst.peek()) >= 0) {
			tmp.push(dst.pop());
		}

		// add val to its correct location
		dst.push(val);

		// add back everything that is larger than val
		while (!tmp.empty()) {
			dst.push(tmp.pop());
		}

	}

	// produce a sorted stack from an unsorted stack src using a third
	// "helper" stack
	public static <T> Stack<T> sortStacks(Stack<T> src, Comparator<T> c) {
		// Create extra helper stack
		Stack<T> dst = new StackList<T>();

		while (!src.empty()) {
			// take one elemnt off of our src stack
			// and stick it in its final location
			// our dst stack
			insertVal(src.pop(), dst, c);
		}

		return dst;
	}

	public static void main (String[] args) {
		Stack<String> src = new StackList<String>();

		// build a stack from command line arguments
		for (String arg : args) {
			src.push(arg);
		}
		System.out.println("Initial value of src:");
		System.out.println(src);

		Stack<String> dst = sortStacks(src, new NaturalComparator<String>());


		System.out.println("Final value of dst:");
		System.out.println(dst);


	}
}
