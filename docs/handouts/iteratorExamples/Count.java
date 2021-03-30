import structure5.*;
import java.util.Iterator;

public class Count {

  public static<E> int countWhile (List<E> data, E o) {
    int count = 0;
    Iterator<E> iter = data.iterator();
    while (iter.hasNext())
    if(o.equals(iter.next())) count++;
    return count;
  }


  public static<E> int countFor1 (List<E> data, E o) {
    int count = 0;
    for(Iterator<E> i= data.iterator(); i.hasNext();)
    if(o.equals(i.next())) count++;
    return count;
  }

  public static<E> int countFor2 (List<E> data, E o) {
    int count = 0;
    for(AbstractIterator<E> i= (AbstractIterator<E>) data.iterator();
    i.hasNext(); i.next()) {
      if(o.equals(i.get())) count++;
    }
    return count;
  }

  public static<E> int countFor3 (List<E> data, E o) {
    int count = 0;
    for(E current : data)
    if(o.equals(current)) count++;
    return count;
  }

  // Give usage then quit
  public static void usage() {
    System.out.println("Usage: java Count string1 string2 ...");
    System.exit(0);
  }

  public static void main(String[] args) {
    List<String> li = new SinglyLinkedList<String>();

    for(String s : args) li.add(s);

    System.out.println(countWhile(li, "dog"));
    System.out.println(countFor1(li, "dog"));
    System.out.println(countFor2(li, "dog"));
    System.out.println(countFor3(li, "dog"));
  }
}
