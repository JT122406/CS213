package photos;

import photos.constructors.Admin;
import photos.constructors.Tags;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * @author Joseph McQuigg
 */
public class Photos extends Application {

	/**
	 * Main method to launch the application
	 */
	public static void main(String[] args) throws IOException {
		Admin.init();
		Tags.init();
		launch(args);
	}

	/**
	 * Start method to launch the application
	 * @param primaryStage the primary stage
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane root;
		FXMLLoader loader = new FXMLLoader(getResource("Login.fxml"));
		root = loader.load();
		root.autosize();
		primaryStage.setScene(new Scene(root));
		primaryStage.sizeToScene();
		primaryStage.setTitle("Photos");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	/**
	 * Gets the resource.
	 * @param path the path
	 * @return the resource
	 */
	public static URL getResource(String path) {
		return Photos.class.getResource(path);
	}
}
