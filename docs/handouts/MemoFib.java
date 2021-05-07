/*
* Class SlowFib - A naive recursive method for Fibonacci Numbers
*
* Bill L 10/13
*/

import java.util.Random;

public class MemoFib {

  private long[] table;

  public MemoFib(int max) {
    // Want to store fibonacci numbers fib(0), ..., fib(max)
    table = new long[max+1];
    table[0] = 1L;
    table[1] = 1L;
  }

  // pre: n is non-negative
  public long fib(int n) {
    if(table[n] != 0)
      return table[n];
    else {
      table[n] = fib(n-1) + fib(n-2);
      return table[n];
    }
  }

  public static void main(String[] args) {

    if (args.length !=2) usage();

    int max = Integer.valueOf(args[0]);
    int trials = Integer.valueOf(args[1]);

    MemoFib mf = new MemoFib(max);
    Random rng = new Random();

    for(int i=0; i < trials; i++) {
      System.out.println( mf.fib(rng.nextInt(max+1)) );
    }

  }

  // ********** command line helpers **********

  // Give usage then quit
  public static void usage() {
    System.out.println("Usage: java SlowFib maxIndex trials");
    System.exit(0);
  }

}
