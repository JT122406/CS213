package photos.constructors;

import photos.data.UserData;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Joseph McQuigg
 * @author Milton Zarzuela
 * User Constructor
 */
public class User implements Serializable {

    /** The name of the user */
    private String name;

    /** The list of albums the user has */
    private transient ArrayList<Album> albums;

    /** The list of tags the user has */
    private HashMap<String, ArrayList<String>> userTags;

    /**
     * Creates a new user
     * @param name The name of the user
     */
    public User(String name){
        this.name = name;
        this.albums = new ArrayList<>();
        this.userTags = Tags.DefaultTags();
        Admin.allUsers.add(this);
        try {
            UserData.writeUserData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the list of albums
     * @return The list of albums
     */
    public ArrayList<Album> getAlbums(){
        return albums;
    }

    /**
     * Adds an album to the list of albums
     * @param album The album to add
     */
    public void addAlbum(Album album){
        albums.add(album);
    }

    /**
     * Removes an album from the list of albums
     * @param album The album to remove
     */
    public void removeAlbum(Album album){
        albums.remove(album);
    }

    /**
     * Gets the name of the user
     * @return The name of the user
     */
    public String getName(){
        return this.name;
    }

    public HashMap<String, ArrayList<String>> getUserTags(){
        return userTags;
    }

    // If method returns false, UserViewController displays a popup error
    public boolean addTagKey(String tagKey){
        ArrayList <String> tagValues = new ArrayList<>();
        if(!userTags.containsKey(tagKey)){
            userTags.put(tagKey, tagValues);
            return true;
        } 
        return false;
    }

    // If method returns false, UserViewController displays a popup error
    public boolean addTagValue(String tagKey, String tagValue){
        if (userTags.containsKey(tagKey)){
            userTags.get(tagKey).add(tagValue);
            return true;
        }
        return false;
    }

    /**
     * Writes the user photos.data to a file
     * @param out The stream to write to
     * @throws IOException If the file cannot be written to
     */
    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(albums.size());
        for (Album album : albums) out.writeObject(album);
    }

    /**
     * Reads the user photos.data from a file
     * @param in The stream to read from
     * @throws IOException If the file cannot be read from
     * @throws ClassNotFoundException If the file is not in the correct format
     */
    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        int size = in.readInt();
        albums = new ArrayList<>(size);
        for (int i = 0; i < size; i++) albums.add((Album) in.readObject());
    }
}
