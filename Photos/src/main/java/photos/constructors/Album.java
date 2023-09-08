package photos.constructors;

import photos.data.UserData;
import java.io.*;
import java.util.ArrayList;

/**
 * @author Joseph McQuigg
 * Album Constructor
 */
public class Album implements Serializable {

    /** The name of the album */
    private String name;

    /** The list of photos in the album */
    private ArrayList<Photo> photos;

    /**
     * Creates a new album
     * @param name The name of the album
     */
    public Album(String name) {
        this.name = name;
        this.photos = new ArrayList<>();
        try {
            UserData.writeUserData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new album
     * @param name The name of the album
     * @param photos The list of photos in the album
     */
    public Album(String name, ArrayList<Photo> photos) throws IOException {
        this.name = name;
        this.photos = photos;
        UserData.writeUserData();
    }

    /**
     * Gets the list of photos
     * @return The list of photos
     */
    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    /**
     * Adds a photo to the list of photos
     * @param photo The photo to add
     */
    public void addPhoto(Photo photo) {
        photos.add(photo);
        try {
            UserData.writeUserData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes a photo from the list of photos
     * @param photo The photo to remove
     */
    public void removePhoto(Photo photo) {
        photos.remove(photo);
        try {
            UserData.writeUserData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the name of the album
     * @return The name of the album
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the album
     * @param name The name of the album
     */
    public void setName(String name) {
        this.name = name;
        try {
            UserData.writeUserData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the album to a file
     * @param out The stream to write to
     * @throws IOException If the file cannot be written to
     */
    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(photos.size());
        for (Photo photo : photos) {
            out.writeObject(photo);
        }
    }

    /**
     * Reads the album from a file
     * @param in The stream to read from
     * @throws IOException If the file cannot be read from
     * @throws ClassNotFoundException If the class cannot be found
     */
    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        int size = in.readInt();
        photos = new ArrayList<>(size);
        for (int i = 0; i < size; i++)
            photos.add((Photo) in.readObject());
    }
}
