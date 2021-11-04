package com.example.javafxproject4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AddressBookApplication extends Application {
   @Override
   public void start(Stage stage) throws IOException {
      FXMLLoader fxmlLoader = new FXMLLoader(AddressBookApplication.class.getResource("addressBook.fxml"));
      Scene scene = new Scene(fxmlLoader.load(), 800, 480);
      stage.setTitle("AddressBook");
      stage.setScene(scene);
      stage.show();
   }

   public static void main(String[] args) {
      launch();
   }
}
