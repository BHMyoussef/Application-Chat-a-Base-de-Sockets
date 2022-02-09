package com.chatui.frontendjavafx;

import com.eu.mivrenik.stomp.StompFrame;
import com.eu.mivrenik.stomp.client.StompClient;
import com.eu.mivrenik.stomp.client.listener.StompMessageListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import javafx.concurrent.Task;
import org.java_websocket.drafts.Draft_6455;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostUserHandler extends Task<Void> {
    private Gson gson = new Gson();
    private User user;
    private String url;
    private User session = new User();
    private String res;
    private String token;
    private HttpResponse<String> listUsers;
    public static StompClient stompSocket;

    public PostUserHandler(User user, String url) {
        this.user = user;
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public String getRes() {
        return res;
    }

    public User getSession() {
        return session;
    }
    public void setSession(User user) {
         session = user;
    }

    public HttpResponse<String> getListUsers() {
        return listUsers;
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
            System.out.println("***************************************");
            String [] tab = response.body().split(",");
            List<String> message = null;
            switch (this.url) {
                case Registration.REGISTRATION_URL:
                    if(response.statusCode()==500){
                        message = List.of(tab[3].split(":"));
                    }
                    else if(response.statusCode()==200){
                        this.session= gson.fromJson(response.body(),User.class);
                        this.res = "success";
                        // here the user has a succesfull login start chat session containe all user info
                    }
                    break;
                case Registration.SIGN_IN_URL:
                    if(response.headers().firstValue("Authorization").isPresent()){
                        this.token = response.headers().firstValue("Authorization").get();
                        String userId = response.headers().firstValue("userId").get();

                        this.res = "success";

                        HttpRequest getRequest = HttpRequest.newBuilder()
                                .uri(URI.create("http://localhost:8080/api/v1/users/" + userId))
                                .header("Content-Type", "application/json")
                                .header("accept", "application/json")
                                .header("Authorization", UserToken.token)
                                .GET()
                                .build();
                        HttpResponse<String> currentUser = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
                        System.out.println(currentUser.body());
                        this.session = new ObjectMapper().readValue(currentUser.body(), User.class);

                        System.out.println("profile");
                        System.out.println(getSession());
                            User profile = getSession();
                            String token = UserToken.token;
                            Map<String, String> httpHeaders =new HashMap<>();
                            System.out.println(token);
                            httpHeaders.put("Authorization",token);
                            stompSocket = new StompClient(
                                    URI.create("ws://localhost:8080/chat"),new Draft_6455(), httpHeaders,0);
                            boolean connected = false;
                            try {
                                connected = stompSocket.connectBlocking();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (!connected) {
                                System.out.println("Failed to connect to the socket");
                            }
                            String id = profile.getUserID();
                            stompSocket.subscribe("/topic/messages/"+id, new StompMessageListener() {
                                @Override
                                public void onMessage(StompFrame stompFrame) {
                                    System.out.println("Server Upcoming message: " + stompFrame.getBody());
                                }

                        });
                    }
                    else {
                        message = List.of(tab[1].split(":"));
                    }
                    break;
                case Registration.Modify_Cox_State:
                    break;

            }
            this.res = message.get(1).substring(1,message.get(1).length()-1);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;

    }
}
