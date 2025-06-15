package util;

public class JsonExporter {

    public String export(PatientProfile profile) {
        return "{\"name\": \"" + profile.getName() + "\"}";
    }
}