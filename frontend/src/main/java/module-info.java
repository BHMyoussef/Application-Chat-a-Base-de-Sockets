module com.chatui.frontendjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.net.http;
    requires javafx.graphics;
    requires javafx.base;
    requires Java.WebSocket;
    requires com.fasterxml.jackson.databind;

    opens com.chatui.frontendjavafx to javafx.fxml, com.google.gson, Jsvs.WebSocket, com.fasterxml.jackson.databind;
    exports com.chatui.frontendjavafx;
}