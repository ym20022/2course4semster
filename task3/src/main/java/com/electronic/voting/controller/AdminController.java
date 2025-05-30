package com.electronic.voting.controller;

import com.electronic.voting.model.User;
import com.electronic.voting.model.CEC;
import com.electronic.voting.model.Candidate;
import com.electronic.voting.service.UserService;
import com.electronic.voting.service.CECService;
import com.electronic.voting.service.CandidateService;
import java.util.List;
import com.electronic.voting.model.Role;  // Add this import
import com.electronic.voting.model.User;
import com.electronic.voting.model.CEC;
public class AdminController {
    private UserService userService;
    private CECService cecService;
    private CandidateService candidateService;

    public AdminController(UserService userService, CECService cecService, CandidateService candidateService) {
        this.userService = userService;
        this.cecService = cecService;
        this.candidateService = candidateService;
    }

    public List<User> viewAllUsers() {
        return userService.getAllUsers();
    }

    public boolean deleteUser(int userId) {
        return userService.deleteUser(userId);
    }

    public List<CEC> viewAllCECs() {
        return cecService.getAllCECs();
    }

    public boolean deleteCEC(int cecId) {
        return cecService.deleteCEC(cecId);
    }

    public CEC createCEC(String login, String password, String fullName) {
        User user = userService.registerUser(fullName, null, null, login, password, Role.CEC);
        return cecService.createCEC(new CEC(user));
    }

    public boolean deleteCandidate(int candidateId) {
        return candidateService.deleteCandidate(candidateId);
    }

    public List<Candidate> viewAllCandidates() {
        return candidateService.getAllCandidates();
    }


}