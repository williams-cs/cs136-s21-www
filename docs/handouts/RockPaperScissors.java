import java.util.Random;
import java.util.Scanner;

/**
 * Aah, Rock, Paper, Scissors, ...: A classic!
 * We implement it, in part, to show that the TwoPlayerGame
 * interface is quite flexible.
 * Thought questions before looking at code:
 * - What is a move?
 * - When is it valid?
 * - When is the game over?
 */

public class RockPaperScissors implements TwoPlayerGame{

	public static final String[] moves = {"Rock", "Paper", "Scissors"};
	protected boolean isGameOver;

		public RockPaperScissors() {
			isGameOver = false;
		}

	public String toString() {
		String s = "Moves: ";
		for (int move = 0; move < moves.length; move++) {
			s += "[" + move + ", " + moves[move] + "]";
		}
		return s;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	// removes matches from the specified pile
	public void makeMove(int p0, int p1) {
		System.out.println(moves[p0] + " vs. " + moves[p1]);
		if (p0 == p1) {
			System.out.println("Tie. Keep going.");
		} else {
			isGameOver = true;
		}
	}

	// Indices better be 0, 1, or 2!
	public boolean isValidMove(int p0, int p1) {
		return (0 <= p0) && (p0 < moves.length) && (0 <= p1) && (p1 < moves.length);
	}

}
