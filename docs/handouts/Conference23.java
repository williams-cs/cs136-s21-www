import structure5.*;
import java.util.Scanner;

public class Conference23{

	private static void smallestAndLargest(){
		//show the largest and smallest ints in Java
		System.out.println("Largest int:" + Integer.MAX_VALUE);
		System.out.println("Smallest int:" + Integer.MIN_VALUE);
	}

	private static void absolute(){
		int x = Integer.MIN_VALUE;

		System.out.println("Abs(smallest):" + Math.abs(x));
		
		System.out.println("Taking out the MSB of the smallest:" + (x & 0x7fffffff));

		//Some motivation for taking Math.abs() after the mod:
		//System.out.println("''polygenelubricants''.hashCode()");
		//System.out.println("polygenelubricants".hashCode());
	}

	private static void getHashes() {
		Scanner in = new Scanner(System.in);
		while(true){
			System.out.print("Enter the string to hash: ");
			String input = in.nextLine();
			System.out.println("Hash of " + input + ": " + input.hashCode());
			System.out.println("Hash % 8: " + Math.abs(input.hashCode() % 8));
		}
	}


	public static void main(String[] args){
		//smallestAndLargest();
		//absolute();
		getHashes();
	}
}
