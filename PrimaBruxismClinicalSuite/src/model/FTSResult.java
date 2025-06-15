package model;

public class FTSResult {
    private double muscularScore;
    private double articularScore;
    private double occlusalScore;
    private double psychologicalScore;

    public FTSResult(double muscular, double articular, double occlusal, double psychological) {
        this.muscularScore = muscular;
        this.articularScore = articular;
        this.occlusalScore = occlusal;
        this.psychologicalScore = psychological;
    }

    public double getMuscularScore() {
        return muscularScore;
    }

    public double getArticularScore() {
        return articularScore;
    }

    public double getOcclusalScore() {
        return occlusalScore;
    }

    public double getPsychologicalScore() {
        return psychologicalScore;
    }
}
