import structure5.*;
import java.util.Random;

public class MergeSort {

  /**
  method to do a mergesort on an array of Comparable objects

  @param elts array of Comparable elements
  @post elts is sorted in ascending order
  // post: values in data are in ascending order
  */
  public static <T extends Comparable<T>> void mergeSort(T[] data) {
    // Clone is used to create an array of the same (generic) type as data
    mergeSortRecursive(data,data.clone(),0,data.length-1);
  }

  // pre: 0 <= low <= high < data.length
  // post: values in data[low..high] are in ascending order
  private static <T extends Comparable<T>> void
        mergeSortRecursive(T[] data,  T[] temp, int low, int high) {

    int n = high-low+1;
    int middle = low + n/2;
    int i;

    if (n < 2) return;
    // move lower half of data into temporary storage
    for (i = low; i < middle; i++) {
      temp[i] = data[i];
    }
    // sort lower half of array
    mergeSortRecursive(temp,data,low,middle-1);
    // sort upper half of array
    mergeSortRecursive(data,temp,middle,high);
    // merge halves together
    merge(data,temp,low,middle,high);
  }

  // pre: data[middle..high] are ascending
  //      temp[low..middle-1] are ascending
  // post: data[low..high] contains all values in ascending order
  private static <T extends Comparable<T>> void
        merge(T[] data, T[] temp, int low, int middle, int high) {

    int ri = low; // result index
    int ti = low; // temp index
    int di = middle; // destination index
    // while two lists are not empty merge smaller value
    while (ti < middle && di <= high) {
      if (data[di].compareTo(temp[ti]) < 0) {
        data[ri++] = data[di++]; // smaller is in high data
      } else {
        data[ri++] = temp[ti++]; // smaller is in temp
      }
    }
    // possibly some values left in temp array
    while (ti < middle) {
      data[ri++] = temp[ti++];
    }
    // ...or some values left (in correct place) in data array
    // no moves needed
  }

  // Give usage then quit
  public static void usage() {
    System.out.println("Usage: java BinSearchCompVector n (n > 0)");
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

  public static void main (String args[]) {

    if(args.length == 0) usage();
    int n = checkArgVal(args[0]);

    Integer[] nums = new Integer[n];
    Random rng = new Random();
    for(int i=0; i<n; i++) nums[i] = rng.nextInt();


    System.out.println("The unsorted values...");
    for(int i=0; i<n; i++) System.out.println(nums[i]);

    mergeSort(nums);

    System.out.println("The sorted values...");
    for(int i=0; i<n; i++) System.out.println(nums[i]);

  }
}
