// Another iterator over Fibonacci numbers
// by Bill 2016

import java.util.Iterator;

public class EvenFib implements Iterator<Integer> {

  private FibonacciNumbers baseIter;
  private int nextVal;  // The value to be returned by next(); 0 if no values remaining

  public EvenFib() {
    baseIter = new FibonacciNumbers();
    nextEven();
  }

  public EvenFib(int n) {
    baseIter = new FibonacciNumbers(n);
    nextEven();
  }
  
  // Post: Either nextVal is the next even Fib or hasNext is false
  private void nextEven() {
    nextVal = 0;  // Assume no more even values remain
    while(baseIter.hasNext()) {
      nextVal = baseIter.next();
      if (nextVal % 2 == 0) return;
    }
    nextVal = 0; // No more even values remain
  }

  public boolean hasNext() { return nextVal != 0; }

  public Integer next() {
    int temp = nextVal; // Save current value to return
    nextEven();
    return temp;
  }

  public static void main(String[] args) {
    EvenFib ef;

    if(args.length > 0)
    ef = new EvenFib(Integer.valueOf(args[0]));
    else
    ef = new EvenFib();

    while(ef.hasNext())
    System.out.println(ef.next());
  }
}
