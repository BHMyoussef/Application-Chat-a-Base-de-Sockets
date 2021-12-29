package com.chatui.frontendjavafx;

public class User {

    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password) {
        this.name = name.trim().toLowerCase();
        this.email = email.trim().toLowerCase();
        this.password = password.trim().toLowerCase();
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


    @Override
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

