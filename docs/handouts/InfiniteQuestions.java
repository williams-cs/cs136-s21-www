import structure5.*;
import java.util.Scanner;

public class InfiniteQuestions {

    public static void play(Scanner human, BinaryTree<String> database) {
        if (database.height() == 0) {
            // at leaf- must be single object left
            System.out.println("Is it "+database.value()+"?");
            if (human.nextLine().startsWith("y")) {
                System.out.println("I guessed it!");
            } else {
                learn(human, database);
            }
        } else {
            // further choices; must ask a question to distinguish them
            System.out.println(database.value());
            if (human.nextLine().startsWith("y")) {
                play(human,database.left());
            } else {
                play(human,database.right());
            }
        }
    }

    public static void learn(Scanner human, BinaryTree<String> leaf) {
        System.out.println("Darn.  What were you thinking of?");
        BinaryTree<String> newObject = new BinaryTree<String>(human.nextLine());
        BinaryTree<String> oldObject = new BinaryTree<String>(leaf.value());
        System.out.println("What question would distinguish "+
                           newObject.value()+" from "+
                           oldObject.value()+"?");
        leaf.setValue(human.nextLine());
        leaf.setLeft(newObject);
        leaf.setRight(oldObject);
    }

    public static void main(String args[]) {
        Scanner human = new Scanner(System.in);
        // construct a simple database -- knows only about a computer
        BinaryTree<String> database = new BinaryTree<String>("a computer");

        System.out.println("\nThink of something...I'll guess it. Press 'Return' to begin...");
        human.nextLine();
        while (true) {
            play(human,database);
            System.out.println("\nLet's play again!\n");
        }
    }
}
