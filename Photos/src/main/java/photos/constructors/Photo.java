package photos.constructors;

import javafx.scene.image.Image;
import photos.data.UserData;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Photo Constructor
 * @author Joseph T. McQuigg
 * @author Milton Zarzuela
 */
public class Photo implements Serializable {

    /** The date of the photo */
    private String dateString;
    private HashMap <String, ArrayList<String>> photoTags;
    private Calendar date;

    /** The path of the photo */
    private String path;

    /** The Caption of the photo */
    private String caption;


    /**
     * Creates a new photo
     * @param path The path of the photo
     */
    public Photo(String path){
        this.date = Calendar.getInstance();
        this.dateString = this.date.getTime().toString();
        this.photoTags = new HashMap<>();
        this.path = path;
        this.caption = "";
        try {
            UserData.writeUserData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new photo
     * @param path The path of the photo
     * @param caption The caption of the photo
     */
    public Photo(String path, String caption){
        this.date = Calendar.getInstance();
        this.dateString = this.date.getTime().toString();
        this.photoTags = new HashMap<>();
        this.path = path;
        this.caption = caption;
        try {
            UserData.writeUserData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the date of the photo
     * @return The date of the photo
     */
    public String getDate(){
        return dateString;
    }

    /**
     * Updates the date of the photo
     * @return The date of the photo
     */
    public String updateDate() {
        this.date = Calendar.getInstance();
        this.dateString = this.date.getTime().toString();
        try {
            UserData.writeUserData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dateString;
    }

    public void addTag(String key, String value){
        if(photoTags.containsKey(key)){
            photoTags.get(key).add(value);
        }
        try {
            UserData.writeUserData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public HashMap<String, ArrayList<String>> getTags(){
        return this.photoTags;
    }

    /**
     * Gets the path of the photo
     * @return The path of the photo
     */
    public String getPath(){
        return path;
    }

    /**
     * Sets the path of the photo
     * @param path The path of the photo
     */
    public void setPath(String path){
        this.path = path;
        try {
            UserData.writeUserData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the caption of the photo
     * @param caption The caption of the photo
     */
    public void setCaption(String caption) {
        this.caption = caption;
        try {
            UserData.writeUserData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the caption of the photo
     * @return The caption of the photo
     */
    public String getCaption(){
        return caption;
    }

    /**
     * Gets the Image of the photo
     * @return The Image of the photo
     */
    public Image getImage(){
        return new Image(new File(path).toURI().toString());
    }

    /**
     * Writes the photo to a file
     * @param out The output stream
     * @throws IOException If the file cannot be written to
     */
    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(photoTags);
    }

    /**
     * Reads the photo from a file
     * @param in The input stream
     * @throws IOException If the file cannot be read from
     * @throws ClassNotFoundException If the class cannot be found
     */
    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        photoTags = new HashMap<>();
        HashMap<String, ArrayList<String>> tags = (HashMap<String, ArrayList<String>>) in.readObject();
        photoTags.putAll(tags);
    }
}
