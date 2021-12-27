package com.chatui.frontendjavafx;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Registration {

    public final static String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    public final static String validBorder = "-fx-border-color: #d5dfec;";
    public final static String errorBorder ="-fx-border-color: #dc3545;";

    public boolean isUserValid = false;

    public void registrationHandler(ActionEvent event, String fileName){
        updateBoxes();
        if(isUserValid) {
            goTo(event, fileName);
        }
    }

    public void goTo(ActionEvent event, String fileName) {
        ScenesController scenesController = new ScenesController();
        scenesController.switchScene(event, fileName);
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
}
