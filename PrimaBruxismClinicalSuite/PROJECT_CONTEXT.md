# PrimaBruxismClinicalSuite – PROJECT_CONTEXT.md

## 🔷 Overview
`PrimaBruxismClinicalSuite` è un software clinico progettato in **Java (con GUI JavaFX)** per implementare il framework decisionale PRIMA, sviluppato e validato in un contesto scientifico multidisciplinare per la diagnosi e il trattamento del bruxismo.

Il software consente:
- l’integrazione dei dati clinici raccolti tramite STAB (Axis A),
- l’analisi funzionale tramite FTS (Functional Tendency Score),
- la selezione logica degli strumenti diagnostici (ex-Axis B),
- l’interpretazione tramite soglie cliniche validate,
- la definizione di strategie terapeutiche con sequenze intra-dominio (ITSL),
- l’integrazione del contesto aggravante tramite Aggravating Factor Index Stratification (AFI).

## 🔮 Future Features – Planned Modules (v2.0 Roadmap)

The current implementation of PrimaBruxismClinicalSuite focuses on core functionality, logic reproducibility, and clinical usability. However, to enhance its clinical utility, scalability, and scientific robustness, the following modules are planned for version 2.0.

🔐 1. Audit & Change Tracking Module
Function: Automatically logs all clinical, diagnostic, and therapeutic modifications.
Format: Timestamped entries, linked to patient IDs.
Export: Encrypted .log or .json.
Purpose: Clinical traceability (GDPR-compliant), reproducibility for research, medico-legal security.
🧪 2. Simulation Mode (Academic Use)
Function: Launches the software with preloaded simulated patients from Appendix 5.
Use Case: Training, lectures, publication demos.
Flag: SIMULATION_MODE = TRUE on export to avoid confusion with real data.
📦 3. Full Case Export/Import Engine
Export: Entire patient workflow (JSON profile, .csv tables, FTS/AFI history, diagnostic outcomes).
Import: Restores a complete case in one step.
Purpose: Multicenter collaboration, backup, study submission.
📊 4. FTS/AFI Visualization Dashboard
Charts: Radar plot (4-domain FTS), longitudinal bar graphs (AFI over time).
Tool: JavaFX charts, exportable to .png or .pdf.
🧬 5. Risk Classification Layer (RCL)
Inputs: FTS + AFI + Diagnostic thresholds.
Output: Qualitative tag (e.g., “Low-Risk Monodomain”, “High-Risk Multifactorial”).
Purpose: Enhance clinical anticipation and publication-level profiling.
📚 6. Evidence Reference Engine
Function: Links every proposed diagnostic or therapeutic action to PubMed references.
Access: Side-panel citation preview or tooltip on action.
Source: Static mapping file (reference_map.csv).
🌐 7. Multi-language Support
Structure: Java ResourceBundle system.
Languages: EN (default), IT, PT-BR, ES (planned).
Translation files: lang_en.properties, lang_it.properties, etc.
💾 8. Auto-Backup System
Interval: Optional backup every X min or every major change.
Format: .zip with timestamped versioning.
Destination: Local or cloud-mountable path.
🧠 9. Clinical Red Flags Detector
Function: Alerts user when critical combinations (e.g., FTS > 90% + AFI > 0.8) are detected.
Output: Visual warning with rationale + suggestion for escalation.
🧾 10. Scientific Submission Checklist
Purpose: Assists users in preparing PRIMA-based case reports or studies.
Checklist Includes:
All csv files loaded?
All thresholds applied?
Export complete?
Output: Print-ready checklist summary.
---

## 🔁 Regola d’uso fondamentale

> ✅ Questo file **deve essere caricato all’inizio di ogni nuova sessione**, e **aggiornato alla fine della sessione stessa**.
> Serve a garantire continuità del contesto e tracciare lo stato del progetto (clinico + software).

---

## 📁 Struttura Progetto

```
PrimaBruxismClinicalSuite/
├── src/
├── src/
│   ├── StabExcelParser.java
│   ├── model/
│   │   ├── PatientProfile.java
│   │   ├── RiskProfile.java
│   │   ├── FTSResult.java
│   │   ├── DiagnosticSuggestion.java
│   │   └── PatientTreatmentPlan.java
│   ├── logic/
│   │   ├── FTSCalculator.java
│   │   ├── AFICalculator.java
│   │   ├── AFIInterpreter.java
│   │   ├── AFIStratification.java
│   │   ├── DiagnosticEngine.java
│   │   ├── EvidenceMapper.java
│   │   ├── ITSLSequencer.java
│   │   ├── RedFlagDetector.java
│   │   ├── TherapyPlanner.java
│   │   ├── TherapyConflictResolver.java
│   │   ├── PatientTreatmentPlan.java
│   │   └── SimulationController.java
│   ├── ui/
│   │   ├── AFIGraphPanel.java
│   │   ├── FTSVisualizer.java
│   │   ├── LanguageSwitcher.java
│   │   ├── MainDashboard.java
│   │   ├── RiskDisplay.java
│   │   └── SimulationController.java
│   ├── util/
│   │   ├── CSVParser.java
│   │   ├── FileManager.java
│   │   ├── ExportManager.java
│   │   ├── AutoBackupHandler.java
│   │   ├── GDPRLogger.java
│   │   └── SimulatedCaseManager.java
│   │   └── CSVWriterHelper.java
│   └── test/
│       ├── AllTests.java
│       ├── TestFTS.java
│       ├── logic/
│           ├── FTSCalculatorTest.java
│       ├── model/
│           ├── PatientProfileTest.java
│       ├── ui/
│           ├── RiskDisplayTest.java
│       ├── util/
│           └── CSVParserTest.java
├── patients/                     # 🔹 Per-patient structured data
│   ├── patient_0001/
│   │   ├── profile.json
│   │   ├── audit_log.txt
│   │   ├── therapy_report.pdf
│   │   ├── chart_fts_afi.png
│   │   └── raw_inputs.csv
│   ├── patient_0002/
│   │   └── ...
├── data/                         # Sample csvs, thresholds, mappings
│   ├── Appendix_1_-_FTS_Justification.csv
│   ├── Appendix_2_-_Activation_Threshold_Logic.csv
│   ├── Appendix_3_-_Cross-Domain_Therapeutic_Logic.csv
│   ├── Appendix_5_-_Simulated_Cases.csv
│   ├── Table_2_Instrument_Mapping.csv
│   ├── Table_3_Clinical_Thresholds.csv
│   ├── Table_4_Therapy_Matrix OK.csv
│   ├── Table_5_Domain_Interaction_Matrix ok.csv
│   ├── Table_AFI_Stratification.csv
│   ├── Table_Clinical_Thresholds OK.csv
│   ├── Appendix 1 – Functional Tendency Score (FTS) Calculation and Justification.docx`
│   ├── Appendix 5 - Conceptual Clinical Scenarios.xlsx`
│   ├── Appendix 7 - Evidence-Based Therapeutic Strategies (Optional Clinical Integration).docx`
│   ├── Appendix 9 – Aggravating Factor Stratification.docx`
│   ├── Appendix Table A1 - PATIENT ANAMNESIS.xlsx`
│   ├── Appendix Table A2 - SLEEP-AWAKE.xlsx`
│   ├── Appendix Table A3 - PATIENT COMPLAIN.xlsx`
│   ├── Appendix Table A4 - Clinically based examiner assessment.xlsx`
│   ├── Table 2 - Instrument Mapping by Functional Domain.csv`
│   ├── Table 3 - Clinical Threshold Matrix.csv`
│   ├── Table 4 – Domain Therapy Mapping Matrix.csv`
│   ├── Table 5 – Therapeutic Interaction Matrix.csv`
│   ├── Table_AFI_Stratification.csv`
│   └── reference_map.csv
│   ├── STAB_full_input.csv`
├── resources/                    # Styles, images, languages
│   ├── lang_en.properties
│   ├── lang_it.properties
│   ├── lang_es.properties
│   └── style.css
├── logs/
│   └── audit_log_2025-05-31.log
├── backup/
│   └── session_2025-05-31_1245.zip
├── README.md
├── PROJECT_CONTEXT.md
└── PrimaBruxismLauncher.java

```

---

## 🧠 Logica Clinica del PRIMA Model

### 🔐 Step 0 – Patient Profile & Data Privacy Layer

Ogni sessione clinica comincia con la creazione/selezione di un profilo paziente:

- ID pseudonimizzato (`PRIMA-ID-000123`)
- Età, sesso, note cliniche (facoltativi)
- Flag consenso informato
- Container locale per dati CSV e log sessione

**GDPR Compliance**:
- Dati sempre pseudonimizzati
- Nessuna trasmissione remota
- Audit trail e flag consenso
- Accesso solo locale

---

### Step 1 – Raccolta dati da STAB (Appendix Tables A1–A4)
Il clinico importa i dati STAB da quattro file CSV:
- A1 – Anamnesi generale (comorbidità, farmaci)
- A2 – Classificazione sleep/awake bruxism
- A3 – Sintomi riferiti
- A4 – Esame obiettivo

Questi item sono già categorizzati nei 4 domini + aggravating factor flag.

Output: CSV unificato `STAB_full_input.csv`

---

### Step 2 – Calcolo FTS (Functional Tendency Score)

- Gli item STAB sono classificati in 4 domini funzionali.
- Il punteggio di ogni dominio è normalizzato sul massimo teorico.
- Viene assegnata un’etichetta qualitativa:
  - 0–25% → *Unlikely*
  - 26–65% → *Possible*
  - >65% → *Probable*

Output: file `FTS_output.json` contenente 4 valori FTS e label associate.

---

### Step 3 – Mappatura Diagnostica Suggerita

- FTS ≥ *Possible* → attiva suggerimenti diagnostici (Table 2)
- Il medico può confermare, modificare o aggiungere strumenti
- Input successivo: risultati strumentali (.csv)

---

### Step 4 – Interpretazione soglie cliniche
- Il software confronta i risultati con soglie da Table 3.
- Attivazione se:
  - Score totale ≥ 13/15
  - Almeno 60% del punteggio da parametri "core diagnostic"

Solo se superate, si attiva la fase terapeutica.

---

### Step 5 – Calcolo AFI + Stratificazione
- Il punteggio AFI è calcolato come:
  ```
  AFI = (Sum_AF / Max_AF) × 100
  ```
- Il valore AFI è convertito in fascia:
  - 0–25% → *Low*
  - 26–65% → *Moderate*
  - >65% → *High*

- Ogni item aggravante è anche classificato secondo categorie cliniche:
  - COMORBIDITIES
  - GENETIC_HISTORY
  - BEHAVIORAL_DAY
  - BEHAVIORAL_NIGHT
  - SLEEP_QUALITY
  - ENVIRONMENTAL_STRESS
  - UNCLASSIFIED_SYSTEMIC

- Il modulo `AFIInterpreter` restituisce:
  - Spiegazione clinica
  - Impatto terapeutico
  - Strategia suggerita
  - Output visibile in GUI + export in PDF report

---

### Step 6 – Intra-Domain Therapeutic Sequencing (ITSL)

- Sequenza a 3 fasi (Initiation, Escalation, Optimization)
- Modello adattivo basato su:
  - Intensità FTS
  - Risposte Axis B
  - Principio temporale-invasivo
- Codificata nella `TherapyPlannerExt.java`

Riferimento: Appendix 8 – Therapeutic Sequencing Logic

---

### Step 7 – Aggravating Factor Index (AFI) Stratification

- Gli item STAB non assegnati a domini sono etichettati come **Aggravating Factors** (AF).
- Sono organizzati in 6 sotto-contesti:
  - Familial/Genetic
  - Sleep-Related
  - Awake Behavior
  - Daytime Comorbidities
  - Nighttime Comorbidities
  - Miscellaneous Risk

Ogni subscore è espresso in %, normalizzato.

- Output: `AFI_Stratified.csv` + `AFI_Totale.json`
- Il livello AFI stratificato influisce su ITSL:
AFI High (>65%): anticipa necessità di escalation precoce
AFI Moderate (35–65%): accorcia finestra di rivalutazione
AFI Low (<35%): ITSL standard
Riferimento: Appendix 9 – AFI Stratification and Impact

---

### Step 7 – Iterazione e follow-up
- Funzioni previste:
  - Aggiornamento dati nel tempo
  - Recalcolo dinamico
  - Visualizzazione trend FTS/AFI
  - Esportazione report clinico
  - Inserimento nuovi STAB
  - Ricalcolo FTS e AFI
  - Visualizzazione andamento nel tempo (grafico FTS per dominio)
  - Cronologia completa nella cartella `patients/PRIMA-ID-xxxx/`

---

## 📊 File e Tabelle Richieste

### Obbligatorie (da caricare a ogni sessione):
- `PROJECT_CONTEXT.md`
- `STAB_full_input.csv`
- `Appendix Table A1 - PATIENT ANAMNESIS.csv`
- `Appendix Table A2 - SLEEP-AWAKE.csv`
- `Appendix Table A3 - PATIENT COMPLAIN.csv`
- `Appendix Table A4 - Clinically based examiner assessment.csv`
- `Table 2 - Instrument Mapping by Functional Domain.csv`
- `Table 3 - Clinical Threshold Matrix.csv`
- `Table 4 – Domain Therapy Mapping Matrix.csv`
- `Table 5 – Therapeutic Interaction Matrix.csv`
- `Table_AFI_Stratification.csv`

### Supplementari opzionali:
- `Appendix 1 – Functional Tendency Score (FTS) Calculation and Justification.docx`
- `Appendix 5 - Conceptual Clinical Scenarios.xlsx`
- `Appendix 7 - Evidence-Based Therapeutic Strategies (Optional Clinical Integration).docx`
- `Appendix 9 – Aggravating Factor Stratification.docx`


---

## ✅ Stato Corrente del Progetto

### Completati:
- Architettura clinica e software
- Architettura software
- Privacy framework GDPR
- Specifiche cliniche
- Motori FTS, AFI, ITSL
- Moduli logici: FTSCalculator, IFAInterpreter, TherapyPlannerExt
- Tabelle cliniche validate
- Export CSV e PDF funzionante

### Da sviluppare:
- [ ] UI inserimento dati
- [ ] Diagnostica Instrumental tools
- [ ] Salvataggio sessione, esportazione
- GUI JavaFX (Sessione 2)
- Parser CSV + Import Live
- Pannello AFI-Stratification interattivo
- Report clinico finale
- Packaging ZIP per sottomissione

---

## ℹ️ Note operative

- Questo file è obbligatorio per il ripristino del contesto.
- Tutti i moduli Java sono standard, no dipendenze esterne.
- La GUI segue uno schema tab-based:
  - Tab 1: Patient Input
  - Tab 2: FTS Results
  - Tab 3: Instruments
  - Tab 4: AFI & Stratification
  - Tab 5: Therapy Plan

---

> Versione: `v1.2 – Full Logic + IFA Stratification + Export CSV`
> Ultima modifica: 2025-05-31 – GPT-4
