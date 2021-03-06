/** InterfacePlay.java
	*
	* Allows player to choose any TwoPlayerGame and play it
	* Uses static methods to improve organization/readability
	*/

import java.util.Scanner;

public class StaticInterfacePlay {

	// Select game
	public static TwoPlayerGame selectGame(String gameName) {

		if(gameName.equals("Nim")) {
			return new InterfaceNim();
		}
		else if(gameName.equals("RPS")) {
			return new RockPaperScissors();
		}
		else if(gameName.equals("Coin")) {
			return new CoinStrip();
		}
		else {
			System.exit(0);
			return null;	// otherwise compiler complains
		}
	}

	// play a game
	public static void play(TwoPlayerGame game, Scanner scan) {

		while (!game.isGameOver()) {

			// note, since TwoPlayerGame is a general interface,
			// we don't know *exactly* what rules the game has.
			// We could add some context by printing a message
			// to the user before calling playTwoPlayerGame...
			System.out.print("Enter a move as two integers: ");
			int one = scan.nextInt();
			int two = scan.nextInt();

			while(!game.isValidMove(one,two)) {
				System.out.println("\nInvalid move! Try again");
				System.out.println(game);

				System.out.print("Enter a move as two integers: ");
				one = scan.nextInt();
				two = scan.nextInt();
			}
			game.makeMove(one,two);
			System.out.println(game);
		}
	}

	// This method accepts any object whose type implements TwoPlayerGame
	// But for this reason, we can *only* call methods that are specified
	// inside the TwoPlayerGame interface.
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter choice: Nim, RPS, Coin, or Quit");
		TwoPlayerGame game = selectGame(scan.next());

		// Display game
		System.out.println(game);
		play(game, scan);

		System.out.println("Game Over");
	}
}
