package com.electronic.voting.model;

public class Candidate {
    private int candidateId;
    private User user;
    private String bio;
    private String program;

    
    public Candidate() {}

    public Candidate(User user, String bio, String program) {
        this.user = user;
        this.bio = bio;
        this.program = program;
    }

    
    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }
}
