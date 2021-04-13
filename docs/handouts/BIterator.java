import structure5.*;
import java.util.Iterator;

public class BIterator extends AbstractIterator<String> {

	/* n is the number whose bits we will to iterate through, from
	 least significant bit to most significant bit (i.e., right to
	 left) */
	protected int n;

	/* current keeps track of the "next bit" to yield.
	 In java, ints have 32 bits, so we can continue to yield
	 new bits as long as 0 <= curreent < 32 */
	protected int current;

	/**
	 * Constructs a new BIterator that will dispense the bits
	 * of n
	 */
	public BIterator(int n) {
		// store our value, then "reset" our iteration
		// back to the first bit (current = 0)
		this.n = n;
		reset();
	}

	public void reset() {
		current = 0;
	}

	/**
	 * This organization lets us reuse code:
	 *  - get() extracts the current value
	 *  - the rest of "next" advances the iteration
	 */
	public String next() {
		String nextBit = get();
		current++;
		return nextBit;
	}

	/**
	 * Since an int has 32 bits, we can only call next()
	 * 32 times. (Each next call advances current by 1)
	 */
	public boolean hasNext() {
		return current < 32;
	}

	/**
	 * Strategy: construct a "mask" in order to isolate the bit
	 * value at the target position.
	 *
	 * Then we either yield a "0" or a "1", depending on the
	 * target bit's value.
	 */
	public String get() {
		// shift a `1` by `current` places to the left so we can
		// "extract" the target bit. For example:
		//    1 << 0 = 000...0001 (in binary)
		//    1 << 1 = 000...0010 (in binary)
		//    1 << 2 = 000...0100 (in binary)
		// etc.
		// whichBit stores the "mask" we will use to extract
		// the bit
		int whichBit = 1 << current;

		// Since `whichBit` has a 1 in exactly one bit position,
		// the expression (n & whichBit) will isolate that bit
		// from our original int `n`.
		// In other words, if `n` had a 1 at that position, the result
		// will have a 1 at that bit position and 0 everywhere else.
		// If `n` had a 0 at that bit position, then the result will
		// be all 0's. So we compare the "masked" value to the "mask";
		// if they match, then we know we had a 1 at that position!
		if ((n & whichBit) == whichBit) {
			return "1";
		}
		return "0";
	}

	/**
	 * Revisit our ideas from the recursion lab as an example.
	 * In this "version", we shrink `n` and examine it's rightmost
	 * bit.
	 * Note: Only works correctly on NON-NEGATIVE numbers
	 */
	public static String numInBinary(int n) {
		if (n <= 1) {
			// (Java trick to convert an int to a String)
			return "" + n;
		}

		// * divide `n` by 2 in order to advance to the next digit
		// * mod `n` with 2 so we can determine the value of the
		//   current digit.
		// Since numInBinary(int n) returns a String, this
		// expression will yield a String (it converts
		// (n % 2) to a String using the same trick as above).
		return numInBinary(n / 2) + (n % 2);
	}

	/**
	 * Revisit our ideas from the recursion lab as an example.
	 * In this "version", we keep `n` the same, but we examine
	 * the bit at position `i` in each iteration of our loop
	 */
	public static String numInBinary(int n, int width) {
		String result = "";
		for(int i = 0; i < width; i++)
			if ((n & (1<<i)) == 0)
				result = 0 + result;
			else
				result = 1 + result;
		return result;
	}

	public static final int MAX_BITS = 32;

	public static void main(String args[]) {
		if (args.length ==0 ) {
			System.out.println("please provide 1 Integer argument, and an optional width (max of 32)");
			return;
		}

		int n = Integer.parseInt(args[0]);
		int width = MAX_BITS;
		if(args.length > 1) {
			width = Integer.parseInt(args[1]);
			if(width > MAX_BITS) width = MAX_BITS;
		}

		System.out.print("numInBinary(" + n + "):\n\t");
		System.out.println(numInBinary(n));

		System.out.print(width + "-bit width numInBinary(" + n + "):\n\t");
		System.out.println(numInBinary(n, width));

		System.out.println("Iterating through all (32) bits in " + n + " (note reverse order!):");
		int count = 0;
		BIterator it = new BIterator(n);
		while (it.hasNext()) {
			System.out.print(it.next());
		}
		System.out.println();
	}

}
