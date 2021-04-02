import structure5.*;

/*
 *  class to evaluate simple expression trees using structure's
 *  BinaryTree implementation.
 *
 *  Each node in the tree holds a String that is either a binary operator
 *  (restricted to "+", "-", "*", "/") or can be treated as an integer value.
 */
public class BinExpTreeFromPS{

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
            case "add" : return left+right;
            case "sub" : return left-right;
            case "mul" : return left*right;
            case "div" : return left/right;
	    }
            Assert.fail("Bad op: " + op);
            return -1;
        }
    }

    /** main method to set up an answer */
    public static void main(String[] args) {

	if(args.length == 0) {
	    System.out.println("Usage: java BinExpTreeFromPS PostScript_Expression");
	    return;
	}
	Stack<BinaryTree<String>> treeStack = new StackList<BinaryTree<String>>();

	BinaryTree<String> t, left, right;

	for(String s : args) {
	    if(s.equals("add") || s.equals("sub") || s.equals("mul") || s.equals("div")) {
		// Create children of non-leaf
		right = treeStack.pop();
		left = treeStack.pop();
		t = new BinaryTree<String>(s, left, right);
	    }
	    else
		t = new BinaryTree<String>(s);

	    treeStack.push(t);
	}

	// Get expression tree from stack (should be only thing on stack)
	t = treeStack.pop();

        /* what does it look like? */
        System.out.println(t.treeString());

        /* what's the answer */
        System.out.println(evaluate(t));
    }
}
