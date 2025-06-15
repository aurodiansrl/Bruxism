// File: test/TestFTS.java

package test;

import model.FTSResult;
import model.PatientProfile;
import logic.FTSCalculator;

public class TestFTS {

    public static void main(String[] args) {
        // Simula un paziente con punteggi predefiniti per ogni dominio
        PatientProfile patient = new PatientProfile("TEST_PATIENT_001");
        patient.addAttribute("FTS_Muscular", "30");      // su max 40
        patient.addAttribute("FTS_Articular", "10");     // su max 20
        patient.addAttribute("FTS_Occlusal", "25");      // su max 40
        patient.addAttribute("FTS_Psychological", "28"); // su max 35

        // Esegui calcolo FTS
        FTSResult result = FTSCalculator.computeFTS(patient);

        // Stampa risultati
        System.out.println("=== TEST FTS RESULT ===");
        System.out.println("Muscular:       " + result.getMuscularScore() + " → " + result.getMuscularLabel());
        System.out.println("Articular:      " + result.getArticularScore() + " → " + result.getArticularLabel());
        System.out.println("Occlusal:       " + result.getOcclusalScore() + " → " + result.getOcclusalLabel());
        System.out.println("Psychological:  " + result.getPsychologicalScore() + " → " + result.getPsychologicalLabel());

        // Verifica automatica (esempi)
        assert result.getMuscularLabel().equals("Possible");
        assert result.getArticularLabel().equals("Unlikely");
        assert result.getOcclusalLabel().equals("Possible");
        assert result.getPsychologicalLabel().equals("Probable");

        System.out.println("Test completato con successo.");
    }
}
