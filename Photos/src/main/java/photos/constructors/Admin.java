package photos.constructors;

import javafx.scene.control.Alert;
import photos.data.UserData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Joseph McQuigg
 * @author Milton Zarzuela
 * Admin UI Controller
 */
public class Admin {

    /**The list of all users*/
    public static ArrayList<User> allUsers;
    public static User selectedUser;

    public static void init() throws IOException {
        //Check for photos.data and import if exists. if not run below
        if (!UserData.dataExists()) {
            UserData.createFile();
            allUsers = new ArrayList<>();
            createStockUser();
            UserData.writeUserData();
        } else
            try {
                allUsers = UserData.readUserData();
            } catch (Exception e) {
                e.printStackTrace();
            }

        selectedUser = null;
    }

    /**
     * Creates a stock user with a stock album and photos
     */
    private static void createStockUser() {
        User stock = new User("stock");
        Album stockAlbum = new Album("stock");

        String basePath = new File("").getAbsolutePath();
        try {
            File[] file = new File(basePath + File.separator + "data" + File.separator + "stock").listFiles();
            assert file != null;
            for (File f : file) stockAlbum.addPhoto(new Photo(f.getPath(), "Stock Photo"));
            stock.addAlbum(stockAlbum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a user to the list of users
     * @param name the name of the user
     */
    public static void addUser(String name) {
        if (!allUsers.isEmpty())
            for (User u : allUsers)
                if (u.getName().equals(name)) {
                    new Alert(Alert.AlertType.ERROR, "This Username is Taken, Try Again.").show();
                    return;
                }
        new User(name);
    }

    /**
     * Removes a user from the list of users
     * @param user the user to be removed
     */
    public static void removeUser(User user) {
        if (allUsers.isEmpty() || !allUsers.contains(user)) return;
        allUsers.remove(user);
        try {
            UserData.writeUserData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
