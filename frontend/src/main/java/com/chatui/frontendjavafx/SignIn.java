package com.chatui.frontendjavafx;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;
import java.io.IOException;


public class SignIn extends Registration{
    @FXML
    private TextField email;
    @FXML
    private Label emailErrorMessage;
    @FXML
    private PasswordField password;
    @FXML
    private Label passwordErrorMessage;
    @FXML
    private Label signInMessage;
    @FXML
    private Pane parentContainer1;
    @FXML
    private BorderPane childContainer;
    @FXML
    private Button button1;
    @FXML
    private Button button;

    private String token;
    public static PostUserHandler postUser;
    public void SignInHandler(ActionEvent event) throws IOException {
        registrationHandler(event, SIGN_UP_PATH);
        if(isSuccess){
            UserToken.getInstance(token);
            goTo(event, HOME_PATH, this.parentContainer1, this.childContainer, this.button);
        }
    }

    public void goSignUp(ActionEvent event){
        goTo(event, SIGN_UP_PATH, this.parentContainer1, this.childContainer, this.button1);
    }
    /*
    @Override
    public void authSuccess() {
        User user = new User();
        user.setEmail(email.getText());
        user.setPassword(password.getText());
        if(isUserValid) validateUserSuccess(user);
        String validMsg = "You have access";
        String errMsg = "Email or password invalid";
        showResponseMessage(signInMessage,validMsg, errMsg, getClass().getSimpleName());
    }
    */
    @Override
    public void postToServer(ActionEvent event)  {
        User user = new User();
        user.setEmail(email.getText());
        user.setPassword(password.getText());
        postUser = new PostUserHandler(user, SIGN_IN_URL);
        postUser.call();
        String res = postUser.getRes();
        this.token = postUser.getToken();
        showResponseMessage(signInMessage,res);
        System.out.println(res);
        System.out.println(postUser.getSession().getEmail());
        new Thread(postUser).start();
    }

    @Override
    public void updateBoxes(){
        if(!isEmptyBox(this.email)){
            updateEmailBox(this.email, this.emailErrorMessage);
        }else {
            updateEmptyBox(this.email);
        }
        if(!isEmptyBox(this.password)){
            updatePasswordBoxes();
        }else
            updateEmptyBox(this.password);
    }

    @Override
    public void updatePasswordBoxes(){
        changeBoxStyle(this.password, validBorder,false);
    }

    @Override
    public Label getAttribute(String attribute) {
        switch (attribute) {
            case "email": return this.emailErrorMessage;
            case "password": return this.passwordErrorMessage;
            default: throw new RuntimeException("Invalid attribute: " + attribute);
        }
    }
}
