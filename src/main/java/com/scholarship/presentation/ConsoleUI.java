package main.java.com.scholarship.presentation;

import java.util.Scanner;

import main.java.com.scholarship.service.ApplicantManager;
import main.java.com.scholarship.service.AuthService;

public class ConsoleUI {
    
    private Scanner input = new Scanner(System.in);

    private AuthService authService;
    private ApplicantManager applicantManager;

    public ConsoleUI(AuthService authService,
                     ApplicantManager applicantManager) {

        this.authService = authService;
        this.applicantManager = applicantManager;
    }


    public void start() {

        String realName = "Jay Mark Lacano";
        String realPassword = "12345";

        System.out.print("Enter Name: ");
        String name = input.nextLine();

        System.out.print("Enter Password: ");
        String password = input.nextLine();

        if (!authService.login(name, password)) {

            if (!name.equals(realName) &&
                !password.equals(realPassword)) {

                System.out.println("Incorrect username and password!");

            } else if (!name.equals(realName)) {

                System.out.println("Incorrect username!");

            } else {

                System.out.println("Incorrect password!");
            }

            return;
        }

        



    }

}
