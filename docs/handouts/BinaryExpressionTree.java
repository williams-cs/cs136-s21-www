import structure5.*;

/*
 *  class to evaluate simple expression trees using structure's
 *  BinaryTree implementation.
 *
 *  Each node in the tree holds a String that is either a binary operator
 *  (restricted to "+", "-", "*", "/") or can be treated as an integer value.
 */
public class BinaryExpressionTree {

    /*
     *  Evaluate an expression stored in a BinaryTree
     */
    public static int evaluate(BinaryTree<String> root) {

        if (root.height() == 0) {
            return Integer.parseInt(root.value());
        } else {
            int left = evaluate(root.left());
            int right = evaluate(root.right());
            String op = root.value();
	    switch (op) {
            case "+" : return left+right;
            case "-" : return left-right;
            case "*" : return left*right;
            case "/" : return left/right;
	    }
            Assert.fail("Bad op: " + op);
            return -1;
        }
    }

    /** main method to set up an answer */
    public static void main(String[] args) {

        /* build and evaluate the binary tree for ((4+3)*(10-5))/2 */
        /* we will build it from the bottom up */
        BinaryTree<String> four = new BinaryTree<String>("4");
        BinaryTree<String> three = new BinaryTree<String>("3");
        BinaryTree<String> ten = new BinaryTree<String>("10");
        BinaryTree<String> five = new BinaryTree<String>("5");
        BinaryTree<String> two = new BinaryTree<String>("2");

        /* build the actual tree structure */
        BinaryTree<String> plus = new BinaryTree<String>("+", four, three);
        BinaryTree<String> minus = new BinaryTree<String>("-", ten, five);
        BinaryTree<String> times = new BinaryTree<String>("*", plus, minus);
        BinaryTree<String> divide = new BinaryTree<String>("/", times, two);

        /* what does it look like? */
        System.out.println(divide.treeString());

        /* what's the answer */
        System.out.println(evaluate(divide));
    }
}
/*                                    (/)
                                     /   \
                                    /     \
                                   /       \
                                  /         \
                                (*)         (2)
                               /   \
                              /     \
                             /       \
                            /         \
                          (+)         (-)
                         /   \       /   \
                        /     \     /     \
                      (4)     (3) (10)    (5)
*/
