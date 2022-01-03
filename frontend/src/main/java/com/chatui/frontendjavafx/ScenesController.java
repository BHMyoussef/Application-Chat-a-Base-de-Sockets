package com.chatui.frontendjavafx;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class ScenesController {
//    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchScene(ActionEvent event, String fileName, Pane parentContainer, BorderPane childContainer, Button button1) {
        try {
            root = FXMLLoader.load(getClass().getResource(fileName));
            scene= button1.getScene();
            root.translateXProperty().set(scene.getWidth());
            parentContainer.getChildren().add(root);
            Timeline timeline = new Timeline();
            KeyValue keyValue = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
            timeline.getKeyFrames().add(keyFrame);
            timeline.setOnFinished(event1 -> {
                parentContainer.getChildren().remove(childContainer);
            });
            timeline.play();

        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
