import structure5.*;

public class TreeVisitor {

  // used for labeling nodes
  static int nodeNumber = 0;

  // A method that does something with a node
  public static <E> void touch(BinaryTree<E> t) {
    nodeNumber++;
    System.out.println("" + nodeNumber + " : " + t.value());
  }

  public static <E> void preOrder(BinaryTree<E> t) {
    if (t.isEmpty()) return;
    touch(t);
    preOrder(t.left());
    preOrder(t.right());
  }

  public static <E> void inOrder(BinaryTree<E> t) {
    if (t.isEmpty()) return;
    inOrder(t.left());
    touch(t);
    inOrder(t.right());
  }

  public static <E> void postOrder(BinaryTree<E> t) {
    if (t.isEmpty()) return;
    postOrder(t.left());
    postOrder(t.right());
    touch(t);
  }

  public static <E> void levelOrder(BinaryTree<E> t) {
    if (t.isEmpty()) return;
    Queue<BinaryTree<E>> q = new QueueList<BinaryTree<E>>();
    q.enqueue(t);
    while(!q.isEmpty()) {
      BinaryTree<E> next = q.dequeue();
      touch(next);
      if(!next.left().isEmpty()) q.enqueue(next.left());
      if(!next.right().isEmpty()) q.enqueue(next.right());
    }
  }

  /** main method to set up an answer */
  public static void main(String[] args) {

    BinaryTree<String> arya = new BinaryTree<String>("Arya");
    BinaryTree<String> brienne = new BinaryTree<String>("Brienne");
    BinaryTree<String> jon = new BinaryTree<String>("Jon");
    BinaryTree<String> melisandre = new BinaryTree<String>("Melisandre");
    BinaryTree<String> tyrion = new BinaryTree<String>("Tyrion");

    /* build the actual tree structure */
    BinaryTree<String> bran = new BinaryTree<String>("Bran", arya, brienne);
    BinaryTree<String> jaime = new BinaryTree<String>("Jaime", bran, jon);
    BinaryTree<String> sansa = new BinaryTree<String>("Sansa", melisandre, tyrion );
    BinaryTree<String> root = new BinaryTree<String>("Jorah", jaime, sansa);

    /* Pre-order traversal */
    System.out.println("Pre-order traversal...");
    preOrder(root);
    System.out.println();

    /* In-order traversal */
    nodeNumber = 0;
    System.out.println("In-order traversal...");
    inOrder(root);
    System.out.println();

    /* Post-order traversal */
    nodeNumber = 0;
    System.out.println("Post-order traversal...");
    postOrder(root);
    System.out.println();

    /* level-order traversal */
    nodeNumber = 0;
    System.out.println("Level-order traversal...");
    levelOrder(root);
    System.out.println();

  }
}
