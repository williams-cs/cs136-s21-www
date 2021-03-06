import java.util.Scanner;
import structure5.Association;
import structure5.Assert;

public class TrackParser {

  private static Association<String,String> parseLine(String line) {
    String key = null;
    String value = null;

    // Find tag boundaries
    int start = line.indexOf("<");
    int stop = line.indexOf(">", start);

    key = line.substring(start+1,stop);
    line = line.substring(stop+1);

    // Extract value if present
    if ( ! key.contains("TRACK")) {
      start = line.indexOf("<");
      value = line.substring(0,start);
    }

    return new Association<String,String>(key,value);
  }

  public static Track next(Scanner sc) {
    // Store track information
    String name = null;
    String artist = null;
    String album = "Single";
    String genre = "";
    int year = 0;

    // If scanner is empty, no more tracks
    if (!sc.hasNext()) return null;

    // Get tag name and value
    Association<String,String> pair = parseLine(sc.nextLine());
    String key = (String) pair.getKey();
    String value = (String) pair.getValue();

    while (!key.contains("/TRACK")) {
      switch (key) {
        case "TRACK" :
          break;
        case "TITLE" :
          name = value;
          break;
        case "ARTIST" :
          artist = value;
          break;
        case "ALBUM" :
          album = value;
          break;
        case "GENRE" :
          genre = value;
          break;
        case "YEAR" :
          year = Integer.parseInt(value);
          break;
        default :
            System.out.println("\nUnrecognized key word in track <" + key + "> ignored.\n");

      }
      pair = parseLine(sc.nextLine());
      key = (String) pair.getKey();
      value = (String) pair.getValue();
    }
    return new Track(name, artist,album,genre,year);
  }

  // class testing code
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    Track t = next(in);
    while (t != null) {
      System.out.println(t);
      t = next(in);
    }
  }
}
