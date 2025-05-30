package com.electronic.voting.controller;

import com.electronic.voting.model.Candidate;
import com.electronic.voting.model.Voting;
import com.electronic.voting.service.CandidateService;
import com.electronic.voting.service.VotingService;
import java.util.List;

public class CandidateController {
    private CandidateService candidateService;
    private VotingService votingService;

    public CandidateController(CandidateService candidateService, VotingService votingService) {
        this.candidateService = candidateService;
        this.votingService = votingService;
    }

    public void updateCandidateInfo(Candidate candidate, String bio, String program) {
        candidate.setBio(bio);
        candidate.setProgram(program);
        // In a real application, you would save this to a database
    }

    public List<Voting> getParticipatedVotings(int candidateId) {
        return votingService.getAllVotings().stream()
                .filter(v -> v.getCandidates().stream()
                        .anyMatch(c -> c.getCandidateId() == candidateId))
                .toList();
    }
}