package logic;

import model.PatientProfile;

public class TherapyPlanner {

    public static void generate(PatientProfile profile, Object risk) {
        System.out.println("Generating therapy plan for: " + profile.getFullName());
    }

    public static class DomainInteraction {
        public String primary;
        public String secondary;
        public Object relation;
        public String action;

        public DomainInteraction(String primary, String secondary) {
            this.primary = primary;
            this.secondary = secondary;
        }
    }

    public class TherapyOption {

        public String therapy;
        public String domain;
    }
}
