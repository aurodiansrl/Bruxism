import model.PatientProfile;
import controller.ClinicalDecisionCoordinator;

public class Main {
    public static void main(String[] args) {
        PatientProfile profile = new PatientProfile(
            1, "Mario Rossi",
            70.0, 60.0,
            50.0, 40.0,
            30.0, 80.0,
            70.0, 2,
            3, true
        );

        ClinicalDecisionCoordinator coordinator = new ClinicalDecisionCoordinator();
        coordinator.process(profile);
    }
}
