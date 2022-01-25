package com.chatui.frontendjavafx;

import java.util.Objects;

public class User {

    private String userID;

    private Long id;
    private String name;
    private String email;
    private String password;
    private int totalFriends = 0;

    public User(String userID, Long id, String name, String email, String password, int totalFriends) {
        this.userID = userID;
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.totalFriends = totalFriends;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTotalFriends() {
        return totalFriends;
    }

    public void setTotalFriends(int totalFriends) {
        this.totalFriends = totalFriends;
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
