package com.example.javafxproject4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class searchController implements Initializable {
   ObservableList<String> obsName = FXCollections.observableArrayList();
   ObservableList<String> obsMobile = FXCollections.observableArrayList();
   ArrayList<String> fullInfo = new ArrayList<>();
   @FXML
   private TextField name;
   @FXML
   private Label labelFirst,labelMobile,enterName;
   @FXML
   private Button searchBtn2;
   @FXML
   private Button backBtn;

   public void getInfo(ObservableList<String> names, ObservableList<String> nums, ArrayList<String> fullinf) {
      fullInfo = fullinf;
      obsName = names;
      obsMobile = nums;
   }

   @FXML
   private void search(ActionEvent event) {
      int c = 0;
      for (int i = 0; i < obsName.size(); i++) {
         if (name.getText().equals(obsName.get(i))) {
            labelFirst.setText("Name : \t"+obsName.get(i));
            labelMobile.setText("Mobile number  : \t"+obsMobile.get(i));
         }
         else {
            c++;
         }
      }
      if(c==obsName.size()){
         Alert alert = new Alert(Alert.AlertType.ERROR);
         displayAlertNContactNotFound();
      }
   }

   @FXML
   public void backBtnHandle(ActionEvent actionEvent)throws IOException {
      ((Node) actionEvent.getSource()).getScene().getWindow().hide();
      FXMLLoader load=new FXMLLoader(getClass().getResource("addressBook.fxml"));
      Scene scene=new Scene(load.load());
      AddressBookController secondScene= load.getController();
      secondScene.getInfo(obsName,obsMobile,fullInfo);
      Stage stage= new Stage();
      stage.setTitle("Address Book");
      stage.setScene(scene);
      stage.show();
   }

   @Override
   public void initialize(URL url, ResourceBundle resourceBundle) {

   }

   public void displayAlertNContactNotFound(){
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("ERROR");
      alert.getDialogPane().setHeaderText("Error!");
      alert.setContentText("Contact does not exist.");
      alert.showAndWait();
   }
}
