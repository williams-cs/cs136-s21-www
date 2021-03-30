
import structure5.*;
import java.util.Iterator;


/*
 * An iterator that reverses the order of elements
 * returned from another iterator.
 */
class ReverseIterator<E> extends AbstractIterator<E> {

    protected AbstractIterator<E> elems;
    
    /**
     * Create a new interator for the elements in reverse,
     * storing them in a SLL.
     */
    public ReverseIterator(Iterator<E> iter) {
        SinglyLinkedList<E> list = new SinglyLinkedList<E>();
        while (iter.hasNext()) {
            list.addFirst(iter.next());
        }
        elems = (AbstractIterator<E>)list.iterator();
    }

    // All other methods dispatch to the underlying iterator.
    public boolean hasNext() { return elems.hasNext(); }
    public void reset() { elems.reset(); }
    public E next() { return elems.next(); }
    public E get() { return elems.get(); }

    // Print the contents of an interator until empty
    public static <E> void print(Iterator<E> iter) {
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
    }

    public static void main(String args[]) {

        // put the args into a Vector
        Vector<String> argVector = new Vector<String>();
        for (int i = 0; i < args.length; i++) {
            argVector.add(args[i]);
        }

        System.out.print("forward: ");
        print(argVector.iterator());
        System.out.println();

        System.out.print("reverse: ");
        print(new ReverseIterator<String>(argVector.iterator()));
        System.out.println();
    }
}
