import java.util.Vector;

public class CheckHeap {

	/**
	 * @pre data is not null
	 * @post returns true if data[] represents a valid heap
	 */
	public static boolean isHeap(int data[]) {
		assert data != null;
		// Call recursive helper method starting at the root
		// of data (index 0)
		return heapHelper(data, 0);
	}

	/**
	 * @pre: data is not null
	 * @post: returns true if the subtree of data[] that has a root
	 *        at index i is a valid heap, and false otherwise.
	 */
	protected static boolean heapHelper(int data[], int i) {
		// base case: the heap is represented as a complete
		//            tree, so if we successfully get beyond
		//            the end of our array we, know we've
		//            checked the all the nodes from the root
		//            down to some leaf. So we are done!
		if (!inbounds(data, i))
			return true;

		// recursive case: to satisfy the heap property, the
		//                 left and right children (if they
		//                 exist) must have values that are
		//                 less than or equal to the root's
		//                 value, and the left and right
		//                 subtrees must also be heaps
		int rootValue = data[i];
		int leftChildIndex = left(i);
		int rightChildIndex = right(i);

		// Step 1: check that the root node satisfies the
		// local heap property
		if (inbounds(data, leftChildIndex) &&
			(rootValue > data[leftChildIndex])) {
				return false;
		}

		if (inbounds(data, rightChildIndex) &&
			(rootValue > data[rightChildIndex])) {
				return false;
		}

		// Step 2: recursively check the the left and right
		// subtrees are also heaps
		return heapHelper(data, leftChildIndex) &&
			   heapHelper(data, rightChildIndex);
	}

	/**
	 * @pre: data is not null
	 * @post: returns true if the subtree of data[] that has a root
	 *        at index i is a valid heap, and false otherwise.
	 */
	public static boolean isHeapIterative(int data[]) {
		for (int i = 0; i < data.length; i++) {
			// check that data at index i is in order w.r.t. children
			int rootValue = data[i];
			int leftChildIndex = left(i);
			int rightChildIndex = right(i);

			if (inbounds(data, leftChildIndex) &&
				rootValue > data[leftChildIndex]) {
					return false;
			}

			if (inbounds(data, rightChildIndex) &&
			 	rootValue > data[rightChildIndex]) {
				return false;
			}
		}

		return true;
	}

	
	/**
	 * @post returns true if index `i` is within the bounds of the
	 *       binary tree of integers that is implicitly
	 *       represented in the array `data`
	 */
	public static boolean inbounds(int data[], int i) {
		return i < data.length;
	}

	/**
	 * @post returns the index where the left child of a node at
	 *       index `i` would be located in a binary tree that is
	 *       implicitly represented in the array `data`
	 */
	public static int left(int i) {
		return (2 * i) + 1;
	}

	/**
	 * @post returns the index where the right child of a node at
	 *       index `i` would be located in a binary tree that is
	 *       implicitly represented in the array `data`
	 */
	public static int right(int i) {
		return (2 * i) + 2;
	}

	/**
	 * This main method constructs two arrays of integers, and
	 * checks whether or not those arrays are valid heaps.
	 */
	public static void main(String args[]) {
		int validHeap[] = { 1, 2, 3, 4, 5, 6 };
		System.out.print("Is validHeap a valid heap? ");
		System.out.println(isHeap(validHeap)); // true

		int invalidHeap[] = { 1, 5, 6, 4, 3, 2 };
		System.out.print("Is invalidHeap a valid heap? ");
		System.out.println(isHeap(invalidHeap)); // false
	}
}
