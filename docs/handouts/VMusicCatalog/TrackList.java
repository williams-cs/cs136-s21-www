// Vector-based TrackList

import structure5.*;

public class TrackList {

  private Vector<Track> tracks;

  // create a TrackList
  public TrackList() { tracks = new Vector<Track>(); }

  // Return number of tracks in TrackList
  public int size() { return tracks.size(); }

  // Return track i
  // Pre: 0 <= i < tracks.size()
  public Track get(int i) {return tracks.get(i);}

  // Add a track to the end of the TrackList
  public void add(Track t) {
    tracks.add(t);
  }

  // Does tracklist contain t
  public boolean contains(Track t) { return tracks.contains(t); }

  public String toString() {
    String result = "";
    for(int i = 0; i < tracks.size(); i++) result += tracks.get(i) + "\n";
    return result;
  }

  /** Testing code */
  public static void main(String[] args) {
    Track track0 = new Track("Fire and Rain", "James Taylor", "Sweet Baby James", "Folk Rock", 1971);
    Track track1 = new Track("Dear Abby", "John Prine", "Sweet Revenge", "Folk", 1973);
    Track track2 = new Track("You Should See Me in a Crown", "Billie Eilish", "When We All Fall Asleep, Where Do We Go?",
            "Electropop", 2019);

    TrackList playlist = new TrackList();

    playlist.add(track0);
    playlist.add(track1);
    playlist.add(track2);
    System.out.println(playlist);       // print a blank line

    // Print the artist names
    for (int i = 0; i < playlist.size(); i++)
      System.out.println(playlist.get(i).getArtist());

    System.out.println();

  }
}
