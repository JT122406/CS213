package photos.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import photos.constructors.Album;
import photos.constructors.Photo;
import photos.data.UserData;
import photos.scession.Current;
import photos.ui.Common;

import java.io.File;

/**
 * Controller for the AlbumView.fxml file
 * @author Joseph McQuigg
 * @author Milton Zarzuela
 */
public class AlbumViewController {

    @FXML
    private Button openPhoto;

    @FXML
    private Button logout;

    @FXML
    private Button back;

    @FXML
    private Text name;

    @FXML
    private Button rename;

    @FXML
    private Button ReCaption;

    @FXML
    private Text Name;

    @FXML
    private Button addPhoto;

    @FXML
    private Button deletePhoto;

    @FXML
    private Button addTag;

    @FXML
    private Button addtoAnotherAlbum;

    @FXML
    private Button deleteTag;

    @FXML
    private Button movePhoto;

    @FXML
    private ImageView imageViewer;

    @FXML
    private TextField textfield;

    @FXML
    private TilePane imageGrid;

    @FXML
    private Text caption;

    @FXML
    private Text date;

    @FXML
    void initialize(){
        assert addPhoto != null : "fx:id=\"addPhoto\" was not injected: Check your FXML file";
        assert deletePhoto != null : "fx:id=\"deletePhoto\" was not injected: Check your FXML file";
        assert addTag != null : "fx:id=\"addTag\" was not injected: Check your FXML file";
        assert addtoAnotherAlbum != null : "fx:id=\"addtoAnotherAlbum\" was not injected: Check your FXML file";
        assert deleteTag != null : "fx:id=\"deleteTag\" was not injected: Check your FXML file";
        assert movePhoto != null : "fx:id=\"movePhoto\" was not injected: Check your FXML file";
        assert imageViewer != null : "fx:id=\"imageViewer\" was not injected: Check your FXML file";
        assert Name != null : "fx:id=\"Name\" was not injected: Check your FXML file";
        assert imageGrid != null : "fx:id=\"imageGrid\" was not injected: Check your FXML file";
        assert caption != null : "fx:id=\"caption\" was not injected: Check your FXML file";
        assert date != null : "fx:id=\"date\" was not injected: Check your FXML file";
        assert rename != null : "fx:id=\"rename\" was not injected: Check your FXML file";
        assert ReCaption != null : "fx:id=\"ReCaption\" was not injected: Check your FXML file";
        assert textfield != null : "fx:id=\"textField\" was not injected: Check your FXML file";
        assert logout != null : "fx:id=\"logout\" was not injected: Check your FXML file";
        assert back != null : "fx:id=\"back\" was not injected: Check your FXML file";
        assert name != null : "fx:id=\"name\" was not injected: Check your FXML file";
        assert openPhoto != null : "fx:id=\"openPhoto\" was not injected: Check your FXML file";
        Name.setText(Current.session.getCurrentAlbum().getName());
        if (Current.session.getCurrentPhoto() != null) {
            caption.setText(Current.session.getCurrentPhoto().getCaption());
            date.setText(Current.session.getCurrentPhoto().getDate());
            imageViewer.setImage(Current.session.getCurrentPhoto().getImage());
        }
        updateGrid();
    }

    @FXML
    private void logout(){
        Current.session.logout();
        Common.newScene("Login", (Stage) logout.getScene().getWindow());
    }

    @FXML
    private void back(){
        Current.session.setCurrentPhoto(null);
        Current.session.setCurrentAlbum(null);
        Common.newScene("User", (Stage) back.getScene().getWindow());
    }

    @FXML
    private void setReCaption(){
        if (Current.session.getCurrentPhoto() == null) return;
        Current.session.getCurrentPhoto().setCaption(textfield.getText());
        try {
            UserData.writeUserData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void renamePhoto(){
        if (Current.session.getCurrentPhoto() == null || textfield.getText().isEmpty()) return;
        Photo photo = Current.session.getCurrentPhoto();
        new File(photo.getPath()).renameTo(new File(photo.getPath().substring(0, photo.getPath().lastIndexOf('\\') + 1) + textfield.getText() + photo.getPath().substring(photo.getPath().lastIndexOf('.'))));
        photo.setPath(photo.getPath().substring(0, photo.getPath().lastIndexOf('\\') + 1) + textfield.getText() + photo.getPath().substring(photo.getPath().lastIndexOf('.')));
        try {
            UserData.writeUserData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateGrid(){
        Album album = Current.session.getCurrentAlbum();
        if (album == null || album.getPhotos().isEmpty()) return;
        double height;
        double width;
        if (album.getPhotos().size() == 1){
            height = imageGrid.getPrefHeight();
            width = imageGrid.getPrefWidth();
        }else {
            height = (imageGrid.getPrefWidth() / ((double) album.getPhotos().size() / 2));
            width = imageGrid.getPrefWidth() / ((double) album.getPhotos().size() / 2);
        }

        imageGrid.setPrefTileHeight(height);
        imageGrid.setPrefTileWidth(width);

        for (Photo photo : album.getPhotos()) {
            if (photo == Current.session.getCurrentPhoto()) continue;
            ImageView imageView = new ImageView(photo.getImage());
            imageView.setFitWidth(width);
            imageView.setFitHeight(height);
            imageView.setPreserveRatio(true);
            imageView.setOnMouseClicked(this::clickImage);
            imageGrid.getChildren().add(imageView);
        }
    }

    private void clickImage(MouseEvent event){
        Image image = ((ImageView) event.getSource()).getImage();
        for (Photo photo : Current.session.getCurrentAlbum().getPhotos()) {
            if (photo.getImage().getUrl().equals(image.getUrl())) {
                imageViewer.setImage(image);
                caption.setText(photo.getCaption());
                date.setText(photo.updateDate());
                name.setText(photo.getPath().substring(photo.getPath().lastIndexOf('\\') + 1, photo.getPath().lastIndexOf('.')));
                Current.session.setCurrentPhoto(photo);
                return;
            }
        }
    }

    @FXML
    private void addPhoto(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Picture File");
        Stage stage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile == null) {
            new Alert(Alert.AlertType.ERROR, "No file selected");
            return;
        }

        switch (selectedFile.getName().substring(selectedFile.getName().lastIndexOf('.'))) {
            case ".jpg":
            case ".png":
            case ".gif":
            case ".bmp":
            case ".jpeg":
                break;
            default:
                new Alert(Alert.AlertType.ERROR, "Invalid file type");
                return;
        }
        Photo photo = new Photo(selectedFile.getAbsolutePath());
        Current.session.getCurrentAlbum().addPhoto(new Photo(selectedFile.getAbsolutePath()));
        TextInputDialog td = new TextInputDialog("Enter a caption for the photo");
        td.setHeaderText("Caption Prompt");
        Button okButton = (Button) td.getDialogPane().lookupButton(ButtonType.OK);
        okButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            String text = td.getEditor().getText();
            photo.setCaption(text);
            caption.setText(text);
            updateGrid();
        });
        td.show();
        try {
            UserData.writeUserData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateGrid();
    }

    @FXML
    private void deletePhoto(){
        if (Current.session.getCurrentPhoto() == null) return;
        Current.session.getCurrentAlbum().removePhoto(Current.session.getCurrentPhoto());
        Current.session.setCurrentPhoto(null);
        imageViewer.setImage(null);
        caption.setText("");
        updateGrid();
        try {
            UserData.writeUserData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addTag(){
        TextInputDialog td = new TextInputDialog("Enter a tag");
        td.setHeaderText("Tag Prompt");
        Button okButton = (Button) td.getDialogPane().lookupButton(ButtonType.OK);
        okButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            String text = td.getEditor().getText();
            if (text == null || text.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "No tag entered");
                return;
            }
            /*
            Current.session.getCurrentPhoto().addTag(text);
            textField.setText(text);
            try {
                UserData.writeUserData();
            } catch (Exception e) {
                e.printStackTrace();
            }
             */
        });
        td.show();
    }

    @FXML
    private void addtoAnotherAlbum(){
        if (Current.session.getCurrentUser().getAlbums().size() == 1) {
            new Alert(Alert.AlertType.ERROR, "No other albums to add to").show();
            return;
        }
        TextInputDialog td = new TextInputDialog("Enter the name of the album you would like to add this Photo to");
        td.setHeaderText("Album Name Prompt");
        Button okButton = (Button) td.getDialogPane().lookupButton(ButtonType.OK);
        okButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            String text = td.getEditor().getText();
            if (text == null || text.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "No album name entered");
                return;
            }

            for (Album album : Current.session.getCurrentUser().getAlbums())
                if (album.getName().equals(text)) {
                    album.addPhoto(Current.session.getCurrentPhoto());
                    try {
                        UserData.writeUserData();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return;
                }
        });
        td.show();
    }

    @FXML
    private void deleteTag(){

    }

    @FXML
    private void movePhoto(){
        if (Current.session.getCurrentUser().getAlbums().size() == 1) {
            new Alert(Alert.AlertType.ERROR, "No other albums to move to").show();
            return;
        }
        TextInputDialog td = new TextInputDialog("Enter the name of the album you would like to move this Photo to");
        td.setHeaderText("Album Name Prompt");
        Button okButton = (Button) td.getDialogPane().lookupButton(ButtonType.OK);
        okButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            String text = td.getEditor().getText();
            if (text == null || text.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "No album name entered");
                return;
            }
            for (Album album : Current.session.getCurrentUser().getAlbums())
                if (album.getName().equals(text)) {
                    album.addPhoto(Current.session.getCurrentPhoto());
                    Current.session.getCurrentAlbum().removePhoto(Current.session.getCurrentPhoto());
                    try {
                        UserData.writeUserData();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    updateGrid();
                    return;
                }
        });
        td.show();
    }

    @FXML
    private void launchFull() {
        if (Current.session.getCurrentPhoto() == null) return;
        Common.newScene("Full", (Stage) openPhoto.getScene().getWindow());
    }

}
