package com.chatui.frontendjavafx;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetUserHandler {

    public static User[] getUsers() {
        Gson gson = new Gson();
        User[] Users = null;
        try {
            URL url = new URL(Registration.REGISTRATION_URL);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            InputStreamReader reader = new InputStreamReader(httpConnection.getInputStream());
            Users = gson.fromJson(reader, User[].class);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return Users;
    }
}
