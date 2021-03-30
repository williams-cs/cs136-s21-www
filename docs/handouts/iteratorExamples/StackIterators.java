import structure5.*;
import java.util.Iterator;

public class StackIterators {

  // Give usage then quit
  public static void usage() {
    System.out.println("Usage: java StackIterators string1 string2 ...");
    System.exit(0);
  }

  public static <E> void printStack(Stack<E> st) {
    for(E s : st)
      System.out.print(s + " ");
    System.out.println();
  }

  public static void main(String[] args) {
    if(args.length == 0) usage();

    Stack<String> as = new StackArray<String>(args.length);
    Stack<String> ls = new StackList<String>();

    for(String s : args) {
      as.push(s);
      ls.push(s);
    }

    printStack(as);
    printStack(ls);
  }
}
