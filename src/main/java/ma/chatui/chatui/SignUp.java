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
        CheckBoxes();
    }
    public void CheckBoxes() {
        String style;
        if(name.getText().isEmpty() || email.getText().isEmpty() || password.getText().isEmpty() || repeatPassword.getText().isEmpty() )
            style = "-fx-border-color: #f00;";
        else
            style = "-fx-border-color: #d5dfec;";
        name.setStyle(name.getStyle() + style);
        email.setStyle(name.getStyle() + style);
        password.setStyle(name.getStyle() + style);
        repeatPassword.setStyle(name.getStyle() + style);
    }
}
