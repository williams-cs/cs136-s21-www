import structure5.*;
import java.util.Iterator;

// An iterator to generate first n primes
public class PrimesStarter implements Iterator<Integer> {

  public PrimesStarter(int n) {
  }

  public boolean hasNext() { return true;}

  public Integer next() {return 0;
  }

  public static void main(String[] args) {
    // Make sure command line argument is legal
    if(args.length == 0) usage();

    int n = checkArgVal(args[0]);
    Primes p = new Primes(n);

    while(p.hasNext())
    System.out.println(p.next());
  }

  // Give usage then quit
  public static void usage() {
    System.out.println("Usage: java Primes n (n > 0)");
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
}

/*
// Is num divisible by one of the primes found so far
public boolean composite(int num) {
  for(int i=0;i < count;i++)
  if(num % primes[i] == 0) return true;
  return false;
}

*/
