package com.chatui.frontendjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class Home extends Registration{

    @FXML
    private ImageView exit;
    @FXML
    private Pane parentContainer1;
    @FXML
    private BorderPane childContainer;
    @FXML
    private GridPane emojis;
    @FXML
    private ImageView emojisButton;
    @FXML
    private TextField messageBar;
    @FXML
    private ImageView emo00, emo01, emo02, emo03, emo04, emo05, emo10, emo11, emo12, emo13, emo14, emo15, emo20, emo21, emo22, emo23, emo24, emo25, emo30, emo31, emo32, emo33, emo34, emo35;

    private boolean isEmojiMenuShown = false;

    public void Exit(MouseEvent event) {
            goTo(event, SIGN_IN_PATH, this.parentContainer1, this.childContainer, this.exit);
    }

    public void ShowEmojis(MouseEvent event) {
        if(!isEmojiMenuShown) {
            emojis.setStyle(emojis.getStyle() + " visibility: visible; ");
            isEmojiMenuShown = true;
        }
        else{
            emojis.setStyle(emojis.getStyle() + " visibility: hidden; ");
            isEmojiMenuShown = false;
        }
    }
    public void emojiHandler(MouseEvent event) {
        if(emo00.equals(event.getSource())) {
            messageBar.setText(messageBar.getText()+ "\uD83D\uDE0A");
        }
        if(emo01.equals(event.getSource()))
            messageBar.setText(messageBar.getText()+ "\uD83D\uDE01");
        if(emo02.equals(event.getSource()))
            messageBar.setText(messageBar.getText()+ "\uD83D\uDE06");
        if(emo03.equals(event.getSource()))
            messageBar.setText(messageBar.getText()+ "\uD83E\uDD23");
        if(emo04.equals(event.getSource()))
            messageBar.setText(messageBar.getText()+ "\uD83D\uDE0E");
        if(emo05.equals(event.getSource()))
            messageBar.setText(messageBar.getText()+ "\uD83E\uDD21");
        if(emo10.equals(event.getSource()))
            messageBar.setText(messageBar.getText()+ "\uD83D\uDE09");
        if(emo11.equals(event.getSource()))
            messageBar.setText(messageBar.getText()+ "\uD83E\uDD2A");
        if(emo12.equals(event.getSource()))
            messageBar.setText(messageBar.getText()+ "\uD83E\uDD17");
        if(emo13.equals(event.getSource()))
            messageBar.setText(messageBar.getText()+ "\uD83D\uDE36");
        if(emo14.equals(event.getSource()))
            messageBar.setText(messageBar.getText()+ "\uD83E\uDD7A");
        if(emo15.equals(event.getSource()))
            messageBar.setText(messageBar.getText()+ "\uD83D\uDE48");
        if(emo20.equals(event.getSource()))
            messageBar.setText(messageBar.getText()+ "\uD83E\uDD2B");
        if(emo21.equals(event.getSource()))
            messageBar.setText(messageBar.getText()+ "\uD83D\uDE11");
        if(emo22.equals(event.getSource()))
            messageBar.setText(messageBar.getText()+ "\uD83D\uDE37");
        if(emo23.equals(event.getSource()))
            messageBar.setText(messageBar.getText()+ "\uD83E\uDD25");
        if(emo24.equals(event.getSource()))
            messageBar.setText(messageBar.getText()+ "\uD83E\uDDD0");
        if(emo25.equals(event.getSource()))
            messageBar.setText(messageBar.getText()+ "\uD83D\uDE40");
        if(emo30.equals(event.getSource()))
            messageBar.setText(messageBar.getText()+ "\uD83D\uDE34");
        if(emo31.equals(event.getSource()))
            messageBar.setText(messageBar.getText()+ "\uD83E\uDD12");
        if(emo32.equals(event.getSource()))
            messageBar.setText(messageBar.getText()+ "\uD83E\uDD20");
        if(emo33.equals(event.getSource()))
            messageBar.setText(messageBar.getText()+ "\uD83E\uDD73");
        if(emo34.equals(event.getSource()))
            messageBar.setText(messageBar.getText()+ "\uD83D\uDC7A");
        if(emo35.equals(event.getSource()))
            messageBar.setText(messageBar.getText()+ "\uD83D\uDCA3");
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
        // if you want to make any request (except for signin && registration) do this:
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("some_url"))
//                .header("Content-Type", "application/json")
//                .header("Authorization", UserToken.token) !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//                .build();
    }
}
