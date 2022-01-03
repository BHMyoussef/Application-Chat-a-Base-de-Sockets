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
    private User session;
    private String res;

    public PostUserHandler(User user, String url) {
        this.user = user;
        this.url = url;
    }

    public String getRes() {
        return res;
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
            System.out.println(response.body());

            if(response.statusCode()==500){
                    String [] tab = response.body().split(",");
                    String [] message = tab[3].split(":");
                   this.res = message[1].substring(1,message[1].length()-1);
                }
            else if(response.statusCode()==200){
                    this.session= gson.fromJson(response.body(),User.class);
                    this.res = "success";
                    // here the user has a succesfull login start chat session containe all user info
               }
            }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;

    }
}
