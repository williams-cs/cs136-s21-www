// An iterator over Fibonacci numbers
// by Bill 2016

import java.util.Iterator;

public class FibonacciNumbers implements Iterator<Integer> {

  private int next = 1, current = 1;
  private int length = 10;

  public FibonacciNumbers() {}
    public FibonacciNumbers(int n) {length = n;}

    public boolean hasNext() { return length >=0;}

    public Integer next() {
      length--;
      int temp = current;
      current = next;
      next = temp + current;
      return temp;
    }

    public static void main(String[] args) {
      FibonacciNumbers fibGen;

      if(args.length > 0)
      fibGen = new FibonacciNumbers(Integer.valueOf(args[0]));
      else
      fibGen = new FibonacciNumbers();

      while(fibGen.hasNext())
      System.out.println(fibGen.next());
    }
  }
