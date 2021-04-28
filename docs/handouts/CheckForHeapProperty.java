// Method to check for heap property
import structure5.*;

public class CheckForHeapProperty {

  // Do all nodes in tree (including tree) satisfy heap property?
  // If tree is a root (no parent), then it satisfies heap property.
  // Otherwise its value must be at least as big as its parent's value.
  public static <E extends Comparable<E>> boolean isHeap(BinaryTree<E> tree) {
    if(tree.isEmpty() ) return true;

    if(!isHeap(tree.left())) return false;  // Check left subtree
    if(!isHeap(tree.right())) return false; // Check right subtree
    if(tree.parent() == null) return true;  // Check for root
    return tree.value().compareTo(tree.parent().value()) >= 0;  // Node test
  }

  // Array version
  // Note: Only checks array elements "reachable" from index 0
  // There could be "hidden orphans": An orphan is a location
  // in the array that contains a value, but whose "parent"
  // location is null
  public static <E extends Comparable<E>> boolean isHeap(E[] arr) {
    return isHeapHelper(arr,0);
  }

  // Array version helper: Does slice beginning at start satisfy heap property?
  public static <E extends Comparable<E>> boolean isHeapHelper(E[] arr, int start){
    if(start >= arr.length || arr[start] == null) return true;

    if(!isHeapHelper(arr, 2*start+1)) return false;   // Check left subtree
    if(!isHeapHelper(arr, 2*start+2)) return false;   // Check right subtree
    if(start == 0) return true;                       // Check for root
    return arr[start].compareTo(arr[(start-1)/2]) >= 0;
  }

  public static <E> void buildArrayFromTree(BinaryTree<E> tree,
                                            E[] arr) {
    buildVectorFromTreeHelper(tree, arr, 0);
  }

  // Fill in array putting value of tree at location start of vec
  public static <E> void buildVectorFromTreeHelper(BinaryTree<E> tree,
                                            E[] arr, int start) {
    if(tree.isEmpty()) return;
    arr[start] = tree.value();
    buildVectorFromTreeHelper(tree.left(), arr, 2*start+1);
    buildVectorFromTreeHelper(tree.right(), arr, 2*start+2);
  }

  public static void main(String[] args) {
    if(args.length > 0) usage();
    // empty tree t1
    BinaryTree<Integer> t1 = new BinaryTree<Integer>();
    System.out.println("T1 is a heap: " + isHeap(t1));
    BinaryTree<Integer> t2 = new BinaryTree<Integer>(10, t1, t1);
    System.out.println("T2 is a heap: " + isHeap(t2));
    BinaryTree<Integer> t3 = new BinaryTree<Integer>(5, t2, t1);
    System.out.println("T3 is a heap: " + isHeap(t3));
    BinaryTree<Integer> t4 = new BinaryTree<Integer>(3, t1, t3);
    System.out.println("T4 is a heap: " + isHeap(t4));
    BinaryTree<Integer> t5 = new BinaryTree<Integer>(20, t1, t1);
    System.out.println("T5 is a heap: " + isHeap(t5));
    BinaryTree<Integer> t6 = new BinaryTree<Integer>(1, t5, t4);
    System.out.println("T6 is a heap: " + isHeap(t6));

    // Make an array big enough to encode tree
    Integer[] t6array = new Integer[1 << t6.size()];

    buildArrayFromTree(t6,t6array);
    System.out.println("T6 array is a heap: " + isHeap(t6array));

    t4.setValue(7);
    System.out.println("T6 is a heap: " + isHeap(t6));

    // Rebuild array
    for(int i = 0; i < t6array.length; i++) t6array[i] = null;
    buildArrayFromTree(t6,t6array);
    System.out.println("T6 array is a heap: " + isHeap(t6array));


  }

  public static void usage() {
    System.out.println("Usage: CheckForHeapProperty");
    System.exit(0);
  }

}
