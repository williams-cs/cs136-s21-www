import structure5.*;
import java.lang.Comparable;

public class IsHeap {

	/**
	 * @param r the root of some binary tree
	 * @pre data is not null
	 * @post returns true if r is the root of a valid heap
	 */
	public static <V extends Comparable<V>> boolean isMinHeap(BinaryTree<V> r) {
		assert r != null;

		// base case: an empty tree
		if (r.isEmpty())
			return true;

		// Step 1: check that the root node satisfies the
		// local heap property
		if (!r.left().isEmpty() &&
		    (r.value().compareTo(r.left().value()) > 0)) {
				return false;
		}

		if (!r.right().isEmpty() &&
		    (r.value().compareTo(r.right().value()) > 0)) {
				return false;
		}

		// Step 2: recursively check the the left and right
		// subtrees are also heaps
		return isMinHeap(r.left()) && isMinHeap(r.right());
	}


	/**
	 * This main method constructs two heaps of integers, and
	 * checks whether or not those heaps are valid heaps.
	 */
	public static void main(String args[]) {

		// incrementally construct a binary tree that is a valid heap

		// empty tree
		BinaryTree<Integer> empty = new BinaryTree<>();
		System.out.println("EMPTY is a heap: " + isMinHeap(empty));

		// one node: is a heap
		BinaryTree<Integer> t1 = new BinaryTree<>(10, empty, empty);
		System.out.println("T1 is a heap: " + isMinHeap(t1));

		// in these trees, the smaller value always in parent
		BinaryTree<Integer> t2 = new BinaryTree<>(5, t1, empty);
		System.out.println("T2 is a heap: " + isMinHeap(t2));
		BinaryTree<Integer> t3 = new BinaryTree<>(3, empty, t2);
		System.out.println("T3 is a heap: " + isMinHeap(t3));

		// one node: is a heap
		BinaryTree<Integer> t4 = new BinaryTree<>(20, empty, empty);
		System.out.println("T4 is a heap: " + isMinHeap(t4));

		// join t4 and t3 as children of "1"
		BinaryTree<Integer> t5 = new BinaryTree<>(1, t4, t3);
		System.out.println("T5 is a heap: " + isMinHeap(t5));


		// now intentionally break the heap property and check
		// that our code detects that
		System.out.println("Now breaking the heap property...");
		t3.setValue(7);
		System.out.println("T5 is a heap: " + isMinHeap(t5));
	}
}
