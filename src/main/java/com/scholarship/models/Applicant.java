package main.java.com.scholarship.models;

public class Applicant {
    private int idNumber;
    private String lastName;
    private String firstName;
    private String middleName;
    private String address;
    private String contactNumber;
    private double GWA;

    
    

    public Applicant(int idNumber, String lastName, String firstName, String middleName, String address, String contactNumber, double GWA) {

        this.idNumber = idNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.address = address;
        this.contactNumber = contactNumber;
        this.GWA = GWA;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public double getGWA() {
        return GWA;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setGWA(double gWA) {
        GWA = gWA;
    }

    
    



    
}
