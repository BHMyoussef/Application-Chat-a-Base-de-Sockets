package com.chatui.frontendjavafx;

import com.google.gson.Gson;
import javafx.concurrent.Task;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PostUserHandler extends Task<Void> {
    private Gson gson = new Gson();
    private User user;
    private String url;

    public PostUserHandler(User user) {
        this.user = user;
        this.url = "http://localhost:8080/api/chat/users";
    }

    @Override
    public Void call() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(user)))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return null;
    }
}
