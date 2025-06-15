package logic;

public class AFILogicModulator {

    public String adjustStrategy(int afi) {
        if (afi > 65) return "Anticipatory escalation";
        else if (afi > 25) return "Readiness for early Phase 2";
        else return "Standard ITSL";
    }
}