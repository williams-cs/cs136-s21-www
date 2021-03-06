
import java.util.Scanner;

/*
 * A Simple Program to parse an XML file and print the titles of the CD's
 * stored inside it.
 *
 * It reads data from the terminal.  To read the contents of a file,
 * execute a command like the following:
 *
 *     java CDTitles < cds.xml
 *
 */
public class TrackTitles {

    // No constructor needed because all aspects of class are static

    // Extract and print the titles of all CD's in the xml data.
    // Because printTitles is static, it can be called from main
    public static void printTitles(String xml) {
        int startTitle = xml.indexOf("<TITLE>",0);
        while (startTitle != -1) {
            int endTitle = xml.indexOf("</TITLE>", startTitle);
            String name = xml.substring(startTitle+7, endTitle);
            System.out.println(name);
            startTitle = xml.indexOf("<TITLE>",endTitle);
        }
    }

    public static void main(String args[]) {

        // Read the all of the input into one large String.
        Scanner in = new Scanner(System.in);
        String xmlData = "";
        while (in.hasNextLine()) {
            String line = in.nextLine();
            xmlData = xmlData + line + "\n";
        }

        // print the titles
        printTitles(xmlData);
    }

}
