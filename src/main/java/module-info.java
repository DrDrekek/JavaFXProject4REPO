module com.example.javafxproject4 {
   requires javafx.controls;
   requires javafx.fxml;


   opens com.example.javafxproject4 to javafx.fxml;
   exports com.example.javafxproject4;
}