package constructors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static data.SaveData.saveData;

/**
 * @author Joseph T. McQuigg
 * @author Milton Zarzuela
 */

public class Song implements Comparable<Song> {
    private String title;
    private String artist;
    private String album;
    private int year;
    private String wording;
    public static List<Song> allSongs = new ArrayList<>();
    
    public Song(String title, String artist, String album, int year){
        this.artist = artist.trim();
        this.title = title.trim();
        this.album = album.trim();
        this.year = year;
        String albumText = album.isBlank() ? "" : " - Album: " + album;
        String yeartext = year == -1 ? "" : " - Year: " + year;
        this.wording = "Title: " + title + " - Artist: " + artist + albumText + yeartext;
    }

    public static void addSong(Song song, boolean first) {
        allSongs.add(song);
        if (allSongs.size() > 1) songSort(allSongs);
        if (!first) saveData(allSongs);
    }

    public static boolean isDuplicate(Song song){
        for (Song song1 : allSongs)
            if (song.title.equals(song1.title) && song.artist.equals(song1.artist)) return true;
        return false;
    }

    public static void removeSong(Song song){
        allSongs.remove(song);
        saveData(allSongs);
    }

    public static void removeSong(int index){
        allSongs.remove(index);
        saveData(allSongs);
    }

    public static void editSong(int index, String title, String artist, String album, int year){
        Song song = allSongs.get(index);
        song.setTitle(title);
        song.setArtist(artist);
        song.setAlbum(album);
        song.setYear(year);
        String albumText = album.isBlank() ? "" : " - Album: " + album;
        String yeartext = year == -1 ? "" : " - Year: " + year;
        song.setWording("Title: " + title + " - Artist: " + artist + albumText + yeartext);
        songSort(allSongs);
        saveData(allSongs);
    }

    @Override
    public int compareTo(Song other) {
        return this.title.compareTo(other.title);
    }
    
    public static void printAllSongs(){
        for (Song song : allSongs) {
            String title = song.title;
            String artist = song.artist;
            String album = song.album;
            int year = song.year;
            System.out.println("track: " + title + " by " + artist);
        }
    }


    // getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title.trim();
    }

    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist.trim();
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album.trim();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getWording(){
        return wording;
    }

    public void setWording(String wording){
        this.wording = wording;
    }

    private static List<Song> songSort(List<Song> songs){
        songs.sort(Comparator.comparing(Song::getArtist)); //Sorts the list of songs alphabetically by artist
        Collections.sort(songs);  //Sorts the list of songs alphabetically by title
        return songs;
    }
}
