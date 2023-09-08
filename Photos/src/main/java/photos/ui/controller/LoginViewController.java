package photos.ui.controller;

import photos.constructors.Admin;
import photos.constructors.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import photos.scession.Current;
import photos.scession.Session;
import photos.ui.Common;

/**
 * Controller for the Login.fxml file
 * @author Joseph McQuigg
 * @author Milton Zarzuela
 */
public class LoginViewController {

    /** The login button. */
    @FXML
    private Button loginButton;

    /** The username field. */
    @FXML
    private TextField usernameField;

    /**
     * Initializes the controller class.
     */
    @FXML
    void initialize() {
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'Login.fxml'.";
        assert usernameField != null : "fx:id=\"usernameField\" was not injected: check your FXML file 'Login.fxml'.";
    }

    /**
     * Login button click.
     */
    @FXML
    private void loginButtonClick() {
        login();
    }

    @FXML
    private void usernameFieldEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) login();
    }

    private void login() {
        if (usernameField.getText().isBlank() || usernameField.getText().isEmpty())
           new Alert(Alert.AlertType.ERROR, "Please Enter a Username").show();
        else if (usernameField.getText().equalsIgnoreCase("admin")) Common.newScene("Admin", (Stage) loginButton.getScene().getWindow());
        else {
            String name = usernameField.getText();
            if (!Admin.allUsers.isEmpty())
                for (User user : Admin.allUsers)
                    if (user.getName().equalsIgnoreCase(name)) {
                        Current.session = new Session(user);
                        Common.newScene("User", (Stage) loginButton.getScene().getWindow());
                        return;
                    }
            new Alert(Alert.AlertType.ERROR, "No user found with that name").show();
        }
    }

}
