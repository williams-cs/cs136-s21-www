/**
 * The knapsack problem is a "classic" computer science problem with
 * several variants. This class provides a recursive method that
 * solves the following vesrion of the knapsack problem:
 *
 *  - We have a knapsack with some fixed capacity (`int capacity`).
 *
 *  - We have a set of items that we want to put in our knapsack
 *    (`int[] items`), and those items each have a cost (the cost of
 *    the ith item is `items[i]`).
 *
 * Our goal is to find out how full we can make the knapsack without
 * overflowing it.
 *
 * The approach we take is to consider *every possible combination* of
 * items, and then return the maximum size among all those
 * combinations.
 */
import java.util.Scanner;

public class Knapsack {

	/**
	 * @param capacity the total capacity of our knapsack
	 * @param items The set of things we want to fill our knapsack
	 *        with
	 * @return The maximum size (after considering all possible
	 *         combinations of items) that we can fit into our
	 *         knapsack
	 */
	public static int solveKnapsack(int capacity, int[] items) {
		// The provided method signature doesn't have all the
		// information we need to keep track of our progress
		// through our recursive calls, so we create a helper
		// method that also contains an index corresponding
		// to "which item we're currently considering". We
		// start considering the first item, hence we pass in
		// index `0`.
		return knapsackHelper(capacity, items, 0);
	}

	/**
	 * Return the best solution within capacity using only items
	 * from range `index`...`items.length`
	 *
	 * Our knapsack has some `capacity`, and we model the concept
	 * of "adding something to our knapsack" by "reducing the
	 * knapsack's capacity by the size of that item" (i.e.,
	 * we recursively call our helpwer with (capacity-items[index]))
	 *
	 * Our strategy is to walk through the elements one at a
	 * time. For each element, we have two options:
	 *
	 *   - leave it out of our knapsack: we continue checking the
	 *     remaining elements by incrementing `index` and
	 *     recursively calling knapsackHelper with the same `capacity`
	 *
	 *   - put it in our knapsack *if there is enough room*: (to
	 *     check if there is enough room, we compare the item's
	 *     cost (`items[index]`)to the current `capacity`).  We
	 *     model adding the item to our knapsack by subtracting
	 *     its cost (`items[index]`) from `capacity`, then
	 *     continue considering the remaining elements by
	 *     recursively calling the helper with the next index.
	 */
	protected static int knapsackHelper(int capacity, int[] items, int index) {
		// Base cases: our knapsack is full, or we've considered every item.
		// We don't add anything to our knapsack, so we return 0.
		if (capacity <= 0 || index >= items.length)
			return 0;

		// Now we calculate the best solution that excludes the
		// current item, and if it is legal, we calculate the best
		// solution that includes the current item

		int excludeItem = knapsackHelper(capacity, items, index + 1);


		// it may be illegal, in which case return 0
		int includeItem = 0;

		if (capacity >= items[index])
			// add the current item to the total weight of the
			// "rest" of the items
			includeItem = items[index] +
				knapsackHelper(capacity - items[index],
					       items, index + 1);

		// return the best (i.e., the biggest)
		return Math.max(includeItem, excludeItem);
	}


	public static void main(String[] args) {

		if(args.length==0) usage();

		int[] values = checkArgVal(args);
		Scanner in = new Scanner(System.in);

		System.out.println("Enter a capacity");
		int capacity = in.nextInt();
		while( capacity > 0) {
			System.out.print("For the values: ");
			for(int v : values) System.out.print(v + " ");
			System.out.println();
			System.out.println("And capacity = " + capacity);
			System.out.println("The optimal packing value is: " +
						 solveKnapsack(capacity, values));
			System.out.println("Enter a capacity");
			capacity = in.nextInt();
		}

	}


	// ********** command line helpers **********

	// Give usage then quit
	public static void usage() {
		System.out.println("Usage: java Knapsack num_1 num_2 ... num_k (all positive)");
		System.exit(0);
	}

	// Convert String to int and quit on fail
	public static int[] checkArgVal(String[] args) {
		int[] inputs = new int[args.length];
		try {
			for( int i = 0; i < args.length; i++ ) {
				inputs[i] = Integer.valueOf(args[i]);
			}
		}
		catch( NumberFormatException e ) { usage(); }

		return inputs;
	}

}
