package photos.ui.controller;

import photos.constructors.Album;
import photos.constructors.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import photos.scession.Current;
import photos.ui.Common;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Controller for the UserView.fxml file
 * @author Joseph McQuigg
 * @author Milton Zarzuela
 */
public class UserViewController {

	@FXML
	private Text latestDate;
	@FXML
	private Text oldestDate;
    @FXML
	private Button logoutButton;
	@FXML
	private Text numofphotos;
	@FXML
	private TextField NewAlbumName;
	@FXML
	private Button AddAlbumButton;
	@FXML
	private Button DeleteAlbumButton;
	@FXML
	private Button ChangeNameButton;
	@FXML
	private Button openButton;
	@FXML
	private Button SearchPhotos;
	@FXML
	private TextField SelectedAlbumName;
	@FXML
	private ListView<String> listView;
	@FXML
	private Text WelcomeTag;

	public static Album selectedAlbum;
	
	@FXML
	void initialize(){
		assert logoutButton != null : "fx:id=\"LogOutButton\" was not injected: Check your FXML file";
		assert numofphotos != null : "fx:id=\"numofphotos\" was not injected: Check your FXML file";
		assert NewAlbumName != null : "fx:id=\"NewAlbumName\" was not injected: Check your FXML file";
		assert SearchPhotos != null : "fx:id=\"SearchPhotos\" was not injected: Check your FXML file";
		assert SelectedAlbumName != null : "fx:id=\"SelectedAlbumName\" was not injected: Check your FXML file";
		assert listView != null : "fx:id=\"listView\" was not injected: Check your FXML file";
		assert AddAlbumButton != null : "fx:id=\"AddAlbumButton\" was not injected: Check your FXML file";
		assert DeleteAlbumButton != null : "fx:id=\"DeleteAlbumButton\" was not injected: Check your FXML file";
		assert ChangeNameButton != null : "fx:id=\"ChangeNameButton\" was not injected: Check your FXML file";
		assert openButton != null : "fx:id=\"openButton\" was not injected: Check your FXML file";
		assert WelcomeTag != null : "fx:id=\"WelcomeTag\" was not injected: Check your FXML file";
		assert oldestDate != null : "fx:id=\"oldestDate\" was not injected: Check your FXML file";
		assert latestDate != null : "fx:id=\"latestDate\" was not injected: Check your FXML file";
		if (Current.session != null) {
			WelcomeTag.setText("Welcome " + Current.session.getCurrentUser().getName());
			updateList();
		}
	}

	public void updateList() {
		User user = Current.session.getCurrentUser();
		if (user.getAlbums() == null || user.getAlbums().isEmpty()){
			listView.setItems(FXCollections.observableList(Collections.emptyList()));
			return;
		}

		ArrayList<String> albumNames = new ArrayList<>();
		for (Album album : user.getAlbums())
			albumNames.add(album.getName());
		listView.setItems(FXCollections.observableList(albumNames));
		numofphotos.setText("Number of Photos:");
		SelectedAlbumName.setText("");
	}

	@FXML
	private void AddAlbumClicked(){
		if (NewAlbumName.getText().isEmpty() || NewAlbumName.getText().isBlank()){
            new Alert(Alert.AlertType.ERROR, "Please Enter a Name for the New Album").show();
			return;
		}

		Album album = new Album(NewAlbumName.getText());
		User user = Current.session.getCurrentUser();

		boolean albumExists = false;

		for (Album a : user.getAlbums()){
			if (a.getName().equals(album.getName())) {
				albumExists = true;
				new Alert(Alert.AlertType.ERROR, "An Album Already Exists With This Name, Try Again").show();
				break;
			}
		}

		if (!albumExists){
			user.getAlbums().add(album);
		}
		updateList();
	}

	@FXML
	private void DeleteAlbumClicked() {
		User user = Current.session.getCurrentUser();
		if (user.getAlbums().isEmpty() && selectedAlbum != null) return;

		user.removeAlbum(selectedAlbum);
		updateList();
	}

	@FXML
	private void ChangeNameClicked() {
		String newName = SelectedAlbumName.getText();
		if (newName.isEmpty() || newName.isBlank() || newName.equals(selectedAlbum.getName())) return;

		boolean albumExists = false;
		for (Album album: Current.session.getCurrentUser().getAlbums()){
			if (album.getName().equals(newName)) {
				albumExists = true;
				break;
			}
		}
		if(!albumExists){
			selectedAlbum.setName(newName);
			updateList();
		}
		else{
			new Alert(Alert.AlertType.ERROR, "An Album Already Exists With This Name, Try Again").show();
		}

	}

	@FXML
	private void OpenAlbumClicked() {
		if (selectedAlbum == null) return;
		Current.session.setCurrentAlbum(selectedAlbum);
		Common.newScene("Album", (Stage) logoutButton.getScene().getWindow());
	}

	@FXML
	private void ListViewClicked() {
		if (Current.session.getCurrentUser().getAlbums().isEmpty()) return;
		if (listView.getSelectionModel().getSelectedItems() == null || listView.getSelectionModel().isEmpty()) return;
		String albumString = listView.getSelectionModel().getSelectedItems().get(0);
		for (Album album : Current.session.getCurrentUser().getAlbums())
			if (album.getName().equals(albumString)){
				SelectedAlbumName.setText(album.getName());
				numofphotos.setText(String.valueOf(album.getPhotos().size()));
				if (album.getPhotos().isEmpty()){
					oldestDate.setText("Oldest Date: N/A");
					latestDate.setText("Latest Date: N/A");
				} else {
					oldestDate.setText("Oldest Date: " + album.getPhotos().get(0).getDate());
					latestDate.setText("");
				}
				selectedAlbum = album;
				numofphotos.setText("Number of Photos: " + album.getPhotos().size());
				break;
			}
    }

	@FXML
	private void SearchPhotosClicked(){
		Common.newScene("AllPhotos", (Stage) logoutButton.getScene().getWindow());
	}

	@FXML
	private void LogoutClicked(){
		Current.session.logout();
		Common.newScene("Login", (Stage) logoutButton.getScene().getWindow());
	}
}
