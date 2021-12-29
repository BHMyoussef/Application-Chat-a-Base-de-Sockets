package com.chatui.frontendjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

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

    public void SignInHandler(ActionEvent event) throws IOException {
        registrationHandler(event, SIGN_UP_PATH);

    }

    public void goSignUp(ActionEvent event){
        goTo(event, SIGN_UP_PATH);
    }

    @Override
    public void postToServer(ActionEvent event)  {
        User user = new User();
        user.setEmail(email.getText());
        user.setPassword(password.getText());

        PostUserHandler postUser = new PostUserHandler(user, SIGN_IN_URL);
        postUser.setOnSucceeded(e -> {
            if(postUser.isSuccess()){
                signInMessage.setText("You have access");
                signInMessage.setStyle(signInMessage.getStyle() + validColor + "visibility: visible");
             }
            else {
                signInMessage.setText("Email or password invalid");
                signInMessage.setStyle(signInMessage.getStyle() + errorColor);
                signInMessage.setVisible(true);
            }

            System.out.println("Succeeded");
        });
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
