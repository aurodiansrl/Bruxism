package logic;

// import logic.PatientProfile; // Update this import to match the actual package of PatientProfile, for example:
import model.PatientProfile;

public class AFIScorer {

    public int calculateAFI(PatientProfile profile) {
        return (int)((profile.getSystemicFlags() + profile.getBehavioralFlags()) * 10);
    }
}