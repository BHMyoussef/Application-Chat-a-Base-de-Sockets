package com.chatui.frontendjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScenesController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchScene(ActionEvent event, String fileName) {
        try {
            root = FXMLLoader.load(getClass().getResource(fileName));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene= new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(false); // this is a bug in javafx!
            stage.setMaximized(true);
            stage.show();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
