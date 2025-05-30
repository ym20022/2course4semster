package com.electronic.voting.service;

import com.electronic.voting.model.User;
import com.electronic.voting.model.Role;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserService {
    private List<User> users = new ArrayList<>();

    public User registerUser(String fullName, Date birthDate, String snils, String login, String password, Role role) {
        User user = new User(fullName, birthDate, snils, login, password, role);
        users.add(user);
        return user;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public boolean deleteUser(int userId) {
        return users.removeIf(user -> user.getUserId() == userId);
    }

    public User authenticate(String login, String password) {
        return users.stream()
                .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
}