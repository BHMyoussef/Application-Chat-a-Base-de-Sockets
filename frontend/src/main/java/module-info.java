module com.chatui.frontendjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.net.http;
    requires javafx.graphics;


    opens com.chatui.frontendjavafx to javafx.fxml, com.google.gson;
    exports com.chatui.frontendjavafx;
}