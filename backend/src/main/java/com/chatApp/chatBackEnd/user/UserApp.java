package com.chatApp.chatBackEnd.user;

import javax.persistence.*;

@Entity
@Table
public class UserApp {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private String password;
    private String repassword;

    public UserApp(Long id, String name, String email, String password, String repeatPassword) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.repassword = repeatPassword;
    }

    public UserApp() {
    }

    public UserApp(String name, String email, String password, String repeatPassword) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.repassword = repeatPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repassword = repeatPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repassword + '\'' +
                '}';
    }
}
