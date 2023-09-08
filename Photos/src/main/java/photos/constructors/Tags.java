package photos.constructors;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Tags Constructor
 * @author Milton Zarzuela
 */
public class Tags implements Serializable {
    public static HashMap<String, ArrayList<String>> allTags;

    public static void init(){
        allTags = new HashMap<>();
        ArrayList<String> locations = new ArrayList<>(
                Arrays.asList("New Jersey", "New York", "Iowa", "Florida", "Alabama", "Ohio"));
        allTags.put("Location", locations);

        ArrayList<String> people = new ArrayList<>(
                Arrays.asList("Milton", "Joeseph", "Mark", "Daniel", "Kyle"));
        allTags.put("People", people);

        ArrayList<String> favorites = new ArrayList<>();
        allTags.put("Favorites", favorites);
    }

    public static HashMap<String, ArrayList<String>> DefaultTags(){
       HashMap <String, ArrayList<String>> output = new HashMap<>();

        ArrayList<String> locations = new ArrayList<>(
                Arrays.asList("New Jersey", "New York", "Iowa", "Florida", "Alabama", "Ohio"));
            output.put("Location", locations);

        ArrayList<String> people = new ArrayList<>(
                Arrays.asList("Milton", "Joeseph", "Mark", "Daniel", "Kyle"));
            output.put("People", people);

        ArrayList<String> favorites = new ArrayList<>();
            output.put("Favorites", favorites);

        return output;
    }

    /**
     * Saves the tags to a file
     * @param out the output stream
     * @throws IOException if the file cannot be written to
     */
    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(allTags);
    }

    /**
     * Reads the tags from a file
     * @param in the input stream
     * @throws IOException if the file cannot be read from
     * @throws ClassNotFoundException if the class cannot be found
     */
    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        allTags = (HashMap<String, ArrayList<String>>) in.readObject();
    }
}
