import structure5.*;
import java.util.Iterator;

// An iterator that filters out a value from another iterator
public class SkipIterator<E> extends AbstractIterator<E> {

    protected AbstractIterator<E> elems;
    E value;
    
    public SkipIterator(Iterator<E> iter, E skipMe) {
	elems = (AbstractIterator<E>) iter;
	value = skipMe;
	reset();
    }

    // All other methods dispatch to the underlying iterator.
    public boolean hasNext() { return elems.hasNext(); }

    public void reset() {
	elems.reset();
	skip();
    }

    public E next() {
	E returnVal = elems.next();
	skip();
	return returnVal;
    }
    public E get() { return elems.get(); }

    private void skip() {
	// make sure next element is not value to be skipped
	while(elems.hasNext() && elems.get().equals(value)) elems.next();
    }

    // Print the contents of an interator until empty
    public static <E> void print(Iterator<E> iter) {
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
    }

    public static void main(String args[]) {

	// put the args into a Vector and a SinglyLinkedList
        Vector<String> argVector = new Vector<String>();
        SinglyLinkedList<String> argSLL = new SinglyLinkedList<String>();
        for (int i = 0; i < args.length; i++) {
            argVector.add(args[i]);
            argSLL.add(args[i]);
        }

        System.out.print("The Vector: ");
        print(argVector.iterator());
        System.out.println();

        System.out.print("The Vector with skip: ");
	AbstractIterator<String> skipper =
	    new SkipIterator<String>(argVector.iterator(),args[0]);
        print(skipper);
        System.out.println();

        System.out.print("The Vector: ");
        print(argVector.iterator());
        System.out.println();

        System.out.print("The Vector with skip: ");
	skipper.reset();  // Don't need a new one!
        print(skipper);
        System.out.println();

        System.out.print("The SLL: ");
        print(argSLL.iterator());
        System.out.println();

        System.out.print("The SLL with skip: ");
        print(new SkipIterator<String>(argSLL.iterator(),args[0]));
        System.out.println();
    }
}
