package main.java.com.scholarship;

import main.java.com.scholarship.dataaccess.ApplicantRepository;
import main.java.com.scholarship.presentation.ConsoleUI;
import main.java.com.scholarship.service.ApplicantManager;
import main.java.com.scholarship.service.AuthService;

public class Main {
    public static void main(String[] args) {

        ApplicantRepository repository = new ApplicantRepository();

        AuthService authService = new AuthService();

        ApplicantManager applicantManager = new ApplicantManager(repository);

        ConsoleUI consoleUI = new ConsoleUI(authService, applicantManager);

        consoleUI.start();

    }
}
