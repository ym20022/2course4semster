package com.electronic.voting.service;

import com.electronic.voting.model.Voting;
import com.electronic.voting.model.Candidate;
import com.electronic.voting.model.CEC;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VotingService {
    private List<Voting> votings = new ArrayList<>();

    public Voting createVoting(String title, Date startDate, Date endDate, CEC createdBy) {
        Voting voting = new Voting(title, startDate, endDate, createdBy);
        votings.add(voting);
        return voting;
    }

    public List<Voting> getAllVotings() {
        return new ArrayList<>(votings);
    }

    public boolean addCandidateToVoting(int votingId, Candidate candidate) {
        return votings.stream()
                .filter(v -> v.getVotingId() == votingId)
                .findFirst()
                .map(v -> {
                    v.getCandidates().add(candidate);
                    return true;
                })
                .orElse(false);
    }
}