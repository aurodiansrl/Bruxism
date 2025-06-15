package util;

public class PatientDataValidator {

    public boolean validate(PatientProfile profile) {
        return profile != null && profile.getName() != null && !profile.getName().isEmpty();
    }
}