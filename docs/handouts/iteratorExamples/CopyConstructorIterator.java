import structure5.*;
import java.util.Iterator;

public class CopyConstructorIterator {

  public static void main(String[] args) {

    SLL<String> list = new SLL<String>();

    for(String s : args) {
      list.add(s);
    }

    System.out.println(list);

    SLLIterator<String> outer = (SLLIterator<String>) list.iterator();
    while(outer.hasNext()) {
      String nextOuter = outer.next();
      Iterator<String> inner = new SLLIterator<String>(outer);
      while(inner.hasNext()) {
        String nextInner = inner.next();
        System.out.println(nextOuter + " : " + nextInner);
      }
    }
  }
}
