/* Nim: A 2-person game
 * Bill L 2021
 * with comments from Bill J
 *
 * At the game's start, there are one or more piles of matchsticks.
 * Players take turns.
 * The player whose turn it is must choose one pile and remove one or more
 * matchsticks from that pile.
 * The player who cannot remove a matchstick loses (i.e., the winner removes the
 * very last matchstick from the gameboard).
 *
 * We represent the piles of matches as an array of integers, where each
 * array element represents one pile, and the value at a given index represents
 * the number of matchsticks in that pile.
 *
 * This code is TERRIBLE from an organization perspective!
 * We will be discussing object-oriented programming in subsequent lectures;
 * The points of this example are to show how to decompose a high-level task
 * into discrete sub-tasks that can each be separately implemented,
 * and to exercise many of the Java language features
 * (variable declaration and assignment, operators, key classes,
 * input/output, loops and conditionals, etc.) that we have discussed so far.
*/

import java.util.Random;
import java.util.Scanner;

public class Nim {

  // Plays a game of Nim. By decomposing into methods with
  // meaningful names and clear functionality, this is
  // (hopefully) simple code to read
  public static void main(String[] args) {
    // Check for command-line argument
    if (args.length == 0) {
      System.out.println("Usage: java Nim <number of piles>");
      System.exit(0);
    }

    // Declare needed variables (game state)
    // The array holding the piles
    int[] board;

    // Number of non-empty piles (for checking game over)
    int pilesLeft;

    // Smallest and largest legal pile sizes
    // Final means value can't be changed
    int minPileSize = 3;
    int maxPileSize = 8;

    // A random number generator for creating piles
    // Remember to import it from java.util!
    Random rng = new Random();

    // A Scanner provides more convenient methods for input
    // See the Scanner handout on the handouts page
    Scanner in = new Scanner(System.in);

    // Create the board
    int size = Integer.valueOf(args[0]);
    board = new int[size];

    // Fill the board with randomly sized piles
    for(int i=0; i< board.length; i++)
    board[i] = minPileSize + rng.nextInt(maxPileSize - minPileSize + 1);

    // Every pile is non-empty
    pilesLeft = size;

    // Display the board
    for(int i = 0; i < board.length; i++) {
      System.out.print(i + ":");
      for(int j=0; j < board[i]; j++)  // Display a pile
      System.out.print(" O");
      System.out.println();
    }

    // Take turns while there are sticks remaining
    while (pilesLeft > 0) {
      // Take a single turn
      // Get move from player
      System.out.print("Enter a pile number and number of sticks to remove: ");
      int pile = in.nextInt();  // Should have 0 <= pile < board.length
      int num = in.nextInt();   // Should have num <= board[pile]

      // Error-checking
      while (pile >= board.length || board[pile] == 0 || board[pile] < num ) {
        System.out.print("Choose a non-empty pile and no more sticks than the pile holds");
        System.out.print("Enter a pile number and number of sticks to remove: ");
        pile = in.nextInt();
        num = in.nextInt();
      }

      // Update board state
      board[pile] -= num;
      if (board[pile] == 0) pilesLeft--;

      // Show the board after the move
      for(int i = 0; i < board.length; i++) {
        System.out.print(i + ":");
        for(int j=0; j < board[i]; j++)  // Display a pile
        System.out.print(" O");
        System.out.println();
      }
    }

    System.out.println("Game over!");
  }

}
