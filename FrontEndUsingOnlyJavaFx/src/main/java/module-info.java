module com.chatui.frontendjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.net.http;


    opens com.chatui.frontendjavafx to javafx.fxml;
    exports com.chatui.frontendjavafx;
}