package com.electronic.voting.model;

public class CEC {
    private int cecId;
    private User user;

    // Constructors, getters, and setters
    public CEC() {}

    public CEC(User user) {
        this.user = user;
    }

    // Getters and setters
    public int getCecId() {
        return cecId;
    }

    public void setCecId(int cecId) {
        this.cecId = cecId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}