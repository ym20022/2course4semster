package com.electronic.voting.service;

import com.electronic.voting.model.Candidate;
import java.util.ArrayList;
import java.util.List;

public class CandidateService {
    private List<Candidate> candidates = new ArrayList<>();

    public Candidate createCandidate(Candidate candidate) {
        candidates.add(candidate);
        return candidate;
    }

    public List<Candidate> getAllCandidates() {
        return new ArrayList<>(candidates);
    }

    public boolean deleteCandidate(int candidateId) {
        return candidates.removeIf(c -> c.getCandidateId() == candidateId);
    }
}