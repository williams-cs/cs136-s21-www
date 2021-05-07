import structure5.*;
import java.util.Random;

// A program to explore the Collatz Conjecture
// Generates random non-negative integers to search
// for numbers with long Collatz sequences

// This version uses a hash table to store previously
// computed sequence lengths
public class MemoCollatz {

  Map<Long,Integer> table;

  public MemoCollatz() {
    this(100);
  }

  // Constructor: Parameter is starting table size
  public MemoCollatz(int tableSize) {
    table = new Hashtable<Long,Integer>(tableSize);
    table.put(1L,1);
  }

  // How many values are in the table?
  public int size() { return table.size(); }

  // Compute Collatz successor of n
  public long next(long n) {
    if( n % 2 == 0) return n/2;
    return 3*n+1;
  }

  // Print the Collatz sequence for n
  public void printSequence(long n) {
    System.out.println(n);
    if(n > 1) printSequence(next(n));
  }

  public int seqLength(long n) {
    // Base case
    Integer length = table.get(n);
    // If it's in the table, look it up
    if(length != null) return length;

    // otherwise compute it and add it to the table
    int seqLen = 1 + seqLength(next(n));
    table.put(n, seqLen);
    return seqLen;
  }

  public int noTableSeqLength(long n) {
    // Base case
    if(n == 1L) return 1;

    return 1 + noTableSeqLength(next(n));
  }

  public static void main(String[] args) {
    if( args.length == 0 ) usage();

    int trials = Integer.valueOf(args[0]);
    int tableSize = Integer.valueOf(args[1]);
    int maxValue = Integer.valueOf(args[2]);

    boolean useMemo = args.length > 3;
    // If any 4th parameter appears, use Memoization
    MemoCollatz col = new MemoCollatz(tableSize);

    Random rng = new Random(1L);

    int maxLenSoFar = 0;
    long maxNumSoFar = 1;
    for(int i=0; i < trials; i++) {
      long nextNum = 1 + rng.nextInt(maxValue);
      int len = 0;
      if (useMemo)
        len = col.seqLength(nextNum);
      else
        len = col.noTableSeqLength(nextNum);
      // if(i % 1000 == 0) System.out.println("Trial " + i + ": testing " + nextNum);
      if(len > maxLenSoFar) {
        maxLenSoFar = len;
        maxNumSoFar = nextNum;
        System.out.println("Sequence length for " + nextNum + " is " + len);
        System.out.println("Map size: " + col.size());
      }
    }
    System.out.println("Max length overall was " + maxLenSoFar
          + " for n = " + maxNumSoFar);
    System.out.println("Final table size: " + col.size());
    // System.out.println("The sequence for " + maxNumSoFar + ":");
    // col.printSequence(maxNumSoFar);
  }

  //----------Utility methods for processing command-line arguments-------

  // Give usage then quit
  public static void usage() {
    System.out.println("Usage: java Collatz trials tableSize upperBound");
    System.exit(0);
  }



}
