// Some recursive methods for induction proofs
import structure5.Node;

public class Induction {

  // Recursively print all values in a chain
  public static void print(Node<String> n) {
    if(n==null) {
      System.out.println();
      return;
    }
    System.out.print(n.value() + " ");
    print(n.next());
  }

  /*
   * reverse(String s)  --> String
   * return a string that has the charcters of s in reverse order
   */

  public static String reverse(String s) {
    if(s.length() <= 1) return s;
    int last = s.length() - 1;
    return s.substring(last) + reverse(s.substring(0,last));
  }
  /*
   * removeAll( node , String v) --> node
   * remove all nodes containing value v from the chain
   *  beginning at node n
   * return a reference to the head of the chain
   */

  public static Node<String> removeAll(Node<String> n, String v) {
    if( n == null ) return n;

    if(v.equals(n.value()) ) return removeAll(n.next(),v);
    n.setNext(removeAll(n.next(), v));
    return n;
  }

  public static void main(String[] args) {

    System.out.println(args[0] + " : " + reverse(args[0]));

    // Lazy list building: Built in reverse order of args[] !
    Node<String> words = null;
    for(String s : args)
    words = new Node<String>(s,words);

    print(words);
    // Remove all copies of the first value in the list from the list
    words = removeAll(words,words.value());
    if(words != null) print(words);

  }
}
