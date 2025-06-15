package model;

public class PsychologicalDomainEvaluator {

    public double evaluatePsychologicalFTS(PatientProfile profile) {
        double psychScore = profile.getStressScore() * 0.5 +
                            profile.getSleepQualityIndex() * 0.5;
        return psychScore;
    }
}