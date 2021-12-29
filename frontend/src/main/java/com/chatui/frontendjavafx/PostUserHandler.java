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
    private final static String url = "http://localhost:1947/api/v1/registration";

    public PostUserHandler(User user) {
        this.user = user;
    }

    @Override
    public Void call()  {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(user)))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;

    }
}
