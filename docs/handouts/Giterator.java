import structure5.*;
import java.util.Iterator;

// An iterator that adds get() method to any iterator
public class Giterator<E> implements Iterator<E> {

  protected Iterator<E> elems;
  E currentValue;

  public Giterator(Iterator<E> iter) {
    elems = iter;
    setValue();
  }

  private void setValue() {
    if(elems.hasNext())
      currentValue = elems.next();
    else
      currentValue = null;
  }

  // All other methods dispatch to the underlying iterator.
  public boolean hasNext() { return currentValue != null ; }

  public E next() {
    E returnVal = currentValue;
    setValue();
    return returnVal;
  }
  public E get() { return currentValue; }

  // Print the remaining contents of a giterator until empty
  public static <E> void print(Giterator<E> g) {
    for (; g.hasNext();g.next()) {
      System.out.print(g.get() + " ");
    }
  }

  public static void main(String args[]) {

    Giterator<Integer> fi = new Giterator<Integer>(new FibonacciNumbers(30));
    print(fi);
    System.out.println();
    System.out.println();

    Giterator<Integer> pi = new Giterator<Integer>(new Primes(40));
    print(pi);
    System.out.println();
  }
}
