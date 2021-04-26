import structure5.*;

public class HeapHuffman<E> {

  Vector<E> data;
  CompBinTree<ComparableAssociation<Integer,E>> theTree;
  Hashtable<E,String> encodingTable;

  public HeapHuffman(Vector<E> input) {
    data = input;
    theTree = new CompBinTree<ComparableAssociation<Integer,E>>();
    encodingTable = new Hashtable<E,String>(97);
    buildTree();
    buildEncodingTable(theTree, "");
  }

  // Get source text
  public Vector<E> data() { return data; }

  // Build a Huffman tree from the string
  private void buildTree() {
    // Build a frequency list for the string

    GenericFrequencyList<E> fl = new GenericFrequencyList<E>(data);

    // Create a priority queue of binary trees
    PriorityQueue<CompBinTree<ComparableAssociation<Integer,E>>> heapOfTrees
          = new VectorHeap<CompBinTree<ComparableAssociation<Integer,E>>>();

    // Fill the vector with single-node trees using F.L.
    for(Association<E,Integer> a : fl) {
      // add tree to PQ preserving decreasing order of frequencies
      heapOfTrees.add(new CompBinTree<ComparableAssociation<Integer,E>>(
              new ComparableAssociation<Integer,E>(a.getValue(), a.getKey())));
    }

    // Merge into a single tree
    theTree = mergeTrees(heapOfTrees);
  }

  // Repeatedly merge two smallest trees into a larger tree
  // until a single tree remains
  private CompBinTree<ComparableAssociation<Integer,E>> mergeTrees
              (PriorityQueue<CompBinTree<ComparableAssociation<Integer,E>>> trees) {

    while(trees.size() > 1) {
      // Get trees with smallest frequencies
      CompBinTree<ComparableAssociation<Integer,E>> thing1 = trees.remove();
      CompBinTree<ComparableAssociation<Integer,E>> thing2 = trees.remove();

      // Build new tree using sum of frequencies
      int sum = thing1.value().getKey() + thing2.value().getKey();
      CompBinTree<ComparableAssociation<Integer,E>> merged =
        new CompBinTree<ComparableAssociation<Integer,E>>
            (new ComparableAssociation<Integer,E>(sum, null), thing1, thing2);
      trees.add(merged);
    }
      // Return the single remaining tree
      return trees.remove();
  }

  // Compute encoding length of string from tree
  public int encodingLength(){
    return sumOfFrequencies(theTree) - theTree.value().getKey();
  }

  // Helper method to recursively compute encoding length
  private int sumOfFrequencies(CompBinTree<ComparableAssociation<Integer,E>> t) {
    if(t.isEmpty()) return 0;
    return t.value().getKey() + sumOfFrequencies(t.left()) +
                                sumOfFrequencies(t.right());
  }

  // Build the table of E-->bit-strings pairs
  // Uses a pre-order traversal
  private void buildEncodingTable(CompBinTree<ComparableAssociation<Integer,E>> t,
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
    Set<Association<E,String>> pairs = encodingTable.entrySet();
    for(Association<E,String> a : pairs) {
      System.out.println(a.getKey() + "-->" + a.getValue());
    }
  }

  // Compute Huffman encoding of text string
  public String encode(Vector<E> input) {
    String encoding = "";
    for(int i = 0; i < input.size();i++)
      encoding += encodingTable.get(input.get(i));
    return encoding;
  }

  // Decode a Huffman encoded string
  // Works by repeatedly traversing tree from root moving left and right
  // based on the 'bits' of encoding
  public Vector<E> decode(String encoding) {
    Vector<E> result = new Vector<E>();
    int nextSymbolIndex = 0;
    CompBinTree<ComparableAssociation<Integer,E>> current = theTree;
    boolean finished = false;
    while( nextSymbolIndex < encoding.length() ) {
      // Process current 0/1
      if(encoding.charAt(nextSymbolIndex) == '0') current = current.left();
      else current = current.right();
      nextSymbolIndex++;

      // If we are now at a leaf
      if(current.value().getValue() != null) {
          result.add(current.value().getValue());
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

    // Build Vector of Es from String
    Vector<Character> chars = new Vector<Character>(args[0].length());
		for(int i = 0; i < args[0].length(); i++) chars.add(args[0].charAt(i));

    // Create a Huffman tree
    HeapHuffman<Character> bh = new HeapHuffman<Character>(chars);

    // Use it to determine length of encoded string
    System.out.println("Huffman encoding of " + bh.data() + " is " +
        bh.encodingLength());

    // Print out the encoding table
    System.out.println("Encoding Table for " + bh.data());
    bh.printEncodingTable();

    // Use the encoding table to encode a string!
    String encoded = bh.encode(chars);
    System.out.println(encoded);

    // Now decode the string
    Vector<Character> decoded = bh.decode(encoded);
    System.out.println(decoded);

	}

  public static void usage() {
	    System.out.println("Usage: Huffman string");
			System.exit(0);
 }

}
