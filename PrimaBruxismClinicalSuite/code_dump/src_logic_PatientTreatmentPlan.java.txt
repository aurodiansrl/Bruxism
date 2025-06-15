// src/patient/PatientTreatmentPlan.java
package logic;

import java.util.*;

public class PatientTreatmentPlan {

    public static class TherapyEntry {
        public final String domain;
        public final String therapy;
        public final String rationale;
        public final Date timestamp;

        public TherapyEntry(String domain, String therapy, String rationale) {
            this.domain = domain;
            this.therapy = therapy;
            this.rationale = rationale;
            this.timestamp = new Date();
        }

        @Override
        public String toString() {
            return "[" + domain.toUpperCase() + "] → " + therapy + " (Note: " + rationale + ")";
        }
    }

    private final List<TherapyEntry> therapies;
    private final List<String> conflictNotes;
    private String clinicianNotes;
    private final Date creationDate;

    public PatientTreatmentPlan() {
        this.therapies = new ArrayList<>();
        this.conflictNotes = new ArrayList<>();
        this.creationDate = new Date();
    }

    public void addTherapy(String domain, String therapy, String rationale) {
        therapies.add(new TherapyEntry(domain, therapy, rationale));
    }

    public void addConflictNote(String note) {
        conflictNotes.add(note);
    }

    public void setClinicianNotes(String notes) {
        this.clinicianNotes = notes;
    }

    public List<TherapyEntry> getTherapies() {
        return therapies;
    }

    public List<String> getConflictNotes() {
        return conflictNotes;
    }

    public String getClinicianNotes() {
        return clinicianNotes;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void printPlan() {
        System.out.println("🩺 Final Patient Treatment Plan");
        System.out.println("Date: " + creationDate);
        for (TherapyEntry entry : therapies) {
            System.out.println("• " + entry);
        }
        if (!conflictNotes.isEmpty()) {
            System.out.println("\n⚠️ Conflict Notes:");
            for (String note : conflictNotes) {
                System.out.println("• " + note);
            }
        }
        if (clinicianNotes != null && !clinicianNotes.isBlank()) {
            System.out.println("\n📝 Clinician Notes:\n" + clinicianNotes);
        }
    }
}
