package photos.data;

import photos.constructors.Admin;
import photos.constructors.User;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Joseph T. McQuigg
 * Saves and loads user photos using serialization
 */
public class UserData {

	/**The directory where the user photos is stored*/
	public static final String storeDir = "userdata";

	/**The file where the user photos is stored*/
	public static final String storeFile = "user.dat";

	/**
	 * Writes the user photos to a file
	 * @throws IOException If the file cannot be written to
	 */
	@SuppressWarnings("BlockingMethodInNonBlockingContext")
	public static void writeUserData() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(storeDir + File.separator + storeFile));

		for (User u : Admin.allUsers)
			oos.writeObject(u);

		oos.writeObject(new EOF());
		oos.close();
	}

	/**
	 * Reads the user photos from a file
	 * @return The user photos
	 * @throws IOException If the file cannot be read from
	 * @throws ClassNotFoundException If the file is not in the correct format
	 */
	public static ArrayList<User> readUserData() throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(storeDir + File.separator + storeFile));
		ArrayList<User> users = new ArrayList<>();
		while (ois.readObject() instanceof User u)
			users.add(u);
		ois.close();
		return users;
	}

	/**
	 * Creates the user photos file
	 * @throws IOException If the file cannot be created
	 */
	@SuppressWarnings("ResultOfMethodCallIgnored")
	public static void createFile () throws IOException {
		new File(storeDir).mkdir();
		File file = new File(storeDir + File.separator + storeFile);
		file.createNewFile();
	}

	/**
	 * Checks if the user photos file exists
	 * @return True if the file exists, false otherwise
	 */
	public static boolean dataExists() {
		return new File(storeDir + File.separator + storeFile).exists();
	}

}

/**
 * Used to mark the end of the user photos file
 * @author Joseph T. McQuigg
 */
class EOF implements Serializable {

}
