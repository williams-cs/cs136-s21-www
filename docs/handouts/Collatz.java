// Using recursion to explore the Collatz Conjecture

public class Collatz {

  // Give usage then quit
  public static void usage() {
    System.out.println("Usage: java Collatz n (n > 0)");
    System.exit(0);
  }

  // Convert String to int and quit on fail
  public static int checkArgVal(String arg) {
    int result = 0;
    try { result = Integer.valueOf(arg); }
    catch( NumberFormatException e ) { usage(); }
    if(result < 1) usage();
    return result;
  }

// ---------------------fun starts here------------------

  public static int next(int n) {
    if( n % 2 == 0) return n/2;
    return 3*n+1;
  }

  public static void printSequence(int n) {
    System.out.println(n);
    if(n > 1) printSequence(next(n));
  }

  public static int seqLength(int n) {

    if(n == 1) return 1;
    return 1 + seqLength(next(n));
  }
  public static void main(String[] args) {
    if( args.length == 0 ) usage();

    int n = checkArgVal(args[0]);

    int maxSoFar = 0;
    for(int i=1; i<=n; i++) {
      int l = seqLength(i);
      if(l > maxSoFar) {
        maxSoFar = l;
        System.out.println("Sequence length for " + i + " is " + seqLength(i));
      }
    }
  }
}
