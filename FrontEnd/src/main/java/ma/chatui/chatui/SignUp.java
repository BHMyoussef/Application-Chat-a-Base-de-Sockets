package ma.chatui.chatui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class SignUp {

    private final static String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private final static String validBorder = "-fx-border-color: #d5dfec;";
    private final static String errorBorder ="-fx-border-color: #dc3545;";

    @FXML
    private Button button;
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

    private boolean isUserValid = false;

    public void SignUpHandler(ActionEvent event) throws IOException {
        updateBoxes();
        if(isUserValid){
            ScenesController scenesController = new ScenesController();
            scenesController.switchScene(event, "/SignInUi.fxml");
        }
    }

    public void updateBoxes(){
        if(!isEmptyBox(this.email)){
            updateEmailBox();
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

    public void updateEmptyBox(TextField field) {
        if(isEmptyBox(field)){
            changeBoxStyle(field, errorBorder,true);
            getAttribute(field.getId()).setText(field.getId()+" cannot be empty");

            isUserValid=false;
        } else{
            changeBoxStyle(field, validBorder,false);
        }
    }

    public void updateEmailBox(){
        if(isEmailValid(this.email)){
            changeBoxStyle(this.email, validBorder,false);
            isUserValid=true;
        } else{
            changeBoxStyle(this.email, errorBorder,true);
            this.emailErrorMessage.setText("Email entered is invalid");
            isUserValid=false;
        }
    }

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

    private void changeBoxStyle(TextField field, String color, boolean visibility){
        field.setStyle(field.getStyle() + color);
        getAttribute(field.getId()).setVisible(visibility);

    }

    private boolean isEmptyBox(TextField field){
        return field.getText().trim().isEmpty();
    }

    private  boolean isEmailValid(TextField email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email.getText());
        return matcher.find();
    }

    private boolean isPasswordsMatch(PasswordField firstPasswordField, PasswordField secondPasswordField){
        String firstPassword = firstPasswordField.getText().trim();
        String secondPassword = secondPasswordField.getText().trim();

        return !isEmptyBox(firstPasswordField) && !isEmptyBox(secondPasswordField) && firstPassword.equals(secondPassword);
    }

    private Label getAttribute(String attribute) {
        switch (attribute) {
            case "name": return this.nameErrorMessage;
            case "email": return this.emailErrorMessage;
            case "password": return this.passwordErrorMessage;
            case "repeatPassword": return this.repeatPasswordErrorMessage;
            default: throw new RuntimeException("Invalid attribute: " + attribute);
        }
    }
}
