// An iterator for the Collatz conjecture
// by Bill 2016

import java.util.Iterator;

public class Collatz implements Iterator<Integer> {
  private int current = 27; // default value
  private int count = 0;

  public Collatz() {}
    public Collatz(int n) {current = n;}

    public boolean hasNext() { return current != 1;}

    public Integer next() {
      count++;
      int result = current;
      if (current % 2 == 0) current = current/2;
      else current = 3*current+1;
      return result;
    }

    public String toString() {
      return "(value = " + current + ", count = " + count + ")";
    }

    public static void main(String[] args) {
      Collatz collatzGen;

      if(args.length > 0)
      collatzGen = new Collatz(Integer.valueOf(args[0]));
      else
      collatzGen = new Collatz();

      while(collatzGen.hasNext())
        System.out.println(collatzGen.next());

      System.out.println(collatzGen);
    }
  }
