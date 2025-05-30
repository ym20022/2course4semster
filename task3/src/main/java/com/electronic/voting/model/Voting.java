package com.electronic.voting.model;

import java.util.Date;
import java.util.List;

public class Voting {
    private int votingId;
    private String title;
    private Date startDate;
    private Date endDate;
    private List<Candidate> candidates;
    private CEC createdBy;

    
    public Voting() {}

    public Voting(String title, Date startDate, Date endDate, CEC createdBy) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdBy = createdBy;
    }

    
    public int getVotingId() {
        return votingId;
    }

    public void setVotingId(int votingId) {
        this.votingId = votingId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public CEC getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(CEC createdBy) {
        this.createdBy = createdBy;
    }
}
