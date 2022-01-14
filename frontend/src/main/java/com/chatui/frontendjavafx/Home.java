package com.chatui.frontendjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.nio.Buffer;
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
    private  Pane chatPage;
    @FXML
    private ImageView emo00, emo01, emo02, emo03, emo04, emo05, emo10, emo11, emo12, emo13, emo14, emo15, emo20, emo21, emo22, emo23, emo24, emo25, emo30, emo31, emo32, emo33, emo34, emo35;
    @FXML
    private Label nameProfile, emailProfile;
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
    public void sendHandler(MouseEvent event) {
        if(!messageBar.getText().trim().isEmpty()) {
            Label msg = new Label(messageBar.getText().trim());
            msg.setWrapText(true);
            msg.setTextAlignment(TextAlignment.JUSTIFY);
            msg.setMaxWidth(350.0);
            msg.setMinWidth(messageBar.getLength());
            last_msg_node = this.getAllNodes(chatPage).get(this.getAllNodes(chatPage).size() - 3);
            ImageView image = new ImageView(new Image("file:\\C:\\Users\\Zakaria Dani\\Desktop\\appChat\\Application-Chat-a-Base-de-Sockets\\frontend\\src\\main\\resources\\com\\chatui\\frontendjavafx\\icon\\me.png"));
            msg.setStyle("-fx-background-color: #50c984; -fx-background-radius: 20.0; -fx-font-family: Ebrima; -fx-font-size: 20.0; -fx-text-fill: #fff; -fx-padding: 5.0 5.0 5.0 5.0;");
            chatPage.setMinHeight(chatPage.getHeight() + 500);
            System.out.println(msg.getMinWidth());
            if (msg.getMinWidth() <= 31) {
                msg.setLayoutX(700 - 11.3 * msg.getMinWidth());
                if (msg.getMinWidth() > 10)
                    msg.setLayoutX(700 - 11.0 * msg.getMinWidth());
            } else
                msg.setLayoutX(700 - msg.getMaxWidth());
            image.setLayoutY(last_msg_node.getLayoutY() + 108);
            System.out.println(last_msg_node.getLayoutY());
            msg.setLayoutY(last_msg_node.getLayoutY() + 135.0);
            image.setLayoutX(708.0);
            image.setFitHeight(70.0);
            image.setFitWidth(70.0);
            chatPage.getChildren().add(msg);
            chatPage.getChildren().add(image);
            messageBar.setText("");
            System.out.println(last_msg_node);
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
