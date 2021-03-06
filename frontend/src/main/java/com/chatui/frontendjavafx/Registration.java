package com.chatui.frontendjavafx;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Registration {

    public final static String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    public final static String validBorder = "-fx-border-color: #d5dfec;";
    public final static String errorBorder ="-fx-border-color: #dc3545;";
    public final static String errorColor ="-fx-text-fill: #dc3545;";
    public final static String validColor ="-fx-text-fill: #35b874;";

    public final static String SIGN_UP_PATH = "SignUpUI.fxml";
    public final static String SIGN_IN_PATH = "SignInUI.fxml";
    public final static String HOME_PATH = "home.fxml";

    public final static String REGISTRATION_URL = "http://localhost:8080/api/v1/registration";
    public final static String SIGN_IN_URL = "http://localhost:8080/api/v1/signin";
    public final static String FRIENDS_URL = "http://localhost:8080/api/v1/friends/friendship";
    public final static String Modify_Cox_State = "http://localhost:8080/api/v1/ModifyCoxState";

    public boolean isUserValid = false;
    public boolean isSuccess = false;

    public void registrationHandler(ActionEvent event, String fileName){
        updateBoxes();
        if(isUserValid) {
            postToServer(event);
            //goTo(event, fileName);
        }
    }

    public void goTo(ActionEvent event, String fileName, Pane parentContainer, BorderPane childContainer, Button button1) {
        ScenesController scenesController = new ScenesController();
        scenesController.switchScene(event, fileName, parentContainer, childContainer, button1);
    }
    public void goTo(MouseEvent event, String fileName, Pane parentContainer, BorderPane childContainer, ImageView image) {
        ScenesController scenesController = new ScenesController();
        scenesController.switchScene(event, fileName, parentContainer, childContainer, image);
    }
    /*
    public void validateUserSuccess(User user) {
        User userFromServer = GetUserHandler.getUser(user.getEmail());
            if (userFromServer != null && userFromServer.equals(user)) {
                isSuccess =  true;
            }
            else
                isSuccess = false;
    }
    */
    public void showResponseMessage(Label label, String message){
        // if we are in SignUp class success means user doesn't exist in db
        /*if(className.equals("SignUp")) isSuccess = !isSuccess;*/

        if(message.equals("success")){
            label.setText(message);
            label.setStyle(label.getStyle() + " visibility: visible; " + validColor + validBorder );
            isSuccess = true;
        }
        else{
            label.setText(message);
            label.setStyle(label.getStyle() + " visibility: visible; " + errorColor + errorBorder);
            isSuccess = false;
        }
    }

    public void changeBoxStyle(TextField field, String color, boolean visibility){
        field.setStyle(field.getStyle() + color);
        getAttribute(field.getId()).setVisible(visibility);

    }

    public void updateEmailBox(TextField email, Label emailErrorMessage){
        if(isEmailValid(email)){
            changeBoxStyle(email, SignUp.validBorder,false);
            isUserValid=true;
        } else{
            changeBoxStyle(email, SignUp.errorBorder,true);
            emailErrorMessage.setText("Email entered is invalid");
            isUserValid=false;
        }
    }

    public void updateEmptyBox(TextField field) {
        if(isEmptyBox(field)){
            changeBoxStyle(field, errorBorder,true);
            getAttribute(field.getId()).setText(field.getId()+" cannot be empty");

            isUserValid=false;
        } else{
            changeBoxStyle(field, validBorder,false);
        }
    }

    public boolean isEmptyBox(TextField field){
        return field.getText().trim().isEmpty();
    }

    public  boolean isEmailValid(TextField email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email.getText());
        return matcher.find();
    }

    public abstract void updateBoxes();

    public abstract  void updatePasswordBoxes();

    public abstract Label getAttribute(String attribute);

    public abstract void postToServer(ActionEvent event);

   /* public abstract void authSuccess() throws FileNotFoundException; */
}
