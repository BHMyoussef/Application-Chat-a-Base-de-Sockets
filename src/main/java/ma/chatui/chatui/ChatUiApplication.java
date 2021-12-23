package ma.chatui.chatui;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatUiApplication {

    public static void main(String[] args) {
        Application.launch(UIChatApp.class, args);
    }

}
