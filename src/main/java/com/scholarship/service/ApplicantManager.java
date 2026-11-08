package main.java.com.scholarship.service;

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

    



}
