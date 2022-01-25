package com.chatui.frontendjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Home extends Registration{

    @FXML
    private ImageView exit;
    @FXML
    private Pane parentContainer1;
    @FXML
    private BorderPane childContainer1;
    @FXML
    private BorderPane profileUser;
    @FXML
    private ImageView profile;
    @FXML
    private ImageView conversation;
    @FXML
    private VBox chat;
    @FXML
    private GridPane emojis;
    @FXML
    private ImageView emojisButton;
    @FXML
    private TextField messageBar;
    @FXML
    private ListView chatPage;
    @FXML
    private ListView disscussionPage;
    @FXML
    private ImageView emo00, emo01, emo02, emo03, emo04, emo05, emo10, emo11, emo12, emo13, emo14, emo15, emo20, emo21, emo22, emo23, emo24, emo25, emo30, emo31, emo32, emo33, emo34, emo35;
    @FXML
    private Label nameProfile, emailProfile;
    @FXML
    private ScrollPane scrollPage;

    private Node last_msg_node;

    private boolean isEmojiMenuShown = false;

    public void Exit(MouseEvent event) {
            goTo(event, SIGN_IN_PATH, this.parentContainer1, this.childContainer1, this.exit);
    }

    public void Profile(MouseEvent event) {
        chat.setVisible(false);
        profile.setBlendMode(BlendMode.valueOf("GREEN"));
        conversation.setBlendMode(BlendMode.valueOf("SRC_OVER"));
        profileUser.setVisible(true);
        User profile = SignIn.postUser.getSession();
        emailProfile.setText(profile.getEmail());
        nameProfile.setText(profile.getName());
    }
    public void Conversation(MouseEvent event) {
        chat.setVisible(true);
        conversation.setBlendMode(BlendMode.valueOf("GREEN"));
        profile.setBlendMode(BlendMode.valueOf("SRC_OVER"));
        profileUser.setVisible(false);
    }


    public static ArrayList<Node> getAllNodes(Parent root) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        addAllDescendents(root, nodes);
        return nodes;
    }
    private static void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
        for (Node node : parent.getChildrenUnmodifiable()) {
            nodes.add(node);
            if (node instanceof Parent)
                addAllDescendents((Parent)node, nodes);
        }
    }
    public void sendHandler(MouseEvent event) throws IOException {
        addToChat(event, "send");
    }
    public void receiveHandler(MouseEvent event) throws IOException {
        addToChat(event, "receive");
    }
    public void addDisscussion(MouseEvent event, String username){
        HBox container = new HBox();
        VBox container2 = new VBox();
        Label name = new Label(username);
        Label message = new Label("you: hello my friend...");
        container2.setAlignment(Pos.CENTER_LEFT);
        container2.setPrefWidth(277.0);
        container2.setPrefHeight(100.0);
        name.setPrefWidth(188.0);
        name.setPrefHeight(17.0);
        name.setStyle("-fx-font: Ebrima Bold; -fx-font-size: 26.0; -fx-text-fill: #f7fafa");
        message.setPrefWidth(228.0);
        message.setPrefHeight(30.0);
        message.setLayoutX(10.0);
        message.setLayoutY(10.0);
        message.setStyle("-fx-font: Ebrima; -fx-font-size: 20.0; -fx-text-fill: #14bca6");
        container2.getChildren().addAll(name,message);
        ImageView image = new ImageView();
        InputStream input = getClass().getResourceAsStream("/com/chatui/frontendjavafx/icon/user.png");
        image.setImage(new Image(input));
        image.setFitHeight(90.0);
        image.setFitWidth(90.0);
        container.setAlignment(Pos.BOTTOM_LEFT);
        container.getChildren().addAll(image, container2);
        disscussionPage.fixedCellSizeProperty();
        disscussionPage.getItems().add(container);
    }
    public void addToChat(MouseEvent event, String operation) throws IOException {
        if(!messageBar.getText().trim().isEmpty()) {
            Label msg = new Label(messageBar.getText().trim());
            msg.setWrapText(true);
            msg.setTextAlignment(TextAlignment.JUSTIFY);
            msg.setMaxWidth(350.0);
            ImageView image = new ImageView();
            msg.setStyle("-fx-background-radius: 20.0; -fx-font-family: Ebrima; -fx-font-size: 20.0; -fx-padding: 5.0 5.0 5.0 5.0;");
            image.setFitHeight(70.0);
            image.setFitWidth(70.0);
            HBox x = new HBox();
            x.setMaxWidth(chatPage.getWidth() - 20);
            switch (operation) {
                case "send":
                    InputStream input = getClass().getResourceAsStream("/com/chatui/frontendjavafx/icon/me.png");
                    image.setImage(new Image(input));
                    msg.setStyle(msg.getStyle()+"-fx-background-color: #50c984; -fx-text-fill: #fff");
                    x.setAlignment(Pos.BOTTOM_RIGHT);
                    x.getChildren().addAll(msg, image);
                    break;
                case "receive" :
                    input = getClass().getResourceAsStream("/com/chatui/frontendjavafx/icon/user.png");
                    image.setImage(new Image(input));
                    msg.setStyle(msg.getStyle()+"-fx-background-color: #fff; -fx-text-fill: #000");
                    x.setAlignment(Pos.BOTTOM_LEFT);
                    x.getChildren().addAll(image, msg);
                    break;
            }
            chatPage.setFocusTraversable(false);
            chatPage.getItems().add(x);
            messageBar.setText("");
        }
    }

    public void ShowEmojis(MouseEvent event) {
        if(!isEmojiMenuShown)
            isEmojiMenuShown = true;
        else
            isEmojiMenuShown = false;

        emojis.setVisible(isEmojiMenuShown);
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
