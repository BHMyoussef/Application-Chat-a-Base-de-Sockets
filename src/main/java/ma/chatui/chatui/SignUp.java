package ma.chatui.chatui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.springframework.stereotype.Component;

@Component
public class SignUp {
    @FXML
    private Button button;
    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField repeatPassword;

    public void SignUpHandler(ActionEvent event) {
        updateBoxes();
    }

    public void updateBoxes(){
        updateBoxStyles(name);
        updateBoxStyles(email);
        updateBoxStyles(password);
        updateBoxStyles(repeatPassword);
    }

    public void updateBoxStyles(TextField field) {
        String style;
        if(isEmptyBox(field))
            style = "-fx-border-color: #f00;";
        else
            style = "-fx-border-color: #d5dfec;";
        field.setStyle(field.getStyle() + style);
    }

    private boolean isEmptyBox(TextField field){
        if(field.getText().isEmpty()) return true;
        else return false;
    }
}
