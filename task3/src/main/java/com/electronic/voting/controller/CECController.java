package com.electronic.voting.controller;

import com.electronic.voting.model.Voting;
import com.electronic.voting.model.Candidate;
import com.electronic.voting.model.CEC;
import com.electronic.voting.service.VotingService;
import com.electronic.voting.service.CandidateService;
import com.electronic.voting.service.PDFGenerator;
import java.util.Date;
import java.util.List;

public class CECController {
    private VotingService votingService;
    private CandidateService candidateService;
    private PDFGenerator pdfGenerator;

    public CECController(VotingService votingService, CandidateService candidateService, PDFGenerator pdfGenerator) {
        this.votingService = votingService;
        this.candidateService = candidateService;
        this.pdfGenerator = pdfGenerator;
    }

    public Voting createVoting(String title, Date endDate, CEC createdBy) {
        return votingService.createVoting(title, new Date(), endDate, createdBy);
    }

    public Candidate addCandidate(Candidate candidate) {
        return candidateService.createCandidate(candidate);
    }

    public void printResults(Voting voting, String outputPath) {
        try {
            pdfGenerator.generateVotingResults(voting, outputPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Voting> getAllVotings() {
        return votingService.getAllVotings();
    }
}