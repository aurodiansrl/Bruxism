package util;

public class GDPRConsentManager {

    public boolean hasConsent(PatientProfile profile) {
        return profile != null && profile.isConsentGiven();
    }
}