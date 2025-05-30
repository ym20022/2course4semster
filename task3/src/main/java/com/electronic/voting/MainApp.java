package com.electronic.voting;

import com.electronic.voting.controller.*;
import com.electronic.voting.model.*;
import com.electronic.voting.service.*;

import java.util.Date;
import java.util.Scanner;

public class MainApp {
    private static UserService userService = new UserService();
    private static VotingService votingService = new VotingService();
    private static CandidateService candidateService = new CandidateService();
    private static CECService cecService = new CECService();
    private static PDFGenerator pdfGenerator = new PDFGenerator();

    private static AdminController adminController = new AdminController(userService, cecService, candidateService);
    private static CECController cecController = new CECController(votingService, candidateService, pdfGenerator);
    private static CandidateController candidateController = new CandidateController(candidateService, votingService);
    private static UserController userController = new UserController(userService, votingService);

    private static User currentUser;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Electronic Voting System");

        while (true) {
            if (currentUser == null) {
                showLoginMenu();
            } else {
                switch (currentUser.getRole()) {
                    case ADMIN -> showAdminMenu();
                    case CEC -> showCECMenu();
                    case CANDIDATE -> showCandidateMenu();
                    case USER -> showUserMenu();
                }
            }
        }
    }

    private static void showLoginMenu() {
        System.out.println("\n1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Choose option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1 -> login();
            case 2 -> register();
            case 3 -> System.exit(0);
            default -> System.out.println("Invalid option!");
        }
    }

    private static void login() {
        System.out.print("Enter login: ");
        String login = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        currentUser = userService.authenticate(login, password);
        if (currentUser == null) {
            System.out.println("Invalid credentials!");
        }
    }

    private static void register() {
        System.out.print("Enter full name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter birth date (yyyy-MM-dd): ");
        String birthDateStr = scanner.nextLine();
        System.out.print("Enter SNILS: ");
        String snils = scanner.nextLine();
        System.out.print("Enter login: ");
        String login = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // In a real app, you would parse the birthDateStr to Date
        userController.registerUser(fullName, new Date(), snils, login, password);
        System.out.println("Registration successful! Please login.");
    }

    private static void showAdminMenu() {
        System.out.println("\nADMIN MENU");
        System.out.println("1. View all users");
        System.out.println("2. Delete user");
        System.out.println("3. View all CECs");
        System.out.println("4. Delete CEC");
        System.out.println("5. Create CEC");
        System.out.println("6. View all candidates");
        System.out.println("7. Delete candidate");
        System.out.println("8. Logout");
        System.out.print("Choose option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1 -> adminController.viewAllUsers().forEach(System.out::println);
            case 2 -> {
                System.out.print("Enter user ID to delete: ");
                int userId = scanner.nextInt();
                adminController.deleteUser(userId);
            }
            // Implement other cases similarly
            case 8 -> currentUser = null;
            default -> System.out.println("Invalid option!");
        }
    }

    private static void showCECMenu() {
        System.out.println("\nCEC MENU");
        System.out.println("1. Create voting");
        System.out.println("2. Add candidate");
        System.out.println("3. Print results");
        System.out.println("4. Logout");
        System.out.print("Choose option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            // Implement CEC menu options
            case 4 -> currentUser = null;
            default -> System.out.println("Invalid option!");
        }
    }

    private static void showCandidateMenu() {
        System.out.println("\nCANDIDATE MENU");
        System.out.println("1. Update my info");
        System.out.println("2. View previous results");
        System.out.println("3. View all my votings");
        System.out.println("4. Logout");
        System.out.print("Choose option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            // Implement candidate menu options
            case 4 -> currentUser = null;
            default -> System.out.println("Invalid option!");
        }
    }

    private static void showUserMenu() {
        System.out.println("\nUSER MENU");
        System.out.println("1. View available votings");
        System.out.println("2. Vote");
        System.out.println("3. View candidates");
        System.out.println("4. View my votings");
        System.out.println("5. Logout");
        System.out.print("Choose option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            // Implement user menu options
            case 5 -> currentUser = null;
            default -> System.out.println("Invalid option!");
        }
    }
}