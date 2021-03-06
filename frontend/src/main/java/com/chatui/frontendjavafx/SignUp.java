package com.chatui.frontendjavafx;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.PopupWindow;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SignUp extends Registration{

    @FXML
    private TextField name;
    @FXML
    private Label nameErrorMessage;
    @FXML
    private TextField email;
    @FXML
    private Label emailErrorMessage;
    @FXML
    private PasswordField password;
    @FXML
    private Label passwordErrorMessage;
    @FXML
    private Label repeatPasswordErrorMessage;
    @FXML
    private PasswordField repeatPassword;
    @FXML
    private Label welcomeMessage;
    @FXML
    private Pane parentContainer1;
    @FXML
    private BorderPane childContainer;
    @FXML
    private Button button1;

    private boolean isSuccess = false;


    public void SignUpHandler(ActionEvent event) throws IOException {
        registrationHandler(event, SIGN_IN_PATH);
        /*authSuccess();*/
    }

    public void goSignIn(ActionEvent event){
        goTo(event, SIGN_IN_PATH, this.parentContainer1, this.childContainer, this.button1);
    }

    private boolean isPasswordsMatch(PasswordField firstPasswordField, PasswordField secondPasswordField){
        String firstPassword = firstPasswordField.getText().trim();
        String secondPassword = secondPasswordField.getText().trim();

        return !isEmptyBox(firstPasswordField) && !isEmptyBox(secondPasswordField) && firstPassword.equals(secondPassword);
    }
    /*
    @Override
    public void authSuccess() {
        User user = new User();
        user.setEmail(email.getText());
        validateUserSuccess(user);
        String validMsg = "Welcome, user " + name.getText() + " is created successfully";
        String errMsg = "Email already exists";
        showResponseMessage(welcomeMessage,validMsg, errMsg, getClass().getSimpleName());
    }
    */
    @Override
    public void postToServer(ActionEvent event)  {
        User user = new User();
        user.setName(name.getText());
        user.setEmail(email.getText());
        user.setPassword(password.getText());
        PostUserHandler postUser = new PostUserHandler(user, REGISTRATION_URL);
        postUser.call();
        String res = postUser.getRes();
        showResponseMessage(welcomeMessage,res);
        System.out.println(res);
        Gson gson = new Gson();
        new Thread(postUser).start();
    }

    @Override
    public void updateBoxes(){
        if(!isEmptyBox(this.email)){
            updateEmailBox(this.email, this.emailErrorMessage);
        }else {
            updateEmptyBox(this.email);
        }
        if(!isEmptyBox(this.password) && !isEmptyBox(this.repeatPassword)){
            updatePasswordBoxes();
        }else {
            updateEmptyBox(this.password);
            updateEmptyBox(this.repeatPassword);
        }

        updateEmptyBox(this.name);
    }

    @Override
    public void updatePasswordBoxes(){
        if(isPasswordsMatch(this.password, this.repeatPassword)){
            changeBoxStyle(this.password, validBorder,false);
            changeBoxStyle(this.repeatPassword, validBorder,false);
            isUserValid=true;
        }else {
            changeBoxStyle(this.password, errorBorder,true);
            changeBoxStyle(this.repeatPassword, errorBorder,true);

            this.passwordErrorMessage.setText("Passwords must be matched");
            this.repeatPasswordErrorMessage.setText("Passwords must be matched");
            isUserValid=false;
        }
    }

    @Override
    public Label getAttribute(String attribute) {
        switch (attribute) {
            case "name": return this.nameErrorMessage;
            case "email": return this.emailErrorMessage;
            case "password": return this.passwordErrorMessage;
            case "repeatPassword": return this.repeatPasswordErrorMessage;
            default: throw new RuntimeException("Invalid attribute: " + attribute);
        }
    }
}
