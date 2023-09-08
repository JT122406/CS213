package photos.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import photos.Photos;

/**
 * @author Joseph McQuigg
 * Common UI Class for Common things across all UI Controllers
 */
public class Common {

	public static void newScene(String name, Stage stage) {
		Parent parent;
		FXMLLoader loader = new FXMLLoader(Photos.getResource(name +  ".fxml"));
		try {
			parent = loader.load();
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.show();
			stage.setTitle("Photos - " +  name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
