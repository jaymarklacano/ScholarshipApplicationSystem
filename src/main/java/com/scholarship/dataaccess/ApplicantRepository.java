package main.java.com.scholarship.dataaccess;

import java.util.ArrayList;

import main.java.com.scholarship.models.Applicant;

public class ApplicantRepository {

    private ArrayList<Applicant> applicants = new ArrayList<>();

    public void save(Applicant applicant) {
        applicants.add(applicant);
    }

    public Applicant search(int id) {

        for (Applicant applicant : applicants) {

            if (applicant.getIdNumber() == id) {
                return applicant;
            }
        }

        return null;
    }

    public boolean delete(int id) {

        Applicant applicant = search(id);

        if (applicant != null) {
            applicants.remove(applicant);
            return true;
        }

        return false;
    }

    public ArrayList<Applicant> getAll() {
        return applicants;
    }
}
