public class Track {

  private String title;
  private String artist;
  private String album = "Single";  // Default value is Single
  private String genre = "";  // Default value is empty string
  private int year = 0;         // Default value is 0

  public Track(String title, String artist, String album, String genre, int year) {
    this.title = title;
    this.artist = artist;
    this.album = album;
    this.genre = genre;
    this.year = year;
  }

  // Java allows multiple constructors
  public Track(String title, String artist) {
    this.title = title;
    this.artist = artist;
  }

  // In fact, one constructor can call another....
  public Track(String title, String artist, String album) {
    this(title, artist, album, "", 0);
  }

  // Create a String representation of the track
  public String toString() {
    String result = "Title: " + title + ", ";
    result += "Artist: " + artist + ", ";
    result += "Album: " + album + ", ";
    result += "Genre: " + genre + ", ";
    result += "Year: " + year;
    return result;
  }

  public boolean equals (Object other) {
    if (other instanceof Track) {
      Track ot = (Track) other;
      return title.equals(ot.title) && artist.equals(ot.artist) && album.equals(ot.album);
    }
    return false;
  }

  // Accessor methods
  public String getTitle() { return title; }

  public String getArtist() { return artist; }

  public String getAlbum() { return album; }

  public String getGenre() { return genre; }

  public int getYear() { return year; }

  /** Testing code */
  public static void main(String[] args) {
    Track track0 = new Track("Fire and Rain", "James Taylor", "Sweet Baby James", "Folk Rock", 1971);
    Track track1 = new Track("Dear Abby", "John Prine", "Sweet Revenge", "Folk", 1973);
    Track track2 = new Track("You Should See Me in a Crown", "Billie Eilish", "When We All Fall Asleep, Where Do We Go?",
            "Electropop", 2019);
    Track track3 = new Track("Fire and Rain", "James Taylor", "Greatest Hits", "Folk Rock", 1976);

    System.out.println(track0);
    System.out.println(track1);
    System.out.println(track2);
    System.out.println();       // print a blank line

    Track[] tracks = new Track[3];
    tracks[0] = track0;
    tracks[1] = track1;
    tracks[2] = track2;

    // Print the artist names
    for (int i = 0; i < tracks.length; i++) System.out.println(tracks[i].artist);
    System.out.println();

    // Print the track titles using "for-each" variant
    for ( Track t : tracks) System.out.println(t.title);
    System.out.println();
  }
}
