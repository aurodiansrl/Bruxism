// src/logic/AFIStratification.java
package logic;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Calcola lo score AFI (Aggravating Factor Index) per dominio funzionale,
 * basato su item STAB selezionati e punteggi individuali.
 */
public class AFIStratification {

    public enum SeverityLabel {
        LOW, MODERATE, HIGH
    }

    // Soglie standard per stratificazione (possono essere caricate dinamicamente)
    private static final Map<String, int[]> DOMAIN_THRESHOLDS = Map.of(
        "muscular", new int[]{2, 5},      // [LOW < 2, MODERATE 2–5, HIGH > 5]
        "articular", new int[]{2, 4},
        "occlusal", new int[]{1, 3},
        "psychological", new int[]{3, 6}
    );

    /**
     * Calcola i punteggi AFI per ciascun dominio in base ai punteggi degli item aggravanti.
     * @param domainToItemScores mappa da dominio → lista di punteggi da item aggravanti
     * @return mappa da dominio → [totalScore, severityLabel]
     */
    public static Map<String, AFIResult> computeAFI(Map<String, List<Integer>> domainToItemScores) {
        Map<String, AFIResult> result = new HashMap<>();

        for (Map.Entry<String, List<Integer>> entry : domainToItemScores.entrySet()) {
            String domain = entry.getKey();
            List<Integer> scores = entry.getValue();
            int total = scores.stream().mapToInt(Integer::intValue).sum();

            SeverityLabel label = stratifySeverity(domain, total);
            result.put(domain, new AFIResult(total, label));
        }

        return result;
    }

    /**
     * Classifica lo score AFI secondo soglie predefinite.
     */
    private static SeverityLabel stratifySeverity(String domain, int totalScore) {
        int[] thresholds = DOMAIN_THRESHOLDS.get(domain.toLowerCase());

        if (totalScore <= thresholds[0]) return SeverityLabel.LOW;
        if (totalScore <= thresholds[1]) return SeverityLabel.MODERATE;
        return SeverityLabel.HIGH;
    }

    // Classe di ritorno per AFI
    public static class AFIResult {
        public final int score;
        public final SeverityLabel severity;

        public AFIResult(int score, SeverityLabel severity) {
            this.score = score;
            this.severity = severity;
        }

        @Override
        public String toString() {
            return String.format("Score: %d, Severity: %s", score, severity);
        }
    }
}
