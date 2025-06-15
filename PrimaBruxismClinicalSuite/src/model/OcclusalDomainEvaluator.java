package model;

public class OcclusalDomainEvaluator {

    public double evaluateOcclusalFTS(PatientProfile profile) {
        return profile.getOcclusalInstabilityIndex();
    }
}