package com.javanc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomeUser.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cardProductNew.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
//        scene.getStylesheets().add("file:/D:/btljvnc/FruitMarket/src/main/java/com/javanc/values/mainFormDesign.css");
//       scene.getStylesheets().add("file:/D:/btljvnc/FruitMarket/src/main/java/com/javanc/values/loginDesign.css");
//       scene.getStylesheets().add("file:/D:/btljvnc/FruitMarket/src/main/java/com/javanc/values/homeUser.css");
//       scene.getStylesheets().add("file:/D:/btljvnc/FruitMarket/src/main/java/com/javanc/values/cardProductNew.css");
//       scene.getStylesheets().add("file:/D:/btljvnc/FruitMarket/src/main/java/com/javanc/values/cardProduct.css");

        scene.getStylesheets().add("file:/D:/btljvnc/FruitMarket/src/main/java/com/javanc/values/mainFormDesign.css");
        scene.getStylesheets().add("file:/D:/btljvnc/FruitMarket/src/main/java/com/javanc/values/loginDesign.css");
        scene.getStylesheets().add("file:/D:/btljvnc/FruitMarket/src/main/java/com/javanc/values/homeUser.css");
        scene.getStylesheets().add("file:/D:/btljvnc/FruitMarket/src/main/java/com/javanc/values/cardProductNew.css");
        scene.getStylesheets().add("file:/D:/btljvnc/FruitMarket/src/main/java/com/javanc/values/cardProduct.css");

        stage.setTitle("BookStore Management System");
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}