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

        String name = getValidName("Enter Name: ", false);
        String password = getValidPassword();

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

        int choice;

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
            System.out.println("   [5]  Scholarship Qualification");
            System.out.println("   [6]  Application Status");
            System.out.println("   [7]  Scholarship Requirements");
            System.out.println("   [8]  Scholarship Evaluation");
            System.out.println("   [9]  Scholarship Awardees");
            System.out.println("   [10] Applicant Reports");
            System.out.println("   [11] Scholarship Renewal");
            System.out.println("   [12] Exit");
            System.out.println();
            System.out.println("--------------------------------------------");
            choice = getValidChoice();

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
                    displayScholarshipQualification();
                    break;
                    
                case 6:
                    applicationStatus();
                    break;
                    
                case 7:
                    scholarshipRequirements();
                    break;
                    
                case 8:
                    scholarshipEvaluation();
                    break;
                    
                case 9:
                    displayScholarshipAwardees();
                    break;
                    
                case 10:
                    displayApplicantReports();
                    break;
                    
                case 11:
                    scholarshipRenewal();
                    break;
                    
                case 12:
                    System.out.println(
                        "\nExiting Scholarship Information System..."
                    );
                    break;
                default:
                    System.out.println(
                        "Invalid choice. Please try again."
                    );
            }

        } while (choice != 12);
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
    
            String lastName = getValidName("Last Name       : ", false);

            String firstName = getValidName("First Name      : ", false);
            
            String middleName = getValidName("Middle Name     : ", true);
            
            String address = getValidAddress();
            
            String contactNumber = getValidContactNumber();
            
            double gwa = getValidGWA();
    
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

        int searchId = getValidID("Enter Applicant ID Number: ");

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

        int searchId = getValidID("Enter Applicant ID Number: ");

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

            String lastName = getValidName("Last Name       : ", false);

            String firstName = getValidName("First Name      : ", false);
            
            String middleName = getValidName("Middle Name     : ", true);
            
            String address = getValidAddress();
            
            String contactNumber = getValidContactNumber();
            
            double gwa = getValidGWA();

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

        int deleteId = getValidID("Enter Applicant ID Number: ");

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


// SCHOLARSHIP QUALIFICATION
private void displayScholarshipQualification() {

    System.out.println();
    System.out.println("================================================================");
    System.out.println("              SCHOLARSHIP QUALIFICATION");
    System.out.println("================================================================");

    // TABLE 1: QUALIFIED APPLICANTS
    System.out.println();
    System.out.println("              POSSIBLE SCHOLARSHIP QUALIFIERS");
    System.out.println("                    GWA: 1.00 - 1.50");
    System.out.println("----------------------------------------------------------------");

    System.out.printf("%-10s %-20s %-20s %-10s%n",
            "ID NUMBER", "LAST NAME", "FIRST NAME", "GWA");

    System.out.println("----------------------------------------------------------------");

    boolean qualifiedFound = false;

    for (Applicant applicant : applicantManager.getAllApplicants()) {

        if (applicant.getGWA() <= 1.50) {

            System.out.printf("%-10d %-20s %-20s %-10.2f%n",
                    applicant.getIdNumber(),
                    applicant.getLastName(),
                    applicant.getFirstName(),
                    applicant.getGWA());

            qualifiedFound = true;
        }
    }

    if (!qualifiedFound) {
        System.out.println("No possible scholarship qualifiers found.");
    }

    System.out.println("----------------------------------------------------------------");


    // TABLE 2: OTHER APPLICANTS
    System.out.println();
    System.out.println("                  OTHER APPLICANTS");
    System.out.println("                    GWA: ABOVE 1.50");
    System.out.println("----------------------------------------------------------------");

    System.out.printf("%-10s %-20s %-20s %-10s%n",
            "ID NUMBER", "LAST NAME", "FIRST NAME", "GWA");

    System.out.println("----------------------------------------------------------------");

    boolean otherFound = false;

    for (Applicant applicant : applicantManager.getAllApplicants()) {

        if (applicant.getGWA() > 1.50) {

            System.out.printf("%-10d %-20s %-20s %-10.2f%n",
                    applicant.getIdNumber(),
                    applicant.getLastName(),
                    applicant.getFirstName(),
                    applicant.getGWA());

            otherFound = true;
        }
    }

    if (!otherFound) {
        System.out.println("No other applicants found.");
    }

    System.out.println("----------------------------------------------------------------");
}

// APPLICATION STATUS
private void applicationStatus() {

    System.out.println("\n============================================");
    System.out.println("             APPLICATION STATUS");
    System.out.println("============================================");

    if (applicantManager.getAllApplicants().isEmpty()) {
        System.out.println("No applicant records available.");
        return;
    }

    int id = getValidID("Enter Applicant ID Number: ");

    Applicant applicant = applicantManager.searchApplicant(id);

    if (applicant == null) {
        System.out.println("Applicant ID not found.");
        return;
    }

    System.out.println("\n--------------------------------------------");
    displayApplicantDetails(applicant);

    System.out.println("--------------------------------------------");
    System.out.println("Current Status: " +
            applicant.getApplicationStatus());

    System.out.println("\n[1] Pending");
    System.out.println("[2] Under Review");
    System.out.println("[3] Approved");
    System.out.println("[4] Rejected");
    System.out.println("[5] Cancel");

    System.out.print("\nSelect New Status: ");

    String choice = input.nextLine().trim();

    switch (choice) {

        case "1":
            applicant.setApplicationStatus("Pending");
            break;

        case "2":
            applicant.setApplicationStatus("Under Review");
            break;

        case "3":
            applicant.setApplicationStatus("Approved");
            break;

        case "4":
            applicant.setApplicationStatus("Rejected");
            break;

        case "5":
            System.out.println("Status update cancelled.");
            return;

        default:
            System.out.println("Invalid choice!");
            return;
    }

    System.out.println("--------------------------------------------");
    System.out.println("Application status updated successfully!");
    System.out.println("New Status: " +
            applicant.getApplicationStatus());
    System.out.println("--------------------------------------------");
}// SCHOLARSHIP REQUIREMENTS
private void scholarshipRequirements() {

    System.out.println("\n============================================");
    System.out.println("          SCHOLARSHIP REQUIREMENTS");
    System.out.println("============================================");

    if (applicantManager.getAllApplicants().isEmpty()) {
        System.out.println("No applicant records available.");
        return;
    }

    int id = getValidID("Enter Applicant ID Number: ");

    Applicant applicant = applicantManager.searchApplicant(id);

    if (applicant == null) {
        System.out.println("Applicant ID not found.");
        return;
    }

    System.out.println("\nApplicant Name: " +
            applicant.getFirstName() + " " +
            applicant.getLastName());

    System.out.println("--------------------------------------------");

    System.out.println("[1] Certificate of Enrollment");
    System.out.println("[2] Copy of Grades");
    System.out.println("[3] Certificate of Indigency");
    System.out.println("[4] Valid School ID");

    System.out.println("--------------------------------------------");
    System.out.println("UPDATE REQUIREMENT STATUS");
    System.out.println("--------------------------------------------");

    applicant.setEnrollmentSubmitted(
        askSubmitted("Certificate of Enrollment")
    );

    applicant.setGradesSubmitted(
        askSubmitted("Copy of Grades")
    );

    applicant.setIndigencySubmitted(
        askSubmitted("Certificate of Indigency")
    );

    applicant.setSchoolIDSubmitted(
        askSubmitted("Valid School ID")
    );

    System.out.println("\n============================================");
    System.out.println("       REQUIREMENT SUBMISSION STATUS");
    System.out.println("============================================");

    displayRequirement(
        "Certificate of Enrollment",
        applicant.isEnrollmentSubmitted()
    );

    displayRequirement(
        "Copy of Grades",
        applicant.isGradesSubmitted()
    );

    displayRequirement(
        "Certificate of Indigency",
        applicant.isIndigencySubmitted()
    );

    displayRequirement(
        "Valid School ID",
        applicant.isSchoolIDSubmitted()
    );

    System.out.println("--------------------------------------------");

    if (areRequirementsComplete(applicant)) {
        System.out.println("Requirements Status: COMPLETE");
    } else {
        System.out.println("Requirements Status: INCOMPLETE");
    }

    System.out.println("============================================");
}


// ASK REQUIREMENT STATUS
private boolean askSubmitted(String requirement) {

    while (true) {

        System.out.print(requirement + " submitted (y/n)? ");

        String answer = input.nextLine().trim();

        if (answer.equalsIgnoreCase("y")) {
            return true;
        }

        if (answer.equalsIgnoreCase("n")) {
            return false;
        }

        System.out.println("Invalid input! Enter y or n.");
    }
}


// DISPLAY REQUIREMENT
private void displayRequirement(String name, boolean submitted) {

    System.out.printf("%-30s : %s%n",
            name,
            submitted ? "Submitted" : "Pending");
}


// CHECK ALL REQUIREMENTS
private boolean areRequirementsComplete(Applicant applicant) {

    return applicant.isEnrollmentSubmitted()
        && applicant.isGradesSubmitted()
        && applicant.isIndigencySubmitted()
        && applicant.isSchoolIDSubmitted();
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


// VALIDATE NAME
private String getValidName(String message, boolean optional) {

    while (true) {

        System.out.print(message);
        String value = input.nextLine().trim();

        if (optional && value.isEmpty()) {
            return value;
        }

        if (value.matches("[\\p{L}][\\p{L} .'-]*")) {
            return value;
        }

        System.out.println(
            "Invalid input! Please enter letters only."
        );
    }
}

// VALIDATE ADDRESS
private String getValidAddress() {

    while (true) {

        System.out.print("Address         : ");
        String address = input.nextLine().trim();

        if (address.matches("[\\p{L}\\p{N} .,/#'()-]+")) {
            return address;
        }

        System.out.println(
            "Invalid address! Please enter a valid address."
        );
    }
}

// VALIDATE CONTACT NUMBER
private String getValidContactNumber() {

    while (true) {

        System.out.print("Contact Number  : ");
        String contact = input.nextLine().trim();

        if (contact.matches("\\d{10,15}")) {
            return contact;
        }

        System.out.println(
            "Invalid contact number! Enter 10 to 15 digits only."
        );
    }
}

// VALIDATE GWA
private double getValidGWA() {

    while (true) {

        System.out.print("GWA             : ");
        String value = input.nextLine().trim();

        try {

            double gwa = Double.parseDouble(value);

            if (Double.isFinite(gwa) && gwa >= 1.00 && gwa <= 5.00) {
                return gwa;
            }

            System.out.println(
                "Invalid GWA! Please enter a value from 1.00 to 5.00."
            );

        } catch (NumberFormatException e) {

            System.out.println(
                "Invalid GWA! Numbers and decimals only."
            );
        }
    }
}

// VALIDATE APPLICANT ID
private int getValidID(String message) {

    while (true) {

        System.out.print(message);
        String value = input.nextLine().trim();

        try {

            int id = Integer.parseInt(value);

            if (id > 0) {
                return id;
            }

            System.out.println("ID must be a positive number.");

        } catch (NumberFormatException e) {

            System.out.println("Invalid ID! Numbers only.");
        }
    }
}


// VALIDATE MENU CHOICE
private int getValidChoice() {

    while (true) {

        System.out.print("   Enter your choice: ");
        String value = input.nextLine().trim();

        try {
            int choice = Integer.parseInt(value);

            if (choice >= 1 && choice <= 12) {
                return choice;
            }

            System.out.println(
                "Invalid choice! Please enter a number from 1 to 12."
            );

        } catch (NumberFormatException e) {

            System.out.println("Invalid choice! Numbers only.");
        }
    }
}

// VALIDATE PASSWORD
private String getValidPassword() {

    while (true) {

        System.out.print("Enter Password: ");
        String password = input.nextLine();

        if (!password.trim().isEmpty()) {
            return password;
        }

        System.out.println("Password cannot be empty!");
    }
}





    

}
