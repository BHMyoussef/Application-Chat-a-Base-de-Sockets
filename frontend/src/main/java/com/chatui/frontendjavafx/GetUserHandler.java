package com.chatui.frontendjavafx;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetUserHandler {

    public static User[] getUsers() {
        Gson gson = new Gson();
        User[] Users = null;
        try {
            URL url = new URL(Registration.SIGN_IN_URL);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            InputStreamReader reader = new InputStreamReader(httpConnection.getInputStream());
            Users = gson.fromJson(reader, User[].class);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return Users;
    }

    public static User getUser(String email) {
        Gson gson = new Gson();
        User User = new User();
        try {
                URL url = new URL(Registration.SIGN_IN_URL + "/users/" + email);
                HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
                InputStreamReader reader = new InputStreamReader(httpConnection.getInputStream());
                User = gson.fromJson(reader, User.class);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return User;
    }
}
