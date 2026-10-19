package Qno2;
import java.util.*;

class Song {
    String songId;
    String title;
    String artist;

    Song(String songId, String title, String artist) {
        this.songId = songId;
        this.title = title;
        this.artist = artist;
    }

    @Override
    public String toString() {
        return title + " - " + artist;
    }

    // Songs with the same ID are considered duplicates
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Song)) return false;

        Song other = (Song) obj;
        return songId.equals(other.songId);
    }

    @Override
    public int hashCode() {
        return songId.hashCode();
    }
}

public class MediaPlayerQueue {

    LinkedList<Song> playlist = new LinkedList<>();
    LinkedList<Song> recentHistory = new LinkedList<>();

    // Add a song to the end of the playlist
    void addSong(Song song) {
        playlist.addLast(song);
        System.out.println("Added: " + song);
    }

    // Add a priority song to the front
    void addPrioritySong(Song song) {
        playlist.addFirst(song);
        System.out.println("Priority song added: " + song);
    }

    // Insert a song at a specified position
    void insertSong(int position, Song song) {
        if (position < 0 || position > playlist.size()) {
            System.out.println("Invalid position.");
            return;
        }

        playlist.add(position, song);
        System.out.println("Inserted at position "
                + position + ": " + song);
    }

    // Remove duplicate songs while preserving first occurrences
    void removeDuplicates() {
        HashSet<Song> seen = new HashSet<>();
        Iterator<Song> iterator = playlist.iterator();

        while (iterator.hasNext()) {
            Song song = iterator.next();

            if (!seen.add(song)) {
                iterator.remove();
            }
        }

        System.out.println("Duplicate songs removed.");
    }

    // Play the next song and record it in recent history
    void playNext() {
        if (playlist.isEmpty()) {
            System.out.println("Playlist is empty.");
            return;
        }

        Song song = playlist.removeFirst();
        recentHistory.addFirst(song);

        System.out.println("Now playing: " + song);
    }

    // Display playlist using ListIterator in forward direction
    void showForward() {
        System.out.println("\nPlaylist (Forward):");

        ListIterator<Song> iterator = playlist.listIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    // Display playlist using ListIterator in backward direction
    void showBackward() {
        System.out.println("\nPlaylist (Backward):");

        ListIterator<Song> iterator =
                playlist.listIterator(playlist.size());

        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }
    }

    // Display recently played songs
    void showRecentHistory() {
        System.out.println("\nRecently Played:");

        if (recentHistory.isEmpty()) {
            System.out.println("No songs played yet.");
            return;
        }

        for (Song song : recentHistory) {
            System.out.println(song);
        }
    }

    // Display the complete playlist
    void showPlaylist() {
        System.out.println("\nCurrent Playlist:");

        if (playlist.isEmpty()) {
            System.out.println("Playlist is empty.");
            return;
        }

        for (Song song : playlist) {
            System.out.println(song);
        }
    }

    public static void main(String[] args) {

        MediaPlayerQueue player = new MediaPlayerQueue();

        Song s1 = new Song("S01", "Perfect", "Ed Sheeran");
        Song s2 = new Song("S02", "Believer", "Imagine Dragons");
        Song s3 = new Song("S03", "Faded", "Alan Walker");
        Song s4 = new Song("S02", "Believer", "Imagine Dragons");
        Song s5 = new Song("S05", "Blinding Lights", "The Weeknd");

        // Add songs
        player.addSong(s1);
        player.addSong(s2);
        player.addSong(s3);
        player.addSong(s4);

        // Add a priority song
        player.addPrioritySong(s5);

        // Insert a song at position 2
        player.insertSong(2,
                new Song("S06", "Shape of You", "Ed Sheeran"));

        // Remove duplicates
        player.removeDuplicates();

        // Display in both directions
        player.showForward();
        player.showBackward();

        // Play songs and update history
        player.playNext();
        player.playNext();

        // Show recently played songs
        player.showRecentHistory();

        // Show remaining playlist
        player.showPlaylist();
    }
}
