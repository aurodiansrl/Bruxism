// File: model/TherapyPlan.java

package model;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class TherapyPlan {

    public static class DomainTherapy {
        private List<String> suggestedTherapies;
        private String rationale;
        private boolean redFlagDetected;
        private boolean conflictDetected;

        public DomainTherapy(List<String> suggestedTherapies, String rationale,
                             boolean redFlagDetected, boolean conflictDetected) {
            this.suggestedTherapies = suggestedTherapies;
            this.rationale = rationale;
            this.redFlagDetected = redFlagDetected;
            this.conflictDetected = conflictDetected;
        }

        public List<String> getSuggestedTherapies() {
            return suggestedTherapies;
        }

        public String getRationale() {
            return rationale;
        }

        public boolean isRedFlagDetected() {
            return redFlagDetected;
        }

        public boolean isConflictDetected() {
            return conflictDetected;
        }
    }

    private Map<String, DomainTherapy> domainTherapies;  // muscular, articular, occlusal, psychological
    private String patientId;
    private String createdBy;
    private String creationDate;
    private String overallNote;

    public TherapyPlan(String patientId, String createdBy, String creationDate) {
        this.patientId = patientId;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.domainTherapies = new HashMap<>();
    }

    public void addDomainTherapy(String domain, DomainTherapy therapy) {
        domainTherapies.put(domain, therapy);
    }

    public DomainTherapy getDomainTherapy(String domain) {
        return domainTherapies.get(domain);
    }

    public Map<String, DomainTherapy> getAllDomainTherapies() {
        return domainTherapies;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getOverallNote() {
        return overallNote;
    }

    public void setOverallNote(String overallNote) {
        this.overallNote = overallNote;
    }
}
