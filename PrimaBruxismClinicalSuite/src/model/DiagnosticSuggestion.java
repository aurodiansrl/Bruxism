// src/logic/DiagnosticSuggestion.java
package model;

import java.util.*;

/**
 * Classe che incapsula i suggerimenti diagnostici specifici per ciascun dominio funzionale.
 * Utilizzata dal motore diagnostico per proporre gli strumenti ex-Axis B.
 */
public class DiagnosticSuggestion {

    private final Map<String, List<String>> domainToSuggestedTests = new HashMap<>();

    /**
     * Inizializza i suggerimenti diagnostici statici (può essere esteso per lettura dinamica da CSV).
     */
    public DiagnosticSuggestion() {
        domainToSuggestedTests.put("muscular", Arrays.asList(
            "Palpazione muscolare",
            "EMG di superficie",
            "Misura della forza massima"
        ));

        domainToSuggestedTests.put("articular", Arrays.asList(
            "RMN articolare",
            "CBCT articolare",
            "Misurazione del range di apertura"
        ));

        domainToSuggestedTests.put("occlusal", Arrays.asList(
            "Analisi digitale dell’occlusione",
            "Articolatore virtuale",
            "Analisi cinematica mandibolare"
        ));

        domainToSuggestedTests.put("psychological", Arrays.asList(
            "Scala DASS-21",
            "Colloquio clinico psicologico",
            "Somministrazione PSQI o ISI"
        ));
    }

    /**
     * Restituisce gli strumenti diagnostici suggeriti per il dominio.
     * @param domain Nome del dominio (es. "muscular", "articular"...)
     * @return Lista di strumenti suggeriti, oppure lista vuota se non trovati
     */
    public List<String> getSuggestionsForDomain(String domain) {
        return domainToSuggestedTests.getOrDefault(domain.toLowerCase(), Collections.emptyList());
    }

    /**
     * Ritorna una mappa intera dei suggerimenti disponibili.
     */
    public Map<String, List<String>> getAllSuggestions() {
        return Collections.unmodifiableMap(domainToSuggestedTests);
    }

    /**
     * Aggiunge un suggerimento personalizzato a un dominio.
     * @param domain Dominio funzionale
     * @param suggestion Nuovo strumento diagnostico da aggiungere
     */
    public void addCustomSuggestion(String domain, String suggestion) {
        domainToSuggestedTests
            .computeIfAbsent(domain.toLowerCase(), k -> new ArrayList<>())
            .add(suggestion);
    }
}
