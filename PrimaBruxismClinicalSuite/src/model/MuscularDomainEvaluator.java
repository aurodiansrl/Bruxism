package model;

public class MuscularDomainEvaluator {

    public double evaluateMuscularFTS(PatientProfile profile) {
        double muscularScore = profile.getMuscleTensionLevel() * 0.6 +
                               profile.getPalpationResponse() * 0.4;
        return muscularScore;
    }
}