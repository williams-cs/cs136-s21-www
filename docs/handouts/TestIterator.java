
import structure5.*;
import java.util.Iterator;

public class TestIterator {

  public static void main (String args[]) {

    // put the args into a Vector
    List<String> list1 = new Vector<String>();
    List<String> list2 = new Vector<String>();
    List<String> list3 = new SinglyLinkedList<String>();
    List<String> list4 = new SinglyLinkedList<String>();
    for (int i = 0; i < args.length; i++) {
      list1.add(args[i]);
      list2.add(args[i]);
      list3.add(args[i]);
      list4.add(args[i]);
    }

    // "fancy" syntax for easily using iterators in for loops
    System.out.println("New syntax:");
    for (String s: list1) {
      System.out.print(s+" ");
    }
    System.out.println("\n");

    // BE CAREFUL IF YOU CHANGE UNDERLYING DATA STRUCTURE DURING ITERATION!

    // what does this do?
    // What kind of list is list1?

    // System.out.println("Add to end of Vector:");
    //
    // Iterator<String> infIt = list1.iterator();
    // while(infIt.hasNext() && list1.size() < 30) {
    //   String s = infIt.next();
    //   list1.add(s);
    //   System.out.print(s+" ");
    // }
    // System.out.println("\n");
    // System.out.println(list1);

    // // what does this do?
    // What kind of list is list4?

    // System.out.println("Add to end of SLL:");
    // Iterator<String> it4 = list4.iterator();
    // while(it4.hasNext() && list4.size() < 30) {
    //   String s = it4.next();
    //   list4.addLast(s);
    //   System.out.print(s+" ");
    // }
    // System.out.println("\n");
    // System.out.println(list4);

    // What does this do?
    // What kind of list is list2?

    // System.out.println("Add to front of vector:");
    // Iterator<String> it2 = list2.iterator();
    // while(it2.hasNext() && list2.size() < 20) {
    //   String s = it2.next();
    //   list2.addFirst(s);
    //   System.out.print(s+" ");
    // }
    // System.out.println("\n");
    // System.out.println(list2);

    // what does this do?
    // What kind of list is list3?

    // System.out.println("Add to front of SLL:");
    // Iterator<String> it3 = list3.iterator();
    // while(it3.hasNext() && list3.size() < 20) {
    //   String s = it3.next();
    //   list3.addFirst(s);
    //   System.out.print(s+" ");
    // }
    // System.out.println("\n");
    // System.out.println(list3);

  }

}
