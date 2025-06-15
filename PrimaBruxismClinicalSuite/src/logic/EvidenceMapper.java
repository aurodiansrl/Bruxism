// src/logic/EvidenceMapper.java
package logic;

import java.io.*;
import java.util.*;

public class EvidenceMapper {

    public static class TherapyEntry {
        public final String therapyCode;
        public final String therapyName;
        public final String evidenceLevel;
        public final String description;

        public TherapyEntry(String therapyCode, String therapyName, String evidenceLevel, String description) {
            this.therapyCode = therapyCode;
            this.therapyName = therapyName;
            this.evidenceLevel = evidenceLevel;
            this.description = description;
        }

        @Override
        public String toString() {
            return therapyCode + " - " + therapyName + " (" + evidenceLevel + "): " + description;
        }
    }

    private final Map<String, List<TherapyEntry>> therapyMap = new HashMap<>();

    public EvidenceMapper(String csvPath) {
        loadTherapyMatrix(csvPath);
    }

    private void loadTherapyMatrix(String csvPath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvPath))) {
            String line;
            reader.readLine(); // skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length >= 5) {
                    String domain = parts[0].trim().toLowerCase();
                    TherapyEntry entry = new TherapyEntry(
                        parts[1].trim(),
                        parts[2].trim(),
                        parts[3].trim(),
                        parts[4].trim()
                    );
                    therapyMap.computeIfAbsent(domain, k -> new ArrayList<>()).add(entry);
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to load therapy matrix: " + e.getMessage());
        }
    }

    public List<TherapyEntry> getTherapiesForDomain(String domain) {
        return therapyMap.getOrDefault(domain.toLowerCase(), Collections.emptyList());
    }

    public Map<String, List<TherapyEntry>> suggestTherapies(Set<String> activeDomains) {
        Map<String, List<TherapyEntry>> result = new HashMap<>();
        for (String domain : activeDomains) {
            List<TherapyEntry> entries = getTherapiesForDomain(domain);
            result.put(domain, entries);
        }
        return result;
    }
}
