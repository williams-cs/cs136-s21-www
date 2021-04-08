import structure5.*;
import java.util.Scanner;
import java.util.Iterator;

/*  A class to demonstrate how a BinaryTree can be converted
    to an array representation of the tree and vice-versa

    Array entries not corresponding to tree nodes are null
*/

public class ArrayTrees {

  /*
    Fills theArray with tree values from theTree, starting with putting
    value of root in location index of theArray
  */

  public static <E> void fillArrayFromTree(BinaryTree<E> theTree,
                                        E[] theArray) {
    fillArrayFromTree(theTree, theArray, 0);
}

  public static <E> void fillArrayFromTree(BinaryTree<E> theTree,
                                        E[] theArray, int index) {
    if( theTree.isEmpty()) return;

    // put root value in location index
    theArray[index] = theTree.value();
    // recursively fill portions of theArray with subtree values
    fillArrayFromTree(theTree.left(), theArray, 2 * index + 1);
    fillArrayFromTree(theTree.right(), theArray, 2 * index + 2);
  }

  /*
    Build a BinaryTree from an array representation of the tree.
    The element at location index of theArray becomes the root
    of the tree. Then the left and right subtrees are built
    recursively starting at the locations in theArray of the
    children of the root
  */

  public static <E> BinaryTree<E> fillTreeFromArray(E[] theArray) {
    return fillTreeFromArray(theArray, 0);
  }

  public static <E> BinaryTree<E> fillTreeFromArray(
                                  E[] theArray, int index) {
    if(index >= theArray.length || theArray[index] == null)
      return new BinaryTree<E>();
    return new BinaryTree<E>(theArray[index],
                              fillTreeFromArray(theArray, 2 * index + 1),
                              fillTreeFromArray(theArray, 2 * index + 2));
  }

  public static void printSeparator() {
    System.out.println();
    System.out.println("-------------------------");
    System.out.println();

  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // Read in a BinaryTree from standard input
    BinaryTree<String> namesTree = TreeFile.readTree(sc);
    // Print out a representation of the tree
    System.out.println(namesTree.treeString());

    printSeparator();

    // Use an iterator to print the nodes in level order
    System.out.println("The tree as stored (in level order) in BinaryTree:");
    for(Iterator<String> i = namesTree.levelorderIterator(); i.hasNext(); )
      System.out.println(i.next());

    printSeparator();

    // create a large enough array to hold the tree
    int arraySize = ((int) Math.pow(2, 1 + namesTree.height())) - 1;
    String[] namesArray = new String[arraySize];

    // fill the array using the tree
    fillArrayFromTree(namesTree, namesArray);

    // print out the non-null entries
    // This should also be level-order
    System.out.println("The tree as stored in array (with empty cells as ***!):");
    for(String s : namesArray)
      if(s != null) System.out.println(s);
      else System.out.println("***");

    printSeparator();

    // build a new tree from the array and print it out
    System.out.println("The tree as copied from array into " +
                        "new BinaryTree (in level order)");

    BinaryTree<String> newNamesTree = fillTreeFromArray(namesArray);
    for(Iterator<String> i = newNamesTree.levelorderIterator(); i.hasNext(); )
      System.out.println(i.next());

    printSeparator();

    // Compare the original BinaryTree to the new one
    System.out.println("Compare original BinaryTree to new one.");
    if(TreeFile.equals(namesTree, newNamesTree))
      System.out.println("The tree are the same!");
    else
      System.out.println("The tree are different!");
  }
}
