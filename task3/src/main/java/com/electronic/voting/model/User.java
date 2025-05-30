package com.electronic.voting.model;

import java.util.Date;

public class User {
    private int userId;
    private String fullName;
    private Date birthDate;
    private String snils;
    private String login;
    private String password;
    private Role role;

    
    public User() {}

    public User(String fullName, Date birthDate, String snils, String login, String password, Role role) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.snils = snils;
        this.login = login;
        this.password = password;
        this.role = role;
    }

   
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getSnils() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
