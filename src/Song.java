/**
 * simple class that contains information
 * about a song like the artist and song
 * name.
 *
 * @version 1.0.
 * @author Nick
 */

public class Song {
    protected String name;
    protected String artist;

    /**
     * constructor method.
     *
     * @param name song name.
     * @param artist artist name.
     */
    public Song(String name, String artist) {
        this.name = name;
        this.artist = artist;
    }

    /**
     * default constructor.
     */
    public Song() {
        this.name = "";
        this.artist = "";
    }

    /**
     * toString method.
     *
     * @return String representation of song.
     */
    public String toString() {
        return name + " by " + artist + "\n";
    }

    /**
     * return name.
     *
     * @return String name.
     */
    public String getName() {
        return name;
    }

    /**
     * return artist.
     *
     * @return String artist.
     */
    public String getArtist() {
        return artist;
    }
}
