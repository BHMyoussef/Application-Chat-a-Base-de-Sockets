module com.chatui.frontendjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.net.http;
    requires javafx.graphics;
    requires javafx.base;
    requires Java.WebSocket;

    opens com.chatui.frontendjavafx to javafx.fxml, com.google.gson, Jsvs.WebSocket;
    exports com.chatui.frontendjavafx;
}