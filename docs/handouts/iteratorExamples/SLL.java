import structure5.*;
import java.util.Iterator;

// A simple singly-linked list class
// Created by eviscerating the structure5 SinglyLinkedList class

public class SLL<E> extends AbstractList<E>
{
    protected int count;                    // list size

    protected Node<E> head; // ref. to first element

    public SLL()
    {
        head = null;
        count = 0;
    }

    /**
     * Add an object to tail of list.
     */
    public void add(E value)
    {
        addLast(value);
    }

    /**
     * Add a value to head of list.
     */
    public void addFirst(E value)
    {
        head = new Node<E>(value, head);
        count++;
    }

    /**
     * Remove a value from first element of list.
     */
    public E removeFirst()
    {
        Node<E> temp = head;
        head = head.next(); // move head down list
        count--;
        return temp.value();
    }

    /**
     * Add a value to tail of list.
     */
    public void addLast(E value)
    {
        // location for new value
        Node<E> temp = new Node<E>(value,null);
        if (head != null)
        {
            // pointer to possible tail
            Node<E> finger = head;
            while (finger.next() != null)
            {
                finger = finger.next();
            }
            finger.setNext(temp);
        } else head = temp;
        count++;
    }

    /**
     * Remove last value from list.
     */
    public E removeLast()
    {
        Node<E> finger = head;
        Node<E> previous = null;
        Assert.pre(head != null,"List is not empty.");
        while (finger.next() != null) // find end of list
        {
            previous = finger;
            finger = finger.next();
        }
        // finger is null, or points to end of list
        if (previous == null)
        {
            // has exactly one element
            head = null;
        }
        else
        {
            // pointer to last element is reset
            previous.setNext(null);
        }
        count--;
        return finger.value();
    }

    /**
     * Fetch first element of list.
       */
    public E getFirst()
    {
        return head.value();
    }

    /**
     * Fetch last element of list.
     */
    public E getLast()
    {
        Node<E> finger = head;
        Assert.condition(finger != null,"List is not empty.");
        while (finger != null &&
               finger.next() != null)
        {
            finger = finger.next();
        }
        return finger.value();
    }

    /**
     * Check to see if a value is in list.
     */
    public boolean contains(E value)
    {
        Node<E> finger = head;
        while (finger != null &&
               !finger.value().equals(value))
        {
            finger = finger.next();
        }
        return finger != null;
    }

    /**
     * Remove a value from list.  At most one value will be removed.
     */
    public E remove(E value)
    {
        Node<E> finger = head;
        Node<E> previous = null;
        while (finger != null &&
               !finger.value().equals(value))
        {
            previous = finger;
            finger = finger.next();
        }
        // finger points to target value
        if (finger != null) {
            // we found element to remove
            if (previous == null) // it is first
            {
                head = finger.next();
            } else {              // it's not first
                previous.setNext(finger.next());
            }
            count--;
            return finger.value();
        }
        // didn't find it, return null
        return null;
    }

    /**
     * Determine number of elements in list.
     */
    public int size()
    {
        return count;
    }

    /**
     * Remove all values from list.
     */
    public void clear()
    {
        head = null;
        count = 0;
    }

    /**
     * Get value at location i.
     */
    public E get(int i)
    {
        if (i >= size()) return null;
        Node<E> finger = head;
        // search for ith element or end of list
        while (i > 0)
        {
            finger = finger.next();
            i--;
        }
        return finger.value();
    }

    /**
     * Set value stored at location i to object o, returning old value.
     */
    public E set(int i, E o)
    {
        if (i >= size()) return null;
        Node<E> finger = head;
        // search for ith element or end of list
        while (i > 0)
        {
            finger = finger.next();
            i--;
        }
        // get old value, update new value
        E result = finger.value();
        finger.setValue(o);
        return result;
    }

    /**
     * Insert value at location.
     */
    public void add(int i, E o)
    {
        Assert.pre((0 <= i)  && (i <= size()),
                   "Index in range.");
        if (i == size()) {
            addLast(o);
        } else if (i == 0) {
            addFirst(o);
        } else {
            Node<E> previous = null;
            Node<E> finger = head;
            // search for ith position, or end of list
            while (i > 0)
            {
                previous = finger;
                finger = finger.next();
                i--;
            }
            // create new value to insert in correct position
            Node<E> current =
                new Node<E>(o,finger);
            count++;
            // make previous value point to new value
            previous.setNext(current);
        }
    }

    /**
     * Remove and return value at location i.
     */
    public E remove(int i)
    {
        Assert.pre((0 <= i) && (i < size()),
                   "Index in range.");
        if (i == 0) return removeFirst();
        else if (i == size()-1) return removeLast();
        Node<E> previous = null;
        Node<E> finger = head;
        // search for value indexed, keep track of previous
        while (i > 0)
        {
            previous = finger;
            finger = finger.next();
            i--;
        }
        // in list, somewhere in middle
        previous.setNext(finger.next());
        count--;
        // finger's value is old value, return it
        return finger.value();
    }

    /**
     * Determine first location of a value in list.
     */
    public int indexOf(E value)
    {
        int i = 0;
        Node<E> finger = head;
        // search for value or end of list, counting along way
        while (finger != null && !finger.value().equals(value))
        {
            finger = finger.next();
            i++;
        }
        // finger points to value, i is index
        if (finger == null)
        {   // value not found, return indicator
            return -1;
        } else {
            // value found, return index
            return i;
        }
    }

    /**
     * Determine last location of a value in list.
     */
    public int lastIndexOf(E value)
    {
        int result = -1;        // assume not found, return -1
        int i = 0;
        Node<E> finger = head;
        // search for last matching value, result is desired index
        while (finger != null)
        {
            // a match? keep track of location
            if (finger.value().equals(value)) result = i;
            finger = finger.next();
            i++;
        }
        // return last match
        return result;
    }

    /**
     * Returns an iterator traversing list from head to tail.
     */
    public Iterator<E> iterator()
    {
        return new SLLIterator<E>(head);
    }

    /**
     * Construct a string representing list.
       */
    public String toString()
    {
        StringBuffer s = new StringBuffer();
        s.append("<SLL:");
        SLLIterator<E> li = (SLLIterator<E>) iterator();
        while (li.hasNext())
        {
            s.append(" "+li.next());
        }
        s.append(">");
        return s.toString();
    }
}
