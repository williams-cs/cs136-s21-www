import structure5.*;
import java.util.Iterator;

// An iterator to generate first n primes
public class Primes implements Iterator<Integer> {

  int[] primes;   // Primes generated so far
  int nextPrime;  // The next prime to dispense
  int count;      // How many primes have been dispensed

  public Primes(int n) {
    primes = new int[n];
    nextPrime = 2;
    count = 0;
  }

  // If array not full, then hasNext is true
  public boolean hasNext() { return count < primes.length;}

  // Dispense current prime, then update nextPrime to be next prime
  public Integer next() {

    // Save the newly dispensed prime
    primes[count++] = nextPrime;

    // First candidate for next prime
    nextPrime++;

    // keep incrementing nextPrime until it is not
    // divisible by any prime in array. Then it must be prime!
    while(composite(nextPrime)) nextPrime++;
    return primes[count-1];
  }

  // Is num divisible by one of the primes found so far
  protected boolean composite(int num) {
    for(int i=0;i < count;i++)
    if(num % primes[i] == 0) return true;
    return false;
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
