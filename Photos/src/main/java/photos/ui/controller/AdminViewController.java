package photos.ui.controller;

import photos.constructors.Admin;
import photos.constructors.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import photos.ui.Common;

import java.util.ArrayList;

/**
 * Controller for the AdminView.fxml file
 * @author Joseph T. McQuigg
 * @author Milton Zarzuela
 */
public class AdminViewController {
    
    @FXML
    private Button addUserButton;

    @FXML
    private Button deleteUserButton;

    @FXML
    private TextField textField;

    @FXML
    private ListView<String> listView;

    @FXML
    private Button logoutButton;

    @FXML
    void initialize() {
        assert addUserButton != null : "fx:id=\"addUserButton\" was not injected: Check your FXML file";
        assert deleteUserButton != null : "fx:id=\"deleteUserButton\" was not injected: Check your FXML file";
        assert textField != null : "fx:id=\"textField\" was not injected: Check your FXML file";
        assert listView != null : "fx:id=\"listView\" was not injected: Check your FXML file";
		if (Admin.allUsers != null) updateList();
    }

    @FXML
	private void ListViewClicked() {
		if (Admin.allUsers.isEmpty()) return;
		if (listView.getSelectionModel().getSelectedItems() == null) return;
		String userString = listView.getSelectionModel().getSelectedItems().get(0);
		for (User user : Admin.allUsers)
			if (user.getName().equals(userString)){
				textField.setText(user.getName());
				Admin.selectedUser = user;
				break;
			}
    }

    @FXML
	private void AddUserClicked() {
		if (textField.getText().isEmpty() || textField.getText().isBlank()){
            new Alert(Alert.AlertType.ERROR, "Please Enter a Username for the New User").show();
			return;
		}
		String userString = textField.getText();
		if (userString.equalsIgnoreCase("admin")){
			new Alert(Alert.AlertType.ERROR, "Admin is a reserved username").show();
			return;
		}

		for (User user : Admin.allUsers)
			if (user.getName().equals(userString)){
				new Alert(Alert.AlertType.ERROR, "User already exists").show();
				return;
			}

		Admin.addUser(userString);
		textField.clear();
		updateList();
	}

    @FXML
	private void DeleteUserClicked() {
		if (Admin.allUsers.isEmpty()) return;
		String userString = listView.getSelectionModel().getSelectedItems().get(0);
		for (User user : Admin.allUsers)
			if (user.getName().equals(userString)){
				Admin.removeUser(user);
				break;
			}
		updateList();
		textField.clear();
	}

    public void updateList() {
		if (Admin.allUsers == null || Admin.allUsers.isEmpty()) {
            listView.setItems(null);
        }
		ArrayList<String> userList = new ArrayList<>();
		for (User user: Admin.allUsers) {
            userList.add(user.getName());
        }
		listView.setItems(FXCollections.observableList(userList));
		listView.getSelectionModel().selectFirst();
	}

    @FXML
    private void LogoutClicked() {
        //Transition back to LoginViewController
		Common.newScene("Login", (Stage) logoutButton.getScene().getWindow());
    }

	@FXML
	private void textboxEnter(KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)) AddUserClicked();
	}
}
