import structure5.*;   // Uses Duane's Association class
import java.util.Scanner;

public class Catalog {

  private Vector<Association<String, TrackList>> trackLists;

  // No-op constructor
  public Catalog() {
    trackLists = new Vector<Association<String, TrackList>>();
  }

  public int size() { return trackLists.size(); }

  public String toString() {
    String result = "";

    for (int i = 0; i < trackLists.size(); i++) {
      Association<String,TrackList> pair = trackLists.get(i);
      result += pair.getKey() + ": " + pair.getValue();
    }
    return result;
  }

  public TrackList getTrackList(String key) {
    for (Association<String,TrackList>  pair :  trackLists) {
      String pairKey = pair.getKey();
      if (key.equals(pairKey)) return pair.getValue();
    }
    return null;
  }

  public boolean contains(String key) {
      Association<String,TrackList> pair =
        new Association<String, TrackList>(key,null);
      return trackLists.contains(pair);
  }

  // Adds a new TrackList to end of catalog
  // Pre: catalog is not full
  public void add(String key, TrackList value) {
    trackLists.add(new Association<String,TrackList>(key, value));
  }

  // Adds a track to TrackList named by key
  public void addTrack(String key, Track t) {
    int location = trackLists.indexOf(new Association<String,TrackList>(key,null) );
    if (location > -1) {
      TrackList tl = trackLists.get(location).getValue();
      tl.add(t);
    }
    else {
      TrackList tl = new TrackList();
      tl.add(t);
      add(key, tl);
    }
  }

  // Return list of all TrackList names
  public String[] getKeys() {
    String[] names = new String[trackLists.size()];
    for (int i=0;i < trackLists.size(); i++) {
	     names[i] = trackLists.get(i).getKey();
    }
    return names;
  }

  // Test the class
  public static void main(String[] args) {

    Catalog c = new Catalog();

    Track track0 = new Track("Fire and Rain", "James Taylor", "Sweet Baby James", "Folk Rock", 1971);
    Track track1 = new Track("Dear Abby", "John Prine", "Sweet Revenge", "Folk", 1973);
    Track track2 = new Track("You Should See Me in a Crown", "Billie Eilish", "When We All Fall Asleep, Where Do We Go?",
            "Electropop", 2019);

    TrackList playlist0 = new TrackList();

    playlist0.add(track0);
    playlist0.add(track1);
    playlist0.add(track2);

    c.add("List 0", playlist0);

    TrackList playlist1 = new TrackList();

    playlist1.add(track1);
    playlist1.add(track2);

    c.add("List 1", playlist1);

    System.out.println(c);

    Catalog artists = new Catalog();
    Scanner sc = new Scanner(System.in);

    Track t = TrackParser.next(sc);
    while ( t != null) {
      String artist = t.getArtist();
      artists.addTrack(artist, t);
      t = TrackParser.next(sc);
    }

    System.out.println("Catalog size: " + artists.size());

    TrackList tl = artists.getTrackList("Alanis Morissette");
    System.out.println(tl);

    String[] artistNames = artists.getKeys();
    for( String name : artistNames) {
      int trackCount = artists.getTrackList(name).size();
      System.out.println("Artist: " + name + " , Tracks: " + trackCount);
    }
  }
}
