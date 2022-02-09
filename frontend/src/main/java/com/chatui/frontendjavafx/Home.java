package com.chatui.frontendjavafx;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.eu.mivrenik.stomp.client.StompClient;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;
import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import org.java_websocket.drafts.Draft_6455;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Home extends Registration implements Initializable {

    public static StringProperty message;

    @FXML
    private ImageView exit;
    @FXML
    private Pane parentContainer1;
    @FXML
    private BorderPane childContainer1;
    @FXML
    private BorderPane profileUser;
    @FXML
    private BorderPane notificationUser;
    @FXML
    private BorderPane addFriendUser;
    @FXML
    private ImageView profile;
    @FXML
    private VBox discussionsView = new VBox();
    @FXML
    private ImageView conversation;
    @FXML

    private ImageView settingsButton;
    @FXML
    private ImageView notification;
    @FXML
    private ImageView notifCircle;

    @FXML
    private VBox chat;
    @FXML
    private GridPane emojis;
    @FXML
    private ImageView emojisButton, next, previous, searchButton;
    @FXML
    private TextField messageBar, searchField;
    @FXML
    private ListView chatPage, notificationList, addFriendList;
    @FXML
    private Label nameInChat;
    @FXML
    private ListView disscussionPage = new ListView();
    @FXML
    private ImageView emo00, emo01, emo02, emo03, emo04, emo05, emo10, emo11, emo12, emo13, emo14, emo15, emo20, emo21, emo22, emo23, emo24, emo25, emo30, emo31, emo32, emo33, emo34, emo35;
    @FXML
    private Label nameProfile, emailProfile;
    @FXML
    private ScrollPane scrollPage;
    @FXML
    private Node last_msg_node;
    public static List<User> friends;
    public static List<User> allUsers;
    private boolean isEmojiMenuShown = false;
    private String receiverId;

    public Home(){
        message = new SimpleStringProperty("");
        message.addListener(new StringChangeListener());
    }

    class StringChangeListener implements ChangeListener<String>
    {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            handleUiChange(newValue);
        }
    }
    public void handleUiChange(String upComingMessage){
        System.out.println("upCommingMessage Json is: "+upComingMessage);
    }
    public void Exit(MouseEvent event) throws IOException, InterruptedException {
        goTo(event, SIGN_IN_PATH, this.parentContainer1, this.childContainer1, this.exit);
        User user = SignIn.postUser.getSession();
        HttpClient client = HttpClient.newHttpClient();
        Gson gson = new Gson();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/v1/users"))
                .header("Content-Type", "application/json")
                .header("Authorization", UserToken.token)
                .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(user)))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    public String getNext(String uid) {
        int idx = friends.indexOf(uid);
        if (idx < 0 || idx+1 > friends.size()) return friends.get(0).getName();
        if(idx == friends.size()) return friends.get(friends.size()).getName();
        return friends.get(idx + 1).getName();
    }
    public String getPrevious(String uid) {
        int idx = friends.indexOf(uid);
        if (idx <= 0) return friends.get(0).getName();
        return friends.get(idx - 1).getName();
    }
    public void navigationChat(MouseEvent event){
        if(event.getSource() == next){
            nameInChat.setText(getNext(nameInChat.getText()));
        }
        if(event.getSource() == previous){
            nameInChat.setText(getPrevious(nameInChat.getText()));
        }
        disscussionPage.getSelectionModel().select(friends.indexOf(nameInChat.getText()));
    }
    public void Profile(MouseEvent event) {
        chat.setVisible(false);
        addFriendUser.setVisible(false);
        discussionsView.setVisible(false);
        profile.setBlendMode(BlendMode.valueOf("GREEN"));
        conversation.setBlendMode(BlendMode.valueOf("SRC_OVER"));
        settingsButton.setBlendMode(BlendMode.valueOf("SRC_OVER"));
        notification.setBlendMode(BlendMode.valueOf("SRC_OVER"));
        profileUser.setVisible(true);
        notificationUser.setVisible(false);
        User profile = SignIn.postUser.getSession();
        emailProfile.setText(profile.getEmail());
        nameProfile.setText(profile.getName());
    }
    public void Conversation(MouseEvent event) throws JsonProcessingException {
        addFriendUser.setVisible(false);
        chat.setVisible(true);
        discussionsView.setVisible(true);
        notificationUser.setVisible(false);
        conversation.setBlendMode(BlendMode.valueOf("GREEN"));
        profile.setBlendMode(BlendMode.valueOf("SRC_OVER"));
        settingsButton.setBlendMode(BlendMode.valueOf("SRC_OVER"));
        notification.setBlendMode(BlendMode.valueOf("SRC_OVER"));
        profileUser.setVisible(false);
        addDisscussions(null);
    }
    public void AddFriend(MouseEvent event) {
        discussionsView.setVisible(false);
        settingsButton.setBlendMode(BlendMode.valueOf("GREEN"));
        profile.setBlendMode(BlendMode.valueOf("SRC_OVER"));
        conversation.setBlendMode(BlendMode.valueOf("SRC_OVER"));
        notification.setBlendMode(BlendMode.valueOf("SRC_OVER"));
        notificationUser.setVisible(false);
        chat.setVisible(false);
        profileUser.setVisible(false);
        addFriendUser.setVisible(true);
        addfriends(null);
    }

    public void Notification(MouseEvent event){
        addFriendUser.setVisible(false);
        notificationUser.setVisible(true);
        notifCircle.setVisible(false);
        notification.setBlendMode(BlendMode.valueOf("GREEN"));
        conversation.setBlendMode(BlendMode.valueOf("SRC_OVER"));
        profile.setBlendMode(BlendMode.valueOf("SRC_OVER"));
        chat.setVisible(false);
        discussionsView.setVisible(false);
        profileUser.setVisible(false);
        addNotifications(null);
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
    public void searchHandler(MouseEvent event){

    }
    public void receiveHandler(MouseEvent event) throws IOException {
        addToChat(event, "receive");
    }
    public void addDisscussions(MouseEvent event) throws JsonProcessingException {
        friends = new ArrayList<>();
        User user = SignIn.postUser.getSession();
        if(user.getUserID() != null){
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/v1/users" + "/" + user.getUserID() + "/friends"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", UserToken.token)
                    .GET()
                    .build();
            HttpResponse<String> response = null;
            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (Exception e) {
                e.getMessage(); return;
            }
            System.out.println(response.body());
            User[] users = new ObjectMapper().readValue(response.body(), User[].class);

            for (User singleUser: users){
                friends.add(singleUser);
                SignIn.postUser.getSession().getFriends().add(singleUser);
            }

            if(friends.size() > 0){
                nameInChat.setText(friends.get(0).getName());
                for(User friend: friends)
                    addDisscussion(event, friend);
            }

        }

    }
    public void addNotifications(MouseEvent event){
        friends = new ArrayList<>();
        User user = SignIn.postUser.getSession();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Registration.FRIENDS_URL + "/" + user.getUserID()))
                .header("Content-Type", "application/json")
                .header("Authorization", UserToken.token)
                .GET()
                .build();


        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            User[] users = new ObjectMapper().readValue(response.body(), User[].class);

            for (User singleUser: users){
                    friends.add(singleUser);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        for(User friend: friends)
            addNotification(event, friend);
    }
    public void addNotification(MouseEvent event, User friend){
        HBox container = new HBox();
        VBox container2 = new VBox();
        Label name = new Label(friend.getName());
        container2.setAlignment(Pos.CENTER_LEFT);
        container2.setPrefWidth(277.0);
        container2.setPrefHeight(100.0);
        name.setPrefWidth(188.0);
        name.setPrefHeight(17.0);
        name.setStyle("-fx-font: Ebrima Bold; -fx-font-size: 30.0; -fx-text-fill: #f7fafa");
        container2.getChildren().add(name);
        ImageView image = new ImageView();
        InputStream input = getClass().getResourceAsStream("/com/chatui/frontendjavafx/icon/user.png");
        ImageView accept = new ImageView();
        InputStream inputAccept = getClass().getResourceAsStream("/com/chatui/frontendjavafx/icon/accept.png");
        ImageView delete = new ImageView();
        InputStream inputDelete = getClass().getResourceAsStream("/com/chatui/frontendjavafx/icon/delete.png");
        image.setImage(new Image(input));
        accept.setImage(new Image(inputAccept));
        delete.setImage(new Image(inputDelete));
        image.setFitHeight(90.0);
        image.setFitWidth(90.0);
        accept.setFitHeight(50.0);
        accept.setFitWidth(50.0);
        delete.setFitHeight(50.0);
        delete.setFitWidth(50.0);
        container.setAlignment(Pos.CENTER);
        container.setId("chatSelected");
        container.getChildren().addAll(image, container2, accept, delete);
        notificationList.fixedCellSizeProperty();
        notificationList.getItems().add(container);

        container2.setId(friend.getUserID());
        //Accept request
        EventHandler<MouseEvent> selectHandlerAccept = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                User user = SignIn.postUser.getSession();
                Invitation invitation = new Invitation(container2.getId(),user.getUserID());
                HttpClient client = HttpClient.newHttpClient();
                Gson gson = new Gson();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(Registration.FRIENDS_URL))
                        .header("Content-Type", "application/json")
                        .header("Authorization", UserToken.token)
                        .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(invitation)))
                        .build();
                try {
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    System.out.println(response);
                    container.setVisible(false);
                } catch (Exception e) {
                    e.getMessage(); return;
                }
            }
        };
        accept.addEventFilter(MouseEvent.MOUSE_CLICKED, selectHandlerAccept);
        //Delete request
        EventHandler<MouseEvent> selectHandlerDelete = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                User user = SignIn.postUser.getSession();
                Invitation invitation = new Invitation( container2.getId(),user.getUserID());
                HttpClient client = HttpClient.newHttpClient();
                Gson gson = new Gson();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(Registration.FRIENDS_URL + "/delete"))
                        .header("Content-Type", "application/json")
                        .header("Authorization", UserToken.token)
                        .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(invitation)))
                        .build();
                HttpResponse<String> response = null;
                try {
                    response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    container.setVisible(false);
                } catch (Exception e) {
                    e.getMessage(); return;
                }
            }
        };
        delete.addEventFilter(MouseEvent.MOUSE_CLICKED, selectHandlerDelete);
    }
    public void addfriends(MouseEvent event){
        friends = new ArrayList<>();
        allUsers = new ArrayList<>();
        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/v1/users/all"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", UserToken.token)
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            User[] users = new ObjectMapper().readValue(response.body(), User[].class);


            for (User singleUser: users){

                if(!singleUser.getUserID().equals(SignIn.postUser.getSession().getUserID()))
                    allUsers.add(singleUser);
            }

            for(User user: allUsers){
                addfriend(event, user);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void addfriend(MouseEvent event, User user){
        HBox container = new HBox();
        VBox container2 = new VBox();
        Label name = new Label(user.getName());
        container2.setAlignment(Pos.CENTER_LEFT);
        container2.setPrefWidth(277.0);
        container2.setPrefHeight(100.0);
        name.setPrefWidth(188.0);
        name.setPrefHeight(17.0);
        name.setStyle("-fx-font: Ebrima Bold; -fx-font-size: 30.0; -fx-text-fill: #f7fafa");
        container2.getChildren().add(name);
        ImageView image = new ImageView();
        InputStream input = getClass().getResourceAsStream("/com/chatui/frontendjavafx/icon/user.png");
        ImageView add = new ImageView();
        InputStream inputAdd = getClass().getResourceAsStream("/com/chatui/frontendjavafx/icon/add.png");
        image.setImage(new Image(input));
        add.setImage(new Image(inputAdd));
        image.setFitHeight(90.0);
        image.setFitWidth(90.0);
        add.setFitHeight(50.0);
        add.setFitWidth(50.0);
        container.setAlignment(Pos.CENTER);
        container.setId("chatSelected");
        container.getChildren().addAll(image, container2, add);
        addFriendList.fixedCellSizeProperty();
        addFriendList.getItems().add(container);

        container2.setId(user.getUserID());
        //add Image Click
        EventHandler<MouseEvent> selectHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                User user = SignIn.postUser.getSession();

                Invitation invitation = new Invitation(user.getUserID(), container2.getId());
                HttpClient client = HttpClient.newHttpClient();
                Gson gson = new Gson();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(Registration.FRIENDS_URL))
                        .header("Content-Type", "application/json")
                        .header("Authorization", UserToken.token)
                        .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(invitation)))
                        .build();
                HttpResponse<String> response = null;
                try {
                    response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    container.setVisible(false);
                } catch (Exception e) {
                    e.getMessage();
                    return;
                }
            }
        };
        add.addEventFilter(MouseEvent.MOUSE_CLICKED, selectHandler);
    }
    public void addDisscussion(MouseEvent event, User friend){
        HBox container = new HBox();
        VBox container2 = new VBox();
        Label name = new Label(friend.getName());
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
        container.setId("chatSelected");
        container.getChildren().addAll(image, container2);
        disscussionPage.fixedCellSizeProperty();
        disscussionPage.getItems().add(container);

        container2.setId(friend.getUserID());
        //clickEvent
        EventHandler<MouseEvent> selectHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                receiverId = container2.getId();
                nameInChat.setText(name.getText());
            }
        };
        container.addEventFilter(MouseEvent.MOUSE_CLICKED, selectHandler);
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
                    // USE receiverId attribute
                    send(receiverId,messageBar.getText());
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
    public static void send(String idReciever, String message) {
        // Sending JSON message to a server


        String messageJson = "{\"fromLogin\":\""+SignIn.postUser.getSession().getUserID()+"\", \"message\":\""+ message +"\"}";
        System.out.println(idReciever+"\nmessage is:"+messageJson);
        PostUserHandler.stompSocket.send("/app/chat/"+idReciever, messageJson);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        friends = new ArrayList<>();
        allUsers = new ArrayList<>();
        //User profile = SignIn.postUser.getSession();

        //addNotifications(null);
        //addfriends(null);
        try {
            addDisscussions(null);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }
}
