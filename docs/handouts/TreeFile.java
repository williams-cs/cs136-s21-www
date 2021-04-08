import structure5.*;
import java.util.Scanner;

// Allow reading and writing of trees
// Assumes reading and writing in pre-order
public class TreeFile {

  // used for labeling nodes
  final static String EMPTY_TREE_SYM = "***";


  // Pre: There's more input from scanner
  public static BinaryTree<String> readTree(Scanner sc) {
    String next = sc.nextLine();
    // Base case: Build an empty tree
    if(next.equals(EMPTY_TREE_SYM))
      return new BinaryTree<String>();

    // Otherwise read in left and right subtrees and build
    // new tree with root value and left/right subtrees
    BinaryTree<String> left = readTree(sc);
    BinaryTree<String> right = readTree(sc);
    return new BinaryTree<String>(next, left, right);
  }

  public static void writeTree(BinaryTree<String> t) {
    // Base case: Write special code for empty tree
    if(t.isEmpty())
      System.out.println(EMPTY_TREE_SYM);
    else {
      // Otherwise: write root value, then write left subtree
      // followed by right subtree
      System.out.println(t.value());
      writeTree(t.left());
      writeTree(t.right());
    }
  }

  public static <E> BinaryTree<E> copyTree(BinaryTree<E> t) {
    // Base case: empty tree --> return a new empty tree
    if(t.isEmpty()) return new BinaryTree<E>();
    // Otherwise: Make a copy of the root and make its children
    // copies of the left and right subtrees of t
    return new BinaryTree<E>( t.value(),
                                copyTree(t.left()),
                                copyTree(t.right()));
  }

  public static <E> boolean equals(BinaryTree<E> t1, BinaryTree<E> t2) {
    // Base case: Both trees empty
    if(t1.isEmpty() && t2.isEmpty()) return true;
    // Base case: one tree empty and the other non-empty
    if(t1.isEmpty() || t2.isEmpty()) return false;

    return t1.value().equals(t2.value()) &&
            equals(t1.left(), t2.left()) &&
            equals(t1.right(),t2.right());
  }
  /** main method to set up an answer */
  public static void main(String[] args) {

    BinaryTree<String> names = readTree(new Scanner(System.in));
    System.out.println(names.treeString());

    writeTree(names);

    BinaryTree<String> otherNames = copyTree(names);

    System.out.println(equals(names,otherNames));

    BinaryTree<String> finger = otherNames;
    finger = finger.left();
    // Chop off a sub tree
    finger.setRight(new BinaryTree<String>());

    System.out.println(otherNames.treeString());
    System.out.println(equals(names,otherNames));

  }
}




/*
BinaryTree<String> arya = new BinaryTree<String>("Arya");
BinaryTree<String> brienne = new BinaryTree<String>("Brienne");
BinaryTree<String> jon = new BinaryTree<String>("Jon");
BinaryTree<String> melisandre = new BinaryTree<String>("Melisandre");
BinaryTree<String> tyrion = new BinaryTree<String>("Tyrion");


BinaryTree<String> bran = new BinaryTree<String>("Bran", arya, brienne);
BinaryTree<String> jaime = new BinaryTree<String>("Jaime", bran, jon);
BinaryTree<String> sansa = new BinaryTree<String>("Sansa", melisandre, tyrion );
BinaryTree<String> root = new BinaryTree<String>("Jorah", jaime, sansa);
*/
