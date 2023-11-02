package edu.rutgers.chess.storage;

import java.io.*;
import java.util.ArrayList;

public class SaveData {

	/**The directory where the user data is stored*/
	public static final String storeDir = "userdata";

	/**The file where the user data is stored*/
	public static final String storeFile = "user.dat";

	/**
	 * Writes the user data to a file
	 * @throws IOException If the file cannot be written to
	 */
	public static void writeUserData() throws IOException {
		if (!dataExists()) createFile();
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(storeDir + File.separator + storeFile));
		for (GameData game : GameData.games)
			oos.writeObject(game);
		oos.writeObject(new EOF());
		oos.close();
	}

	/**
	 * Reads the user photos from a file
	 *
	 * @throws IOException            If the file cannot be read from
	 * @throws ClassNotFoundException If the file is not in the correct format
	 */
	public static void readUserData() throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(storeDir + File.separator + storeFile));
		ArrayList<GameData> games = new ArrayList<>();
		while (ois.readObject() instanceof GameData)
			games.add((GameData) ois.readObject());
		ois.close();

		GameData.games = games;
	}

	/**
	 * Creates the user data file
	 * @throws IOException If the file cannot be created
	 */
	@SuppressWarnings("ResultOfMethodCallIgnored")
	public static void createFile () throws IOException {
		new File(storeDir).mkdir();
		File file = new File(storeDir + File.separator + storeFile);
		file.createNewFile();
	}

	/**
	 * Checks if the user data file exists
	 * @return True if the file exists, false otherwise
	 */
	public static boolean dataExists() {
		return new File(storeDir + File.separator + storeFile).exists();
	}

}

/**
 * Used to mark the end of the user data file
 * @author Joseph T. McQuigg
 */
class EOF implements Serializable {}
