package com.electronic.voting.controller;

import com.electronic.voting.model.User;
import com.electronic.voting.model.Voting;
import com.electronic.voting.model.Candidate;
import com.electronic.voting.service.UserService;
import com.electronic.voting.service.VotingService;
import java.util.Date;
import java.util.List;
import com.electronic.voting.model.Role;

public class UserController {
    private UserService userService;
    private VotingService votingService;

    public UserController(UserService userService, VotingService votingService) {
        this.userService = userService;
        this.votingService = votingService;
    }

    public User registerUser(String fullName, Date birthDate, String snils, String login, String password) {
        return userService.registerUser(fullName, birthDate, snils, login, password, Role.USER);
    }

    public List<Voting> getAvailableVotings() {
        return votingService.getAllVotings().stream()
                .filter(v -> v.getEndDate().after(new Date()))
                .toList();
    }

    public List<Candidate> getCandidatesForVoting(int votingId) {
        return votingService.getAllVotings().stream()
                .filter(v -> v.getVotingId() == votingId)
                .findFirst()
                .map(Voting::getCandidates)
                .orElse(List.of());
    }
}