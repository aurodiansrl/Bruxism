package logic;

import java.util.*;
import model.PatientProfile;

public class AFIInterpreter {

    public enum AFICategory {
        COMORBIDITIES,
        GENETIC_HISTORY,
        BEHAVIORAL_DAY,
        BEHAVIORAL_NIGHT,
        SLEEP_QUALITY,
        ENVIRONMENTAL_STRESS,
        UNCLASSIFIED_SYSTEMIC
    }

    public static class AFIStratificationResult {
        public AFICategory category;
        public String description;
        public String interpretiveImpact;
        public String strategy;

        public AFIStratificationResult(AFICategory category, String description, String impact, String strategy) {
            this.category = category;
            this.description = description;
            this.interpretiveImpact = impact;
            this.strategy = strategy;
        }

        @Override
        public String toString() {
            return category.name() + ": " + description + "\nImpact: " + interpretiveImpact + "\nStrategy: " + strategy;
        }
    }

    private static final Map<AFICategory, AFIStratificationResult> STRATIFICATION_MAP = new HashMap<>();

    static {
        STRATIFICATION_MAP.put(AFICategory.COMORBIDITIES, new AFIStratificationResult(
            AFICategory.COMORBIDITIES,
            "Presence of medical conditions that may exacerbate bruxism or reduce therapeutic response.",
            "May require concurrent management of systemic disease; expect lower treatment efficacy.",
            "Start from Phase 2 in ITSL; consider dual-path therapies."
        ));
        STRATIFICATION_MAP.put(AFICategory.GENETIC_HISTORY, new AFIStratificationResult(
            AFICategory.GENETIC_HISTORY,
            "Positive family history or genetic susceptibility to bruxism-related disorders.",
            "Increases diagnostic complexity; may indicate predisposition requiring longer monitoring.",
            "Shorten follow-up interval; increase diagnostic breadth."
        ));
        STRATIFICATION_MAP.put(AFICategory.BEHAVIORAL_DAY, new AFIStratificationResult(
            AFICategory.BEHAVIORAL_DAY,
            "Daytime parafunctional behaviors increasing overload.",
            "Suggests muscular overload; prioritize muscular domain assessment and early EMG analysis.",
            "Enhance muscular interventions; lower threshold for instrument activation."
        ));
        STRATIFICATION_MAP.put(AFICategory.BEHAVIORAL_NIGHT, new AFIStratificationResult(
            AFICategory.BEHAVIORAL_NIGHT,
            "Nocturnal behaviors like grinding or clenching.",
            "Supports CNS involvement; consider polysomnography or neuro referral.",
            "Emphasize sleep medicine integration; adapt behavioral strategies."
        ));
        STRATIFICATION_MAP.put(AFICategory.SLEEP_QUALITY, new AFIStratificationResult(
            AFICategory.SLEEP_QUALITY,
            "Low sleep quality or sleep disturbance.",
            "Suggests poor prognosis; recommend psychological and behavioral interventions from Phase 1.",
            "Integrate stress management and cognitive training early."
        ));
        STRATIFICATION_MAP.put(AFICategory.ENVIRONMENTAL_STRESS, new AFIStratificationResult(
            AFICategory.ENVIRONMENTAL_STRESS,
            "Stressful environments or psychological triggers.",
            "May modulate any domain; introduce flexible therapeutic loops and close follow-up.",
            "Combine multi-domain observation; reinforce feedback loops."
        ));
        STRATIFICATION_MAP.put(AFICategory.UNCLASSIFIED_SYSTEMIC, new AFIStratificationResult(
            AFICategory.UNCLASSIFIED_SYSTEMIC,
            "Systemic factors with non-specific aggravating influence.",
            "Requires clinician judgment; may imply global risk or systemic burden.",
            "Enable manual override; clinician-defined adaptation."
        ));
    }

    public static List<AFIStratificationResult> interpretPatient(PatientProfile profile) {
        List<AFIStratificationResult> results = new ArrayList<>();
        Collection<AFICategory> categories = Arrays.asList((AFICategory[]) profile.getActiveAFICategories());
        for (AFICategory cat : categories) {
            if (STRATIFICATION_MAP.containsKey(cat)) {
                results.add(STRATIFICATION_MAP.get(cat));
            }
        }
        return results;
    }
}
