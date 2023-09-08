package photos.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import photos.scession.Current;
import photos.ui.Common;

/**
 * Controller for the FullView.fxml file
 * @author Joseph McQuigg
 * @author Milton Zarzuela
 */
public class FullViewController {

    @FXML
    private Text Date;

    @FXML
    private Text Caption;

    @FXML
    private Text name;

    @FXML
    private ImageView image;

    @FXML
    private Button back;

    @FXML
    void initialize() {
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'FullView.fxml'.";
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'FullView.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'FullView.fxml'.";
        assert Date != null : "fx:id=\"Date\" was not injected: check your FXML file 'FullView.fxml'.";
        assert Caption != null : "fx:id=\"Caption\" was not injected: check your FXML file 'FullView.fxml'.";
        image.setImage(Current.session.getCurrentPhoto().getImage());
        name.setText(Current.session.getCurrentPhoto().getPath().substring(Current.session.getCurrentPhoto().getPath().lastIndexOf("\\") + 1, Current.session.getCurrentPhoto().getPath().lastIndexOf(".")));
        Date.setText(Current.session.getCurrentPhoto().updateDate());
        Caption.setText(Current.session.getCurrentPhoto().getCaption());
    }

    @FXML
    public void BackClicked() {
        Current.session.setCurrentPhoto(null);
        Common.newScene("Album", (Stage) back.getScene().getWindow());
    }
}
