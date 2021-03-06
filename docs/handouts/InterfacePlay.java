/** InterfacePlay.java
	*
	* Allows player to choose any TwoPlayerGame and play it
	*/

import java.util.Scanner;

public class InterfacePlay {

	// This method accepts any object whose type implements TwoPlayerGame
	// But for this reason, we can *only* call methods that are specified
	// inside the TwoPlayerGame interface.
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		TwoPlayerGame game = null;

		System.out.println("Enter choice: Nim, RPS, Coin, or Quit");
		String gameName = scan.next();
		if(gameName.equals("Nim")) {
			game = new InterfaceNim();
		}
		else if(gameName.equals("RPS")) {
			game = new RockPaperScissors();
		}
		else if(gameName.equals("Coin")) {
			game = new CoinStrip();
		}
		else {
			System.exit(0);

		}
		while (!game.isGameOver()) {
			System.out.println(game);

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
		}

		System.out.println("Game Over");
	}

}
