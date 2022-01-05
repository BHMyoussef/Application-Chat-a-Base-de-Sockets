package com.chatui.frontendjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Home extends Registration{

    @FXML
    private ImageView exit;
    @FXML
    private Pane parentContainer1;
    @FXML
    private BorderPane childContainer;


    public void Exit(MouseEvent event) {
            goTo(event, SIGN_IN_PATH, this.parentContainer1, this.childContainer, this.exit);
    }

    @Override
    public void updateBoxes() {

    }

    @Override
    public void updatePasswordBoxes() {

    }

    @Override
    public Label getAttribute(String attribute) {
        return null;
    }

    @Override
    public void postToServer(ActionEvent event) {

    }
}
