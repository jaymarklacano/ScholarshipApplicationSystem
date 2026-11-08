package main.java.com.scholarship.service;

import java.util.ArrayList;

import main.java.com.scholarship.dataaccess.ApplicantRepository;
import main.java.com.scholarship.models.Applicant;

public class ApplicantManager {
    
    private ApplicantRepository repository;
    private int idCounter=2401;

    public ApplicantManager(ApplicantRepository repository) {
        this.repository = repository;
    }

    public boolean saveApplicant(String lastName, String firstName, String middleName, String address, String contactNumber, double GWA) {

        if (repository.getAll().size() >= 50) {
            return false;
        }

        Applicant applicant = new Applicant(
            idCounter,
            lastName,
            firstName,
            middleName,
            address,
            contactNumber,
            GWA
        );

        repository.save(applicant);

        idCounter++;

        return true;
    }

    public boolean updateApplicant(int id, String lastName,
        String firstName, String middleName,
        String address, String contactNumber,
        double GWA) {

        Applicant applicant = repository.search(id);

        if (applicant != null) {

            applicant.setLastName(lastName);
            applicant.setFirstName(firstName);
            applicant.setMiddleName(middleName);
            applicant.setAddress(address);
            applicant.setContactNumber(contactNumber);
            applicant.setGWA(GWA);

            return true;
        }

        return false;
    }

    public boolean deleteApplicant(int id) {
        return repository.delete(id);
    }

    public ArrayList<Applicant> getAllApplicants() {
        return repository.getAll();
    }

    public int getNextId() {
        return idCounter;
    }

    


    



}
