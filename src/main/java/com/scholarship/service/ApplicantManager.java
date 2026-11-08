package main.java.com.scholarship.service;

import main.java.com.scholarship.dataaccess.ApplicantRepository;

public class ApplicantManager {
    
    private ApplicantRepository repository;
    private int idCounter = 2401;

    public ApplicantManager(ApplicantRepository repository) {
        this.repository = repository;
    }

}
