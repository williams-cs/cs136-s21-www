import structure5.*;
import java.lang.Math;

public class CompBinTree<E extends Comparable<E>>
        implements Comparable<CompBinTree<E>> {

    protected E val; // value associated with node

    /**
     * The left child of this node, or an "empty" node
     */
    protected CompBinTree<E> left, right; // children of node

    /**
     * A one-time constructor, for constructing empty trees.
     */
    public CompBinTree()
    {
        val = null;
        left = right = this;
    }

    /**
     * Constructs a tree node with no children.  Value of the node
     * provided by the user
     */
    public CompBinTree(E value)
    {
        Assert.pre(value != null, "Tree values must be non-null.");
        val = value;
        right = left = new CompBinTree<E>();
    }

    /**
     * Constructs a tree node with two children.  Value of the node
     * and subtrees are provided by the user.
     */
    public CompBinTree(E value, CompBinTree<E> left, CompBinTree<E> right)
    {
        Assert.pre(value != null, "Tree values must be non-null.");
        val = value;
        this.left = left;
        this.right = right;
    }

    /**
     * Get left subtree of current node
     */
    public CompBinTree<E> left()
    {
        return left;
    }

    /**
     * Get right subtree of current node
     */
    public CompBinTree<E> right()
    {
        return right;
    }

    /**
     * Returns the number of descendants of node
     */
    public int size()
    {
        if (isEmpty()) return 0;
        return left().size() + right().size() + 1;
    }

    /**
     * Returns true if tree is empty.
     */
    public boolean isEmpty()
    {
        return val == null;
    }

    /**
     * Returns value associated with this node
     */
    public E value()
    {
        return val;
    }

    /**
     * Set's value associated with this node
     */
    public void setValue(E value)
    {
        val = value;
    }

    public String toString() {
      String result = "";
      if(isEmpty()) return result;
      result += val + "\n" + left.toString() + right.toString();
      return result;
    }

    public int compareTo(CompBinTree<E> other) {
      return val.compareTo(other.val);
    }
}
