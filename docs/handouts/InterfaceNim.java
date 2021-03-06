import java.util.Random;
import java.util.Scanner;

/**
 * This is a completed version of the "Interface based Nim"
 *
 * When building this game, we noticed that the "gameplay" pattern is quite
 * general: until the game is over, present the board, prompt a player
 * for a move, and then make the move.
 *
 * This insipired us to make an Interface that all "Two Player Games" could
 * follow. Once we have this interface defined, we can then design a general
 * method that lets us play any "Two Player Game" (See InterfacePlay.java).
 *
 * This Nim version is a small evolution of OOPNim in that it implements the
 * TwoPlayerGame interface. To do this, we update the "class declaration",
 * and then ensure that we provide an implementation of ALL methods in the file
 * TwoPlayerGame.java.
 */

public class InterfaceNim implements TwoPlayerGame{

	// Since these constant varialbes that never change,
	// they are declared "final". The convention is to
	// type constants in ALL_CAPS_WITH_UNDERSCORES
	public static final int MIN_PILES = 3;
	public static final int MAX_PILES = 5;

	// The state of my game can be encoded in just two variables:
	// the board and the current player.
	// As member variables, I have made them protected---if someone
	// wants to access/modify them, they must use my methods, giving me
	// control over the state of my object
	protected int[] board;
	protected int currentPlayer;


	// a "basic" constructor that uses default values
	public InterfaceNim() {
		// it just calls the "specific" constructor. This lets
		// me avoid duplicating code
		this(MIN_PILES, MAX_PILES);
	}

	public InterfaceNim(int minPiles, int maxPiles) {
		currentPlayer = 0;
		createBoard(minPiles, maxPiles);
	}

	// note that this doesn't return any value. That is because its
	// purpose is to update the state of *this* object, not return a
	// new object. Notice that we don't declare a variable called `board`.
	// Instead, when we refer to the variable `board`, we are referring
	// to the instance variable `board`.
	protected void createBoard(int minPiles, int maxPiles) {
		Random random = new Random();
		int numPiles = minPiles + random.nextInt(maxPiles - minPiles);

		// create a board with the chosen number of piles
		board = new int[numPiles];

		// for each pile, add a random number of matchsticks
		for (int pile = 0; pile < numPiles; pile++) {
			// pick a random number of matchSticks between min and max
			// to place in that pile
			int maxElements = 5;
			int minElements = 1;
			int numElements = minElements +
				random.nextInt(maxElements - minElements);

			board[pile] = numElements;
		}
	}

	// this just prints the board; it doesn't need parameters because
	// all of the information it needs is accessible as instance variables
	public void displayBoard() {
		// display the board, with each "row" representing a pile.
		// the format is "<pile number>: (<pile count>) | | | | ...
		System.out.println(this);
	}

	// note that this is the code from displayBoard,
	// modified to produce a string insead of print to the terminal
	// We left displayBoard, but now it just prints the result of
	// calling toString
	public String toString() {
		String representation = "";
		for (int pile = 0; pile < board.length; pile++) {
			representation += pile + ": (" + board[pile] + ")";
			for (int match = 0; match < board[pile]; match++) {
				representation += " |";
			}
			// add a new line to start the next pile's display
			representation += "\n";
		}
		return representation;
	}

	public boolean isGameOver() {
		// Goal: if game is over, break out!
		// Strategy: assume the game is over, then check each pile
		// If any one pile has matches, then the game's not over
		boolean gameOver = true;
		for (int pile = 0; pile < board.length; pile++) {
			if (board[pile] > 0) {
				gameOver = false;
			}
		}
		// if we got here and all matches were gone, then gameOver is
		// still true!

		return gameOver;
	}

	// removes matches from the specified pile
	public void makeMove(int whichPile, int numMatches) {
		board[whichPile] -= numMatches;
	}

	// verifies that the specficiation of the move is a valid move
	// They must: (1) specifiy a valid pile, and (2) remove
	// a valid number of matches from that pile
	public boolean isValidMove(int whichPile, int numMatches) {

		// valid pile?
		if (whichPile < 0 || whichPile >= board.length) {
			return false;
		}

		// valid # of matches?
		if (numMatches < 0 ||  numMatches > board[whichPile]) {
			return false;
		}

		// if we got here, then the move wasn't invalid...
		return true;
	}

	// this combines all of the "turn" logic into one method:
	// prompting the user, verifying the validity of the move,
	// and making the move itself. it has no return value because
	// it's utility is that it modifies the state of the object's
	// instance variables
	public void takeATurn() {
		displayBoard();

		// current player specifies a move
		Scanner sc = new Scanner(System.in);
		System.out.println("Player " + currentPlayer +", please select a pile.");
		int whichPile = sc.nextInt();
		System.out.println("How many matches would you like to remove?");
		int numMatches = sc.nextInt();


		// check if move is legal
		while (!isValidMove(whichPile, numMatches)) {
			displayBoard();
			System.out.println("ILLEGAL MOVE!");
			System.out.println("Player " + currentPlayer +", please select a pile.");
			whichPile = sc.nextInt();
			System.out.println("How many matches would you like to remove?");
			numMatches = sc.nextInt();
		}

		// make move
		makeMove(whichPile, numMatches);

		swapPlayer();
	}

	// My "Getter" and "Setter" methods for the current player.
	// They are public, while the player instance variable is protected.
	// In this way, I am restricting control to my object's instance
	// variables: noone can change the player to an invalid value,
	// since the only way to modify the player is through "swapPlayer"
	public int getPlayer() {
		return currentPlayer;
	}

	public void swapPlayer() {
		if (currentPlayer == 0) {
			currentPlayer = 1;
		} else {
			currentPlayer = 0;
		}
	}


	public static void main(String args[]) {
		// I create a new Nim game object by calling the
		// Nim constructor.
		InterfaceNim nim = new InterfaceNim();

		// my "gameplay" loop is rather simple: continue taking
		// turns until the game is over.
		while (!nim.isGameOver()) {
			nim.takeATurn();
		}

		// finally, announce the winner!
		System.out.println("Player " + nim.getPlayer() + " wins!");
	}

}
