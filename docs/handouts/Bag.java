/**
 * A simple data structure to hold some stuff
 * An item can be added
 * An (unspecified) item can be removed
 */
public class Bag<E> {

	private Object[] theBag;

	// The bag is filled starting at index 0
	// nextFreeSlot is location of the first empty slot in bag
	private int nextFreeSlot;


	// Create a bag of a given capacity
	public Bag(int capacity) {
		theBag = new Object[capacity];
		nextFreeSlot = 0;
	}

	public boolean isFull() {
		return nextFreeSlot == theBag.length;
	}

	public boolean isEmpty() {
		return nextFreeSlot == 0;
	}

	// Add an item in an available location and return true
	// Or bag is full so return false
	public boolean add(E item) {
		if(! isFull()) {
			theBag[nextFreeSlot] = item;
			nextFreeSlot++;
			return true;
		}
		return false;
	}

	// Remove an item from the bag and return it
	// (user can't choose which item)
	// Return false if bag is empty
	public E remove() {
		if(isEmpty()) return null;

		nextFreeSlot--;
		// Casting to generic type generates a compiler warning
		// You can suppress it (if desired) by adding
		// @SuppressWarnings("unchecked") before the declaration
		@SuppressWarnings("unchecked") E result = (E) theBag[nextFreeSlot];
		theBag[nextFreeSlot] = null;
		return result;
	}

	public String toString() {
		String result = "";
		if(! isEmpty()) {
			for(int i = 0; i < nextFreeSlot; i++){
				result += theBag[i] + " ";
			}
		}
		return result;
	}
}
