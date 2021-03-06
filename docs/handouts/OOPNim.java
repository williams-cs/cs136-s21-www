/* OOPNim: A 2-person game
* Bill L 2019, 2021
*
* Class supports creation of Nim board objects for playing Nim
*
* The main method tests class by creating a Nim board object then
* allowing moves to be made until board is empty
*
* The class creates Nim objects. A Nim object contains all information
* about the state of a single Nim game (the board) along with methods
* that allow for initializing and updating the board.
*
* A Nim board represents of one or more piles of matchsticks.
*
* It supports methods for
* - creating the board
* - displaying the board
* - making a (legal) move (checks for legality)
* - checking to see whether the board is empty (game is over)
*
* We represent the board by an array of integers, where each
* array element represents one pile, and the value at a given index represents
* the number of matchsticks in that pile.
*/

import java.util.Random;
import java.util.Scanner;

public class OOPNim {

  // Object state: Instance variables and constants
  final int MIN_PILE_SIZE = 3;   // Smallest legal pile size
  final int MAX_PILE_SIZE = 8;   // Largest legal pile size
  Scanner in = new Scanner(System.in);

  private int[] board;           // Array holding pile sizes
  private int piles;             // Number of piles on board
  private int pilesLeft;         // Number of non-empty piles

  private Random rng = new Random(); // For creating piles

  public OOPNim() { this(5); }

  public OOPNim(int size) {
    // Create the board
    piles = size;
    board = new int[piles];

    // Fill the board with randomly sized piles
    for(int i=0; i< board.length; i++)
    board[i] = MIN_PILE_SIZE + rng.nextInt(MAX_PILE_SIZE - MIN_PILE_SIZE + 1);

    // Every pile is non-empty
    pilesLeft = piles;
  }

  public void displayBoard() {
    for(int i = 0; i < board.length; i++) {
      System.out.print(i + ":");
      for(int j=0; j < board[i]; j++)  // Display a pile
      System.out.print(" O");
      System.out.println();
    }
  }

  public boolean gameOver() { return pilesLeft == 0; }

  public void takeATurn() {
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
  }

  public static void main(String[] args) {

    OOPNim game;

    // Create an instance of the game
    if (args.length == 0) {
      game = new OOPNim();
    }
    else {
      game = new OOPNim(Integer.valueOf(args[0]));
    }

    // Display the board
    game.displayBoard();

    System.out.println("Let's play...");
    System.out.println();

    while (! game.gameOver()) {
      // Make a move in each game
      game.takeATurn();
      game.displayBoard();
    }

    System.out.println("Game over!");
  }

}
