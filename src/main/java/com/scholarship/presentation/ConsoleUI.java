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
        
        System.out.println("Login successful!");
        clearScreen();

        char choice;

        do {

            System.out.println("\n===== MENU =====");
            System.out.println("1. Save");
            System.out.println("2. Search");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Exit");
            System.out.print("Choice: ");

            choice = input.nextLine().charAt(0);

            clearScreen();

            switch (choice) {

                case '1':
                    break;

                case '2':
                    break;

                case '3':
                    break;

                case '4':
                    break;

                case '5':
                    System.out.println(
                        "\nExiting Scholarship Information System..."
                    );
                    break;

                default:
                    System.out.println(
                        "Invalid choice. Please try again."
                    );
            }

        } while (choice != '5');
    }

    private void saveApplicant() {

        char answer;

        do {

            if (applicantManager.getAllApplicants().size() >= 50) {
                System.out.println("Record list is full.");
                break;
            }

            System.out.println(
                "Assigned ID number: " + applicantManager.getNextId()
            );

            System.out.print("Last Name: ");
            String lastName = input.nextLine();

            System.out.print("First Name: ");
            String firstName = input.nextLine();

            System.out.print("Middle Name: ");
            String middleName = input.nextLine();

            System.out.print("Address: ");
            String address = input.nextLine();

            System.out.print("Contact Number: ");
            String contactNumber = input.nextLine();

            System.out.print("GWA: ");
            double gwa = Double.parseDouble(input.nextLine());

            applicantManager.saveApplicant(
                lastName,
                firstName,
                middleName,
                address,
                contactNumber,
                gwa
            );

            System.out.println("Record saved successfully.");

            System.out.print(
                "Do you still want to input another data (y/n)? "
            );

            answer = input.nextLine().charAt(0);

            clearScreen();

            displayApplicants();

        } while (Character.toUpperCase(answer) == 'Y');
    }
    

    
    //CLEAR SCREEN
    private void clearScreen() {

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
        
    private void displayApplicants() {

        if (applicantManager.getAllApplicants().isEmpty()) {
            System.out.println("\nNo applicants to display.");
            return;
        }

        System.out.println("\n=====================================");
        System.out.println(" LIST OF SCHOLARSHIP APPLICANTS");
        System.out.println("=====================================");

        System.out.printf(
            "%-10s %-20s %-20s %-20s %-25s %-15s %-10s%n",
            "ID NUMBER",
            "LAST NAME",
            "FIRST NAME",
            "MIDDLE NAME",
            "ADDRESS",
            "CONTACT NUMBER",
            "GWA"
        );






    }


    

}
