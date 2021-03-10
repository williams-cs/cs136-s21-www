// Towers of Hanoi

public class Tower {

// Give usage then quit
public static void usage() {
  System.out.println("Usage: java Tower n (n > 0)");
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

  public static void solveTowers(int n, String fromPeg, String usingPeg, String toPeg) {
    if(n == 1)
      System.out.println("Move a disk from " + fromPeg + " to " + toPeg);
    else {
      solveTowers(n-1, fromPeg, toPeg, usingPeg);
      solveTowers(1, fromPeg, usingPeg, toPeg);
      solveTowers(n-1, usingPeg, fromPeg, toPeg);

    }
  }

  public static void main(String[] args) {
    if( args.length == 0 ) usage();
    int n = checkArgVal(args[0]);

    System.out.println("The solution is: \n");
    solveTowers(n, "Left", "Right", "Middle");
  }
}
