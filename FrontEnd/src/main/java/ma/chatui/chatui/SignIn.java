package ma.chatui.chatui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class SignIn {
    @FXML
    private TextField email;
    @FXML
    private Label emailErrorMessage;
    @FXML
    private PasswordField password;
    @FXML
    private Label passwordErrorMessage;

    private boolean isUserValid = false;

    public void SignInHandler(ActionEvent event) throws IOException {
        updateBoxes();
        if(isUserValid) {
            GoSignUp(event);
        }
    }
    public void GoSignUp(ActionEvent event) throws IOException {
        ScenesController scenesController = new ScenesController();
        scenesController.switchScene(event, "/SignUpUi.fxml");
    }
    public void updateBoxes(){
        if(!isEmptyBox(this.email)){
            updateEmailBox();
        }else {
            updateEmptyBox(this.email);
        }
        if(!isEmptyBox(this.password)){
            updatePasswordBoxes();
        }else
            updateEmptyBox(this.password);
    }

    public void updateEmptyBox(TextField field) {
        if(isEmptyBox(field)){
            changeBoxStyle(field, SignUp.errorBorder,true);
            getAttribute(field.getId()).setText(field.getId()+" cannot be empty");

            isUserValid=false;
        } else{
            changeBoxStyle(field, SignUp.validBorder,false);
        }
    }

    public void updateEmailBox(){
        if(isEmailValid(this.email)){
            changeBoxStyle(this.email, SignUp.validBorder,false);
            isUserValid=true;
        } else{
            changeBoxStyle(this.email, SignUp.errorBorder,true);
            this.emailErrorMessage.setText("Email entered is invalid");
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
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(SignUp.EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email.getText());
        return matcher.find();
    }
    public void updatePasswordBoxes(){
        changeBoxStyle(this.password, SignUp.validBorder,false);
    }

    private Label getAttribute(String attribute) {
        switch (attribute) {
            case "email": return this.emailErrorMessage;
            case "password": return this.passwordErrorMessage;
            default: throw new RuntimeException("Invalid attribute: " + attribute);
        }
    }
}
