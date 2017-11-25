/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakotwieniezbrojenia.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author emilz
 */
public class MainApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/zakotwieniezbrojenia/view/FormFXML.fxml"));

        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("/com/zakotwieniezbrojenia/images/icon.png"));
        stage.setScene(scene);
        stage.setTitle("Zakotwienie pręta");
        stage.show();  
        stage.setResizable(false);   
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
