import structure5.*;

public class BasicHuffman {

  String text;
  BinaryTree<Association<Integer,Character>> theTree;
  Hashtable<Character,String> encodingTable;

  public BasicHuffman(String input) {
    text = input;
    theTree = new BinaryTree<Association<Integer,Character>>();
    encodingTable = new Hashtable<Character,String>(97);
    buildTree(text);
    buildEncodingTable(theTree, "");
  }

  // Get source text
  public String text() { return text; }

  // Build a Huffman tree from the string
  private void buildTree(String s) {
    // Build a frequency list for the string
    FrequencyList fl = new FrequencyList(s);

    // Create a vector of binary trees
    Vector<BinaryTree<Association<Integer,Character>>> trees
          = new Vector<>();

    // Fill the vector with single-node trees using F.L.
    for(Association<Character,Integer> a : fl) {
      // add tree to vector preserving decreasing order of frequencies
      insertTreeInVector(trees,new BinaryTree<Association<Integer,Character>>(
              new Association<Integer,Character>(a.getValue(), a.getKey())));
    }

    // Merge into a single tree
    theTree = mergeTrees(trees);
  }

  // Repeatedly merge two smallest trees into a larger tree
  // until a single tree remains
  private BinaryTree<Association<Integer,Character>> mergeTrees
              (Vector<BinaryTree<Association<Integer,Character>>> trees) {

    while(trees.size() > 1) {
      // Get trees with smallest frequencies
      BinaryTree<Association<Integer,Character>> thing1 = trees.removeLast();
      BinaryTree<Association<Integer,Character>> thing2 = trees.removeLast();

      // Build new tree using sum of frequencies
      int sum = thing1.value().getKey() + thing2.value().getKey();
      BinaryTree<Association<Integer,Character>> merged =
        new BinaryTree<Association<Integer,Character>>
            (new Association<Integer,Character>(sum, null), thing1, thing2);
      insertTreeInVector(trees, merged);
    }
      // Return the single remaining tree
      return trees.get(0);
  }

  // Helper method to keep vector of binary trees sorted by frequency
  private void insertTreeInVector(
      Vector<BinaryTree<Association<Integer,Character>>> v,
      BinaryTree<Association<Integer,Character>> t) {

    int loc = 0;
    while(loc < v.size() && v.get(loc).value().getKey() > t.value().getKey())
      loc++;
    v.add(loc,t);
  }

  // Compute encoding length of string from tree
  public int encodingLength(){
    return sumOfLengths(theTree) - theTree.value().getKey();
  }

  // Helper method to recursively compute encoding length
  private int sumOfLengths(BinaryTree<Association<Integer,Character>> t) {
    if(t.isEmpty()) return 0;
    return t.value().getKey() + sumOfLengths(t.left()) + sumOfLengths(t.right());
  }

  // Build the table of character-->bit-strings pairs
  // Uses a pre-order traversal
  private void buildEncodingTable(BinaryTree<Association<Integer,Character>> t,
                  String prefix) {
    // Base case: leaf
    if(t.value().getValue() != null)
      encodingTable.put(t.value().getValue(), prefix);
    else {
      buildEncodingTable(t.left(), prefix+"0");
      buildEncodingTable(t.right(), prefix+"1");
    }
  }

  // Print the encoding table in human readable form
  public void printEncodingTable() {
    Set<Association<Character,String>> pairs = encodingTable.entrySet();
    for(Association<Character,String> a : pairs) {
      System.out.println(a.getKey() + "-->" + a.getValue());
    }
  }

  // Compute Huffman encoding of text string
  public String encode(String text) {
    String encoding = "";
    for(int i = 0; i < text.length();i++)
      encoding += encodingTable.get(text.charAt(i));
    return encoding;
  }

  // Decode a Huffman encoded string
  // Works by repeatedly traversing tree from root moving left and right
  // based on the 'bits' of encoding
  // Note: if input is "bad" (doesn't end at a leaf) the result returned
  // is the longest 'good' prefix
  public String decode(String encoding) {
    String result = "";
    int nextSymbolIndex = 0;
    BinaryTree<Association<Integer,Character>> current = theTree;
    while( nextSymbolIndex < encoding.length() ) {
      // Process current 0/1
      if(encoding.charAt(nextSymbolIndex) == '0') current = current.left();
      else current = current.right();
      nextSymbolIndex++;

      // If we are now at a leaf
      if(current.value().getValue() != null) {
          result += current.value().getValue();
          current = theTree;
      }
    }
    return result;
  }

  public String toString() {
    return "";
  }

	public static void main(String[] args) {
		if(args.length < 1) usage();

    // Create a Huffman tree
    BasicHuffman bh = new BasicHuffman(args[0]);

    // Use it to determine length of encoded string
    System.out.println("Huffman encoding of " + bh.text() + " is " +
        bh.encodingLength());

    // Print out the encoding table
    System.out.println("Encoding Table for " + bh.text());
    bh.printEncodingTable();

    // Use the encoding table to encode a string!
    String encoded = bh.encode(args[0]);
    System.out.println(encoded);
    System.out.println("ASCII Encoding Length: " + args[0].length()*8);
    System.out.println("Huffman Encoding Length: " + encoded.length());

    // Now decode the string
    String decoded = bh.decode(encoded);
    System.out.println(decoded);

    // Did it work?
    if(bh.text().equals(decoded))
      System.out.println("Success!");
    else
    System.out.println("Failure!");
	}

  public static void usage() {
	    System.out.println("Usage: Huffman string");
			System.exit(0);
 }

}
