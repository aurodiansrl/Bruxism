// File: test/model/PatientProfileTest.java

package test.model;

import model.PatientProfile;
import model.FTSResult;
import model.RiskProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.HashMap;

public class PatientProfileTest {

    private PatientProfile profile;

    @BeforeEach
    void setUp() {
        profile = new PatientProfile("TEST123");
    }

    @Test
    void testPatientIdAssignment() {
        assertEquals("TEST123", profile.getPatientId());
    }

    @Test
    void testFTSAssignmentAndRetrieval() {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("muscular", 30);
        scores.put("articular", 10);
        scores.put("occlusal", 35);
        scores.put("psychological", 25);

        FTSResult fts = new FTSResult(scores);
        profile.setFtsResult(fts);

        assertEquals(30, profile.getFtsResult().getScore("muscular"));
        assertEquals("Possible", profile.getFtsResult().getClassification("muscular"));
        assertEquals("Probable", profile.getFtsResult().getClassification("occlusal"));
    }

    @Test
    void testRiskProfileSetAndGet() {
        RiskProfile riskProfile = new RiskProfile();
        riskProfile.setFlag("insomnia", true);
        riskProfile.setFlag("reflux", false);

        profile.setRiskProfile(riskProfile);

        assertTrue(profile.getRiskProfile().hasFlag("insomnia"));
        assertFalse(profile.getRiskProfile().hasFlag("reflux"));
    }

    @Test
    void testTherapyHistoryManagement() {
        profile.addTherapyAction("Applied relaxation therapy");
        profile.addTherapyAction("Prescribed splint");

        assertEquals(2, profile.getTherapyHistory().size());
        assertTrue(profile.getTherapyHistory().contains("Prescribed splint"));
    }

    @Test
    void testUpdateAndRetrieveClinicalFlags() {
        profile.addClinicalFlag("High anxiety score");
        profile.addClinicalFlag("Severe tooth wear");

        assertEquals(2, profile.getClinicalFlags().size());
        assertTrue(profile.getClinicalFlags().contains("Severe tooth wear"));
    }

    @Test
    void testToStringSummary() {
        profile.addTherapyAction("Splint");
        String summary = profile.toString();
        assertTrue(summary.contains("Patient ID"));
        assertTrue(summary.contains("Splint"));
    }
}
