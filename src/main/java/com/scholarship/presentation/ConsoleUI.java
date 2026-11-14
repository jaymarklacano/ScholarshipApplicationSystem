package main.java.com.scholarship.presentation;

import java.util.Scanner;

import main.java.com.scholarship.models.Applicant;
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

        System.out.println("============================================");
        System.out.println("          SCHOLARSHIP APPLICANT LOGIN");
        System.out.println("============================================");
        System.out.println();
        System.out.println("             Please Login First");
        System.out.println();

        System.out.print("Enter Name: ");
        String name = input.nextLine();

        System.out.print("Enter Password: ");
        String password = input.nextLine();

        System.out.println();
        System.out.println("--------------------------------------------");

        if (!authService.login(name, password)) {

            if (!name.equals(realName) &&
                !password.equals(realPassword)) {

                System.out.println("Incorrect username and password!");

            } else if (!name.equals(realName)) {

                System.out.println("Incorrect username!");

            } else {

                System.out.println("Incorrect password!");
            }

            System.out.println("--------------------------------------------");
            return;
        }
        
        System.out.println("Login successful!");
        System.out.println("--------------------------------------------");
        clearScreen();

        char choice;

        do {

            System.out.println();
            System.out.println("============================================");
            System.out.println("             SCHOLARSHIP SYSTEM");
            System.out.println("============================================");
            System.out.println();
            System.out.println("              APPLICANT MENU");
            System.out.println("--------------------------------------------");
            System.out.println();
            System.out.println("   [1]  Save Information");
            System.out.println("   [2]  Search ID Number");
            System.out.println("   [3]  Update Information");
            System.out.println("   [4]  Delete Information");
            System.out.println("   [5]  Exit");
            System.out.println();
            System.out.println("--------------------------------------------");
            System.out.print("   Enter your choice: ");
            
            choice = input.nextLine().charAt(0);

            clearScreen();

            switch (choice) {

                case '1':
                    saveApplicant();
                    break;

                case '2':
                    searchApplicant();
                    break;

                case '3':
                    updateApplicant();
                    break;

                case '4':
                    deleteApplicant();
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
    
                System.out.println("============================================");
                System.out.println("             SCHOLARSHIP SYSTEM");
                System.out.println("============================================");
                System.out.println();
                System.out.println("Record list is full.");
                System.out.println("Maximum of 50 applicants reached.");
                System.out.println("============================================");
                break;
            }
    
            System.out.println();
            System.out.println("============================================");
            System.out.println("          SCHOLARSHIP SYSTEM");
            System.out.println("============================================");
            System.out.println();
            System.out.println("        APPLICANT REGISTRATION");
            System.out.println("--------------------------------------------");
            System.out.println(
                "Assigned ID Number: " + applicantManager.getNextId()
            );
            System.out.println("--------------------------------------------");
            System.out.println();
    
            System.out.print("Last Name       : ");
            String lastName = input.nextLine();
    
            System.out.print("First Name      : ");
            String firstName = input.nextLine();
    
            System.out.print("Middle Name     : ");
            String middleName = input.nextLine();
    
            System.out.print("Address         : ");
            String address = input.nextLine();
    
            System.out.print("Contact Number  : ");
            String contactNumber = input.nextLine();
    
            System.out.print("GWA             : ");
            double gwa = Double.parseDouble(input.nextLine());
    
            System.out.println();
            System.out.println("--------------------------------------------");
    
            applicantManager.saveApplicant(
                lastName,
                firstName,
                middleName,
                address,
                contactNumber,
                gwa
            );
    
            System.out.println("Applicant record saved successfully!");
            System.out.println("--------------------------------------------");
    
            System.out.println();
            System.out.print("Do you want to register another applicant (y/n)? ");
            answer = input.nextLine().charAt(0);
    
            clearScreen();
    
            displayApplicants();
    
        } while (Character.toUpperCase(answer) == 'Y');
    }
    
   // SEARCH APPLICANT
private void searchApplicant() {

    char answer;

    do {

        if (applicantManager.getAllApplicants().isEmpty()) {

            System.out.println();
            System.out.println("============================================");
            System.out.println("             SCHOLARSHIP SYSTEM");
            System.out.println("============================================");
            System.out.println();
            System.out.println("          SEARCH APPLICANT");
            System.out.println("--------------------------------------------");
            System.out.println("No applicant records available to search.");
            System.out.println("============================================");
            break;
        }

        System.out.println();
        System.out.println("============================================");
        System.out.println("             SCHOLARSHIP SYSTEM");
        System.out.println("============================================");
        System.out.println();
        System.out.println("           SEARCH APPLICANT");
        System.out.println("--------------------------------------------");
        System.out.println();

        System.out.print("Enter Applicant ID Number: ");
        int searchId = Integer.parseInt(input.nextLine());

        Applicant applicant = applicantManager.searchApplicant(searchId);

        System.out.println();

        if (applicant != null) {

            System.out.println("============================================");
            System.out.println("          APPLICANT SEARCH RESULT");
            System.out.println("============================================");

            displayApplicantDetails(applicant);

            System.out.println("--------------------------------------------");
            System.out.println("Applicant record found successfully!");

        } else {

            System.out.println("--------------------------------------------");
            System.out.println("          APPLICANT SEARCH RESULT");
            System.out.println("--------------------------------------------");
            System.out.println("ID Number: " + searchId);
            System.out.println("Applicant record not found.");
        }

        System.out.println("--------------------------------------------");
        System.out.println();

        System.out.print("Do you want to search another ID (y/n)? ");
        answer = input.nextLine().charAt(0);

        clearScreen();
        displayApplicants();

    } while (Character.toUpperCase(answer) == 'Y');

}

   // UPDATE APPLICANT
private void updateApplicant() {

    char answer;

    do {

        if (applicantManager.getAllApplicants().isEmpty()) {

            System.out.println();
            System.out.println("============================================");
            System.out.println("             SCHOLARSHIP SYSTEM");
            System.out.println("============================================");
            System.out.println();
            System.out.println("          UPDATE APPLICANT");
            System.out.println("--------------------------------------------");
            System.out.println("No applicant records available to update.");
            System.out.println("============================================");
            break;
        }

        System.out.println();
        System.out.println("============================================");
        System.out.println("             SCHOLARSHIP SYSTEM");
        System.out.println("============================================");
        System.out.println();
        System.out.println("           UPDATE APPLICANT");
        System.out.println("--------------------------------------------");
        System.out.println();

        System.out.print("Enter Applicant ID Number: ");
        int searchId = Integer.parseInt(input.nextLine());

        Applicant applicant = applicantManager.searchApplicant(searchId);

        System.out.println();

        if (applicant == null) {

            System.out.println("--------------------------------------------");
            System.out.println("Applicant ID not found.");
            System.out.println("--------------------------------------------");

        } else {

            System.out.println("============================================");
            System.out.println("          EXISTING APPLICANT DETAILS");
            System.out.println("============================================");

            displayApplicantDetails(applicant);

            System.out.println("--------------------------------------------");
            System.out.println();
            System.out.println("       ENTER UPDATED APPLICANT INFORMATION");
            System.out.println("--------------------------------------------");
            System.out.println();

            System.out.print("Last Name       : ");
            String lastName = input.nextLine();

            System.out.print("First Name      : ");
            String firstName = input.nextLine();

            System.out.print("Middle Name     : ");
            String middleName = input.nextLine();

            System.out.print("Address         : ");
            String address = input.nextLine();

            System.out.print("Contact Number  : ");
            String contactNumber = input.nextLine();

            System.out.print("GWA             : ");
            double gwa = Double.parseDouble(input.nextLine());

            applicantManager.updateApplicant(
                searchId,
                lastName,
                firstName,
                middleName,
                address,
                contactNumber,
                gwa
            );

            System.out.println();
            System.out.println("--------------------------------------------");
            System.out.println("Applicant record updated successfully!");
            System.out.println("--------------------------------------------");
        }

        System.out.println();

        displayApplicants();

        System.out.println("--------------------------------------------");
        System.out.print("Do you want to update another applicant (y/n)? ");

        answer = input.nextLine().charAt(0);

        clearScreen();
        displayApplicants();

    } while (Character.toUpperCase(answer) == 'Y');

}
    // DELETE APPLICANT
private void deleteApplicant() {

    char answer;

    do {

        if (applicantManager.getAllApplicants().isEmpty()) {

            System.out.println();
            System.out.println("============================================");
            System.out.println("             SCHOLARSHIP SYSTEM");
            System.out.println("============================================");
            System.out.println();
            System.out.println("           DELETE APPLICANT");
            System.out.println("--------------------------------------------");
            System.out.println("No applicant records available to delete.");
            System.out.println("============================================");
            break;
        }

        System.out.println();
        System.out.println("============================================");
        System.out.println("             SCHOLARSHIP SYSTEM");
        System.out.println("============================================");
        System.out.println();
        System.out.println("           DELETE APPLICANT");
        System.out.println("--------------------------------------------");
        System.out.println();

        System.out.print("Enter Applicant ID Number: ");
        int deleteId = Integer.parseInt(input.nextLine());

        Applicant applicant = applicantManager.searchApplicant(deleteId);

        System.out.println();

        if (applicant == null) {

            System.out.println("--------------------------------------------");
            System.out.println("Applicant ID not found. Cannot delete.");
            System.out.println("--------------------------------------------");

        } else {

            System.out.println("============================================");
            System.out.println("          APPLICANT DELETE DETAILS");
            System.out.println("============================================");

            displayApplicantDetails(applicant);

            System.out.println("--------------------------------------------");
            System.out.println();

            System.out.print(
                "Are you sure you want to delete this applicant (y/n)? "
            );

            char confirm = input.nextLine().charAt(0);

            if (Character.toUpperCase(confirm) == 'Y') {

                applicantManager.deleteApplicant(deleteId);

                System.out.println();
                System.out.println("--------------------------------------------");
                System.out.println("Applicant record deleted successfully!");
                System.out.println("--------------------------------------------");

            } else {

                System.out.println();
                System.out.println("--------------------------------------------");
                System.out.println("Delete operation cancelled.");
                System.out.println("--------------------------------------------");
            }
        }

        System.out.println();

        displayApplicants();

        System.out.println("--------------------------------------------");
        System.out.print("Do you want to delete another applicant (y/n)? ");

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
    
    // DISPLAY ALL APPLICANTS
    private void displayApplicants() {

        if (applicantManager.getAllApplicants().isEmpty()) {
            System.out.println("\nNo applicants to display.");
            return;
        }

        System.out.println("\n===================================================================================================================================");
        System.out.println("                                            LIST OF SCHOLARSHIP APPLICANTS                                                         ");
        System.out.println("===================================================================================================================================");

        System.out.printf(
            "%-10s %-20s %-20s %-20s %-28s %-22s %-10s%n",
            "ID NUMBER",
            "LAST NAME",
            "FIRST NAME",
            "MIDDLE NAME",
            "ADDRESS",
            "CONTACT NUMBER",
            "GWA"
        );

         System.out.println(
            "-----------------------------------------------------------------------------------------------------------------------------------");

        for (Applicant applicant : applicantManager.getAllApplicants()) {
            displayApplicant(applicant);
        }

        System.out.println(
            "-----------------------------------------------------------------------------------------------------------------------------------");
    }
    
    // DISPLAY ONE APPLICANT
    private void displayApplicant(Applicant applicant) {
        System.out.printf(
            "%-10d %-20s %-20s %-20s %-28s %-22s %-10.2f%n",
            applicant.getIdNumber(),
            applicant.getLastName(),
            applicant.getFirstName(),
            applicant.getMiddleName(),
            applicant.getAddress(),
            applicant.getContactNumber(),
            applicant.getGWA()
        );  
    }

     // DISPLAY APPLICANT DETAILS
     private void displayApplicantDetails(Applicant applicant) {

        System.out.println("Applicant ID   : " + applicant.getIdNumber());
        System.out.println("Last Name      : " + applicant.getLastName());
        System.out.println("First Name     : " + applicant.getFirstName());
        System.out.println("Middle Name    : " + applicant.getMiddleName());
        System.out.println("Address        : " + applicant.getAddress());
        System.out.println("Contact Number : " + applicant.getContactNumber());
        System.out.println("GWA            : " + applicant.getGWA());
    }




    

}
