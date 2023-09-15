package data;

import constructors.Song;

import java.io.*;
import java.util.List;

/**
 * @author Joseph T. McQuigg
 * @author Milton Zarzuela
 */

public class SaveData {

    private static final String FILE_PATH = "./lib.txt";
    public static void createData() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.createNewFile())// if file already exists
            loadData(file);
    }

    public static void saveData(List<Song> allSongs){
            try {
                PrintWriter writer = new PrintWriter(FILE_PATH);
                writer.print("");
                writer.close();
                BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH));
                for (Song song : allSongs) {
                    bw.write(song.getTitle() + ":" + song.getArtist() + ":" + song.getAlbum() + ":" + song.getYear());
                    bw.newLine();
                }
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    public static void loadData(File file){
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                line = line.stripTrailing().stripLeading();
                int i = line.indexOf(":");
                String title = line.substring(0, i );
                line = line.substring(i + 1);
                i = line.indexOf(":");
                String artist = line.substring(0, i);
                line = line.substring(i + 1);
                i = line.indexOf(":");
                String album = line.substring(0, i);
                line = line.substring(i + 1);
                int year = Integer.parseInt(line);
                Song.addSong((new Song(title, artist, album, year)), true);
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
