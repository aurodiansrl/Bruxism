package util;

public class DataIntegrityChecker {

    public boolean check(PatientProfile profile) {
        return profile.getId() != null && profile.getId() > 0;
    }
}