package model;
import java.util.List;
import java.util.Map;

import logic.RedFlagDetector.RedFlag;

public class RiskProfile {
    private Map<String, Double> riskScores;

    public Map<String, Double> getRiskScores() {
        return riskScores;
    }


    public String getAfiLevel() {
        return "Moderate";
    }

    public void setRedFlags(List<RedFlag> detect) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setRedFlags'");
    }
}
