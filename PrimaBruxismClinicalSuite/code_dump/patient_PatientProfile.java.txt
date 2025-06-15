// src/patient/PatientProfile.java
package patient;

import java.util.HashMap;
import java.util.Map;

public class PatientProfile {
    private String patientId;
    private Map<String, Double> domainScores;

    public PatientProfile(String patientId) {
        this.patientId = patientId;
        this.domainScores = new HashMap<>();
        domainScores.put("muscular", 0.0);
        domainScores.put("articular", 0.0);
        domainScores.put("occlusal", 0.0);
        domainScores.put("psychological", 0.0);
    }

    public void addScore(String domain, double score) {
        domainScores.put(domain, domainScores.getOrDefault(domain, 0.0) + score);
    }

    public double getScore(String domain) {
        return domainScores.getOrDefault(domain, 0.0);
    }

    public String getPatientId() {
        return patientId;
    }

    public Map<String, Double> getAllDomainScores() {
        return domainScores;
    }
}
