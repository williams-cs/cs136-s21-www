import structure5.*;
import java.util.Iterator;

public class SLLIterator<E> extends AbstractIterator<E> {

  protected Node<E> head, current;

  public SLLIterator(Node<E> head) {
    this.head = head;
    reset();
  }

  public SLLIterator(SLLIterator<E> other)
  {
      head = other.head;
      current = other.current;
  }

  public void reset() {
    current = head;
  }

  public E next() {
    E value = current.value();
    current = current.next();
    return value;
  }

  public boolean hasNext() {
    return current != null;
  }

  public E get() {
    return current.value();
  }

}
