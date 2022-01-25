package com.chatui.frontendjavafx;

import java.util.Objects;

public class User {

    private String userId;

    private Long id;
    private String name;
    private String email;
    private String password;
    private boolean is_connected;
    private int totalFriends = 0;

    public User(String name, String email, String password, String userId, boolean is_connected) {
        this.name = name.trim().toLowerCase();
        this.email = email.trim().toLowerCase();
        this.password = password.trim().toLowerCase();
        this.userId = userId;
        this.is_connected = is_connected;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim().toLowerCase();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim().toLowerCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password.trim().toLowerCase();
    }

    public boolean isIs_connected() {
        return is_connected;
    }

    public void setIs_connected(boolean is_connected) {
        this.is_connected = is_connected;
    }

    @Override
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        // check for sign in
        if(user.password != null)  return Objects.equals(email, user.email) && Objects.equals(password, user.password);
        // check for sign up
        return Objects.equals(email, user.email);
    }
}

