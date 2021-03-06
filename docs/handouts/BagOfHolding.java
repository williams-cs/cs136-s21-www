import structure5.Vector;

/**
 * An evolution of the Bag data structure to use a Vector.
 * This does two things:
 *
 *  1. It simplifies our code significantly. We can rely on the
 *  Vector's implementation of things like removing and resizing
 *
 *  2. It means our bag can be as big as we want, *WITHOUT* any extra
 *  work!
 */
public class BagOfHolding<E> {

	private Vector<E> theBag;
	
	private int realCapacity;


	// Create a bag of a given capacity
	public BagOfHolding(int capacity) {
		theBag = new Vector<E>(capacity);
		realCapacity = capacity * 10;
	}

	public boolean isFull() {
		return theBag.size() >= realCapacity;
	}

	public boolean isEmpty() {
		return theBag.isEmpty();
	}

	// Add an item in an available location and return true
	// Or bag is full so return false
	public boolean add(E item) {
		if (! isFull()) {
			theBag.add(item);
			return true;
		}
		return false;
	}

	// Remove an item from the bag and return it
	// (user can't choose which item)
	// Return false if bag is empty
	public E remove() {
		return theBag.removeLast();
	}

	public String toString() {
		String result = "";
		for(int i = 0; i < theBag.size(); i++){
			result += theBag.get(i) + " ";
		}
		return result;
	}

}
