package photos.ui.controller;

import java.util.ArrayList;

import photos.constructors.Photo;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import photos.scession.Current;
import photos.ui.Common;

/**
 * Controller for the AllPhotosView.fxml file
 * @author Joseph T. McQuigg
 * @author Milton Zarzuela
 */
public class AllPhotosViewController {
    
    @FXML
    private TextField searchBox;

    @FXML
    private Button enter;

    @FXML
    private TilePane tilePane;

    @FXML
	void initialize(){
		assert searchBox != null : "fx:id=\"searchBox\" was not injected: Check your FXML file";
		assert enter != null : "fx:id=\"enter\" was not injected: Check your FXML file";
        assert tilePane != null : "fx:id=\"tilePane\" was not injected: Check your FXML file";

    }


    @FXML 
    private void EnterClicked(){
        boolean OR = false;
        boolean AND = false;
        String key1 = null;
        String key2 = null;
        String value1 = null;
        String value2 = null;

        if (searchBox.getText().isEmpty() || searchBox.getText().isBlank()){
            new Alert(Alert.AlertType.ERROR, "Please Enter a Search Parameter").show();
			return;
		}
        String searchParams = searchBox.getText();

        int tokenStart = 0;
        int tokenEnd = 0;
        for (int i = 0; i < searchParams.length(); i++){
            if (searchParams.charAt(i) == '='){
                tokenEnd = i;
                if (key1 == null) {
                    key1 = searchParams.substring(tokenStart, tokenEnd);
                }
                else{
                    key2 = searchParams.substring(tokenStart, tokenEnd);
                }
                tokenStart = i + 1;
            }
            if (i == searchParams.length() - 1){
                if (value1 == null){
                    value1 = searchParams.substring(tokenStart);
                }
                else {
                    value2 = searchParams.substring(tokenStart);
                }
            }
            if(searchParams.charAt(i) == 'O' && searchParams.charAt(i + 1) == 'R' ){
                OR = true;
                value1 = searchParams.substring(tokenStart, i - 1);
                tokenStart = i + 3;
            }
            if(searchParams.charAt(i) == 'A' && searchParams.charAt(i + 1) == 'N' && searchParams.charAt(i + 2) == 'D'){
                AND = true;
                value1 = searchParams.substring(tokenStart, i - 1);
                tokenStart = i + 4;
            }
        }
        if (key1 == null || value1 == null) {
            return;
        }

        ArrayList<Photo> photos = new ArrayList<>();

        for (int i = 0; i < Current.session.getCurrentUser().getAlbums().size(); i++){
            for (int j = 0; j < Current.session.getCurrentUser().getAlbums().get(i).getPhotos().size(); i++){
                if(Current.session.getCurrentUser().getAlbums().get(i).getPhotos().get(j).getTags().containsKey(key1)){
                    if (OR){
                        for (Photo photo: Current.session.getCurrentUser().getAlbums().get(i).getPhotos()){
                            if (photo.getTags().get(key1).contains(value1) || photo.getTags().get(key2).contains(value2) ){
                                photos.add(photo);
                            }
                        }
                    }
                    if (AND){
                        for (Photo photo: Current.session.getCurrentUser().getAlbums().get(i).getPhotos()){
                            if (photo.getTags().get(key1).contains(value1) && photo.getTags().get(key2).contains(value2) ){
                                photos.add(photo);
                            }
                        }
                    }
                    for (Photo photo: Current.session.getCurrentUser().getAlbums().get(i).getPhotos()){
                        if (photo.getTags().get(key1).contains(value1)){
                            photos.add(photo);
                        }
                    }
                }
            }
        }
    }

    @FXML
    private void BackClicked(){
		Common.newScene("User", (Stage) enter.getScene().getWindow());
    }
}
