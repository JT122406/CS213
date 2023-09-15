package ui;

import constructors.Song;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
/**
 * @author Joseph T. McQuigg
 * @author Milton Zarzuela
 */

public class UI{

		@FXML
		private ResourceBundle resources;

		@FXML
		private URL location;

		@FXML
		private Button AddButton;

		@FXML
		private TextField TitleAdd;

		@FXML
		private TextField ArtistAdd;

		@FXML
		private TextField AlbumAdd;

		@FXML
		private TextField YearAdd;

		@FXML
		private TextField AlbumEdit;

		@FXML
		private TextField ArtistEdit;

		@FXML
		private Button DeleteButton;

		@FXML
		private TextField DetailsEdit;

		@FXML
		private Button EditButton;

		@FXML
		private TextField YearEdit;

		@FXML
		private Font x1;

		@FXML
		private Color x2;

		@FXML
		private Font x3;

		@FXML
		private Color x4;

		@FXML
		private ListView<String> ListViewer;

		@FXML
		private MenuItem quit;

		@FXML
		void initialize() {
			assert AddButton != null : "fx:id=\"AddButton\" was not injected: check your FXML file 'SongLib.fxml'.";
			assert TitleAdd != null : "fx:id=\"TitleAdd\" was not injected: check your FXML file 'SongLib.fxml'.";
			assert ArtistAdd != null : "fx:id=\"ArtistAdd\" was not injected: check your FXML file 'SongLib.fxml'.";
			assert AlbumAdd != null : "fx:id=\"AlbumAdd\" was not injected: check your FXML file 'SongLib.fxml'.";
			assert YearAdd != null : "fx:id=\"YearAdd\" was not injected: check your FXML file 'SongLib.fxml'.";
			assert AlbumEdit != null : "fx:id=\"AlbumEdit\" was not injected: check your FXML file 'SongLib.fxml'.";
			assert ArtistEdit != null : "fx:id=\"ArtistEdit\" was not injected: check your FXML file 'SongLib.fxml'.";
			assert DeleteButton != null : "fx:id=\"DeleteButton\" was not injected: check your FXML file 'SongLib.fxml'.";
			assert DetailsEdit != null : "fx:id=\"DetailsEdit\" was not injected: check your FXML file 'SongLib.fxml'.";
			assert EditButton != null : "fx:id=\"EditButton\" was not injected: check your FXML file 'SongLib.fxml'.";
			assert YearEdit != null : "fx:id=\"YearEdit\" was not injected: check your FXML file 'SongLib.fxml'.";
			assert x1 != null : "fx:id=\"x1\" was not injected: check your FXML file 'SongLib.fxml'.";
			assert x2 != null : "fx:id=\"x2\" was not injected: check your FXML file 'SongLib.fxml'.";
			assert x3 != null : "fx:id=\"x3\" was not injected: check your FXML file 'SongLib.fxml'.";
			assert x4 != null : "fx:id=\"x4\" was not injected: check your FXML file 'SongLib.fxml'.";
			assert ListViewer != null : "fx:id=\"ListViewer\" was not injected: check your FXML file 'SongLib.fxml'.";
			assert quit != null : "fx:id=\"Quit\" was not injected: check your FXML file 'SongLib.fxml'.";
		}

		@FXML
		private void ListViewerClick(MouseEvent event) {
			if (Song.allSongs.isEmpty()) return;
			if (ListViewer.getSelectionModel().getSelectedItems() == null) return;
			String items = ListViewer.getSelectionModel().getSelectedItems().get(0);
			for (Song song : Song.allSongs)
				if (song.getWording().equals(items)){
					DetailsEdit.setText(song.getTitle());
					ArtistEdit.setText(song.getArtist());
					AlbumEdit.setText(song.getAlbum());
					if (song.getYear() == -1) YearEdit.clear();
					else YearEdit.setText(String.valueOf(song.getYear()));
					break;
				}
		}

		@FXML
		private void AddButtonClick(ActionEvent event) {
			if (TitleAdd == null || TitleAdd.getText().isBlank() || ArtistAdd == null || ArtistAdd.getText().isBlank()){
				JOptionPane.showMessageDialog(null, "Please enter a title and artist");
				return;
			}
			String title = TitleAdd.getText();
			String artist = ArtistAdd.getText();
			String album = AlbumAdd == null || AlbumAdd.getText().isBlank() ? "" : AlbumAdd.getText();
			int year = YearAdd == null || YearAdd.getText().isBlank() ? -1 : Integer.parseInt(YearAdd.getText());
			Song song = new Song(title, artist, album, year);
			Song.addSong(song, false);
			TitleAdd.clear();
			ArtistAdd.clear();
			AlbumAdd.clear();
			YearAdd.clear();
			updateList();
		}

		@FXML
		public void DeleteButtonClick(ActionEvent event){
			if (Song.allSongs.isEmpty()) return;
			String items = ListViewer.getSelectionModel().getSelectedItems().get(0);
			for (Song song : Song.allSongs)
				if (song.getWording().equals(items)){
					Song.removeSong(song);
					break;
				}
			updateList();
			clearAll();
		}

		@FXML
		private void EditButtonClick(ActionEvent event) {
			if (Song.allSongs.isEmpty()) return;
			String items = ListViewer.getSelectionModel().getSelectedItems().get(0);
			for (Song song : Song.allSongs)
				if (song.getWording().equals(items)) {
					Song.editSong(Song.allSongs.indexOf(song), DetailsEdit.getText(), ArtistEdit.getText(), AlbumEdit.getText(), YearEdit.getText().isBlank() ? -1 : Integer.parseInt(YearEdit.getText()));
					break;
				}
			updateList();
			clearAll();
		}

		public void clearAll(){
			TitleAdd.clear();
			DetailsEdit.clear();
			ArtistAdd.clear();
			ArtistEdit.clear();
			AlbumAdd.clear();
			AlbumEdit.clear();
			YearAdd.clear();
			YearEdit.clear();
		}

		public void start() {
			updateList();
		}

		public void updateList(){
			if (Song.allSongs.isEmpty()){
				ListViewer.setItems(null);
				disableEdits();
				return;
			}
			enableEdits();
			ArrayList<String> songList = new ArrayList<>();
			for (Song song: Song.allSongs) songList.add(song.getWording());
			ListViewer.setItems(FXCollections.observableList(songList));
			ListViewer.getSelectionModel().selectFirst();
		}

		private void enableEdits(){
			DeleteButton.setDisable(false);
			EditButton.setDisable(false);
			DetailsEdit.setDisable(false);
			AlbumEdit.setDisable(false);
			ArtistEdit.setDisable(false);
			YearEdit.setDisable(false);
		}

		private void disableEdits(){
			DeleteButton.setDisable(true);
			EditButton.setDisable(true);
			DetailsEdit.setDisable(true);
			AlbumEdit.setDisable(true);
			ArtistEdit.setDisable(true);
			YearEdit.setDisable(true);
		}

		@FXML
		private void QuitClick(ActionEvent event) {
			Platform.exit();
		}
	}
