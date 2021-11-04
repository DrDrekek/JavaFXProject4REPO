package com.example.javafxproject4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddressBookController implements Initializable {
   ObservableList<String> obsName= FXCollections.observableArrayList();
   ObservableList<Integer> obsMobile = FXCollections.observableArrayList() ;
   ArrayList<String> fullInfo = new ArrayList<>();
   @FXML
   private Button addBtn;
   @FXML
   private Button deleteBtn;
   @FXML
   private Button searchBtn;
   @FXML
   private TextField firstNameText;
   @FXML
   private TextField secondNameText;
   @FXML
   private TextField mobileNumberText;
   @FXML
   ListView<String> textAreaNames = new ListView <>(obsName);
   @FXML
   ListView<Integer> textAreaNumbers = new ListView <>(obsMobile);

   public void getInfo(ObservableList<String> names,ObservableList<Integer> nums){
      obsName=names;
      obsMobile=nums;
      textAreaNames.setItems(names);
      textAreaNumbers.setItems(nums);
   }

   public boolean checkName(String goalName) {
      String name = "";
      String[] s;
      for (String value : fullInfo) {
         s = value.split(" ");
         name = s[0] + " " + s[1];
         if (goalName.equalsIgnoreCase(name)) {
            return true;
         }
      }
      return false;
   }

   public boolean checkNumber(String goalNumber) {
      String mobileNumber = "";
      String[] s;
      for (String value : fullInfo) {
         s = value.split(" ");
         mobileNumber = s[2];
         if (goalNumber.equalsIgnoreCase(mobileNumber)) {
            return true;
         }
      }
      return false;
   }

   public boolean checkName(String goalName,int index) {
      String name = "";
      String[] s;
      s = fullInfo.get(index).split(" ");
      name = s[0] + " " + s[1];
      return goalName.equalsIgnoreCase(name);
   }

   public boolean checkNumber(String goalNumber,int index) {
      String mobileNumber = "";
      String[] s;
      s = fullInfo.get(index).split(" ");
      mobileNumber = s[2];
      return goalNumber.equalsIgnoreCase(mobileNumber);
   }


   @FXML
   private void addEntry(ActionEvent e) {
      if (!firstNameText.getText().trim().equals("") && !secondNameText.getText().trim().equals("") &&
              !mobileNumberText.getText().trim().equals("") && mobileNumberText.getText().length() == 10) {
         if(!checkName(firstNameText.getText()+" "+ secondNameText.getText())){
            if(!checkNumber(mobileNumberText.getText())) {
               fullInfo.add(firstNameText.getText() + " " + secondNameText.getText() + " " + mobileNumberText.getText());
               obsName.add(firstNameText.getText() + " " + secondNameText.getText());
               obsMobile.add(Integer.valueOf(mobileNumberText.getText()));
               textAreaNumbers.setItems(obsMobile);
               textAreaNames.setItems(obsName);
            }
            else
            {
               displayAlertNumberDupe();
            }
         }
         else{
            displayAlertNumberDupe();
         }
      }
      handle(firstNameText.getText(), secondNameText.getText(), mobileNumberText.getText());
   }

   public void handle(String f,String s,String m){
      if(f.trim().equals("")){
         displayAlertFirstName();
      }
      else if(s.trim().equals("")){
         displayAlertSecondName();
      }
      else if(m.equals("")){
         displayAlertMobileNumberEmpty();
      }
      else if(m.length()!=10){
         displayAlertMobileNumberLength();
      }
   }

   public void deleteEntry(ActionEvent e) {
      if (!firstNameText.getText().trim().equals("") && !secondNameText.getText().trim().equals("") &&
              !mobileNumberText.getText().trim().equals("") && mobileNumberText.getText().length() == 10) {
         for (int i = 0; i <fullInfo.size() ; i++) {
            if(checkName(firstNameText.getText()+" "+ secondNameText.getText(),i)){
               if(checkNumber(mobileNumberText.getText(),i)) {
                  fullInfo.remove(i);
                  obsMobile.remove(i);
                  obsName.remove(i);
               }
               else {
                  displayAlertWrongNum();
               }
            }
         }
      }
      handle(firstNameText.getText(), secondNameText.getText(), mobileNumberText.getText());
   }


   @Override
   public void initialize(URL url, ResourceBundle resourceBundle) {

   }

   public void displayAlertNumberDupe(){
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("ERROR");
      alert.getDialogPane().setHeaderText("Error!");
      alert.setContentText("duplicated Number : ) ");
      alert.showAndWait();
   }
   public void displayAlertWrongNum(){
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("ERROR");
      alert.getDialogPane().setHeaderText("Error!");
      alert.setContentText("Wrong number. ");
      alert.showAndWait();
   }
   public void displayAlertFirstName(){
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("ERROR");
      alert.getDialogPane().setHeaderText("Error!");
      alert.setContentText("First Name Empty.");
      alert.showAndWait();
   }
   public void displayAlertSecondName(){
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("ERROR");
      alert.getDialogPane().setHeaderText("Error!");
      alert.setContentText("Second Name Empty.");
      alert.showAndWait();
   }
   public void displayAlertMobileNumberEmpty(){
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("ERROR");
      alert.getDialogPane().setHeaderText("Error!");
      alert.setContentText("Mobile Number Empty.");
      alert.showAndWait();
   }
   public void displayAlertMobileNumberLength(){
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("ERROR");
      alert.getDialogPane().setHeaderText("Error!");
      alert.setContentText("Number must be 10 digits.");
      alert.showAndWait();
   }

   public void scene2BtnHandle(ActionEvent actionEvent) throws IOException{
      ((Node) actionEvent.getSource()).getScene().getWindow().hide();
      FXMLLoader load = new FXMLLoader(getClass().getResource("search.fxml"));
      Scene scene = new Scene(load.load());
      searchController scene1 = load.getController();
      scene1.getInfo(obsName, obsMobile);
      Stage stage = new Stage();
      stage.setTitle("Search");
      stage.setScene(scene);
      stage.showAndWait();
   }
}
