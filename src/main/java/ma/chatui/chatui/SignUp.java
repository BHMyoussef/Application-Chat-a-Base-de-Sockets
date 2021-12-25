package ma.chatui.chatui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
        String styleName;
        String styleEmail;
        String stylePassword;
        String styleRepeatPassword;
        if(name.getText().isEmpty())
            styleName = "-fx-border-color: #f00;";
        else
            styleName = "-fx-border-color: #d5dfec;";
        if(email.getText().isEmpty())
            styleEmail = "-fx-border-color: #f00;";
        else
            styleEmail = "-fx-border-color: #d5dfec;";
        if(password.getText().isEmpty())
            stylePassword = "-fx-border-color: #f00;";
        else
            stylePassword = "-fx-border-color: #d5dfec;";
        if(repeatPassword.getText().isEmpty())
            styleRepeatPassword = "-fx-border-color: #f00;";
        else
            styleRepeatPassword = "-fx-border-color: #d5dfec;";
        name.setStyle(name.getStyle() + styleName);
        email.setStyle(email.getStyle() + styleEmail);
        password.setStyle(password.getStyle() + stylePassword);
        repeatPassword.setStyle(repeatPassword.getStyle() + styleRepeatPassword);
    }
}
