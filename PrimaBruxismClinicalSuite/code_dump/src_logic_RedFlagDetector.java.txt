// src/logic/RedFlagDetector.java
package logic;

import java.io.*;
import java.util.*;

public class RedFlagDetector {

    public static class RedFlag {
        public final String code;
        public final String field;
        public final String value;
        public final String message;
        public final String severity;

        public RedFlag(String code, String field, String value, String message, String severity) {
            this.code = code;
            this.field = field;
            this.value = value;
            this.message = message;
            this.severity = severity;
        }

        @Override
        public String toString() {
            return "[" + severity + "] " + code + " – " + message;
        }
    }

    private final List<RedFlag> redFlags = new ArrayList<>();

    public RedFlagDetector(String csvPath) {
        loadRedFlags(csvPath);
    }

    private void loadRedFlags(String csvPath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvPath))) {
            String line;
            reader.readLine(); // skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length >= 5) {
                    redFlags.add(new RedFlag(
                        parts[0].trim(), // Code
                        parts[1].trim(), // TriggerField
                        parts[2].trim(), // TriggerValue
                        parts[3].trim(), // Message
                        parts[4].trim()  // Severity
                    ));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading red flags: " + e.getMessage());
        }
    }

    public List<RedFlag> detect(Map<String, String> patientData) {
        List<RedFlag> triggered = new ArrayList<>();
        for (RedFlag rf : redFlags) {
            String value = patientData.get(rf.field);
            if (value != null && value.equalsIgnoreCase(rf.value)) {
                triggered.add(rf);
            }
        }
        return triggered;
    }
}
