package com.chatui.frontendjavafx;

import com.google.gson.Gson;
import javafx.concurrent.Task;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.List;

public class PostUserHandler extends Task<Void> {
    private Gson gson = new Gson();
    private User user;
    private String url;
    private User session = new User();
    private String res;
    private String token;

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
                        System.out.println(token);
                        message = List.of(tab[0].split(":"));
                        System.out.println(this.res);
                        String[] tk1 = token.split(" ");
                        System.out.println(tk1[1]);
                        String[] chunks = tk1[1].split("\\.");
                        String responseInfo = new String(new String(Base64.getDecoder().decode(chunks[1].replaceAll(" ", "")), "UTF-8"));
                        String [] tab1 = responseInfo.split("\\[");
                        String [] tab2 = tab1[1].split("\\]");
                        String [] info = tab2[0].split(",");
                        String email = info[0];
                        String name = info[1];
                        session.setEmail(email);
                        session.setName(name);
                        System.out.println(token + " " + name + " " + email);
                    }
                    else {
                        message = List.of(tab[1].split(":"));
                    }
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
