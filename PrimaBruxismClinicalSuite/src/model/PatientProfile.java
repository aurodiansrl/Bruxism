package model;

import java.util.Map;

public class PatientProfile {
    private int id;
    private String name;
    private double age;

    public int getId() {
        return id;
    }

    // Campi aggiuntivi che servono ad altri file
    private double muscleTensionLevel;
    private double palpationResponse;
    private double jointNoiseLevel;
    private double rangeOfMotionLimit;
    private double occlusalInstabilityIndex;
    private double stressScore;
    private double sleepQualityIndex;

    private boolean someFlag;

    public boolean isSomeFlag() {
        return someFlag;
    }

    public PatientProfile() {
        // Costruttore vuoto richiesto da alcune classi
    }

    public PatientProfile(int id, String name, double age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
// Add this method if it does not exist
public int getBehavioralFlags() {
    // TODO: Implement logic to return behavioral flags
    return 0;
}
    // Costruttore completo richiesto da Main.java
    public PatientProfile(int id, String name, double muscleTensionLevel,
                          double palpationResponse, double jointNoiseLevel,
                          double rangeOfMotionLimit, double occlusalInstabilityIndex,
                          double stressScore, double sleepQualityIndex,
                          int someInt, boolean someFlag) {
        this.id = id;
        this.name = name;
        this.muscleTensionLevel = muscleTensionLevel;
        this.palpationResponse = palpationResponse;
        this.jointNoiseLevel = jointNoiseLevel;
        this.rangeOfMotionLimit = rangeOfMotionLimit;
        this.occlusalInstabilityIndex = occlusalInstabilityIndex;
        this.stressScore = stressScore;
        this.sleepQualityIndex = sleepQualityIndex;
        this.someFlag = someFlag;
    }

    public String getFullName() {
        return name;
    }

    public double getAge() {
        return age;
    }

    public double getMuscleTensionLevel() {
        return muscleTensionLevel;
    }

    public double getPalpationResponse() {
        return palpationResponse;
    }

    public double getJointNoiseLevel() {
        return jointNoiseLevel;
    }

    public double getRangeOfMotionLimit() {
        return rangeOfMotionLimit;
    }

    public double getOcclusalInstabilityIndex() {
        return occlusalInstabilityIndex;
    }

    public double getStressScore() {
        return stressScore;
    }

    public double getSleepQualityIndex() {
        return sleepQualityIndex;
    }

    public Object getActiveAFICategories() {
        // Placeholder, dovrebbe essere una lista
        return java.util.Collections.emptyList();
    }

    public Object getTreatmentPlan() {
        return null; // Placeholder
    }

    public void setAttribute(String key, String value) {
        // Mock per supportare CSVParser
    }

    public Map<String, String> toMap() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toMap'");
    }

    public int getSystemicFlags() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSystemicFlags'");
    }
}
