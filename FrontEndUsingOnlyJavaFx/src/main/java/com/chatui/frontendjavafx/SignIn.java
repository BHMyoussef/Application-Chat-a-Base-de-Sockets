package com.chatui.frontendjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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


    public void SignInHandler(ActionEvent event) throws IOException {
        registrationHandler(event, "/com/chatui/frontendjavafx/SignUpUI.fxml");
    }

    public void goSignUp(ActionEvent event){
        goTo(event, "/com/chatui/frontendjavafx/SignUpUI.fxml");
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
        changeBoxStyle(this.password, SignUp.validBorder,false);
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
