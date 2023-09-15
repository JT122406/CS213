import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ui.UI;

import java.io.IOException;

import static data.SaveData.createData;
/**
 * @author Joseph T. McQuigg
 * @author Milton Zarzuela
 */

public class SongLib extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Pane root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SongLib.fxml"));
        root = loader.load();
        root.autosize();
        UI ui = loader.getController();
        ui.start();
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.setTitle("SongLib");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("app-icon.png")));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        createData();
        launch(args);
    }

}
