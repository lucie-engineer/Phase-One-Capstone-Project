package University;

public class GraduateStudent extends Student {
    private int credits;
    private int creditCost = 1000;
    private double researchFee = 2000.0;

    public int getCredits(){ return credits; }
    public int getCreditCost(){ return creditCost; }
    public double getResearchFee(){ return researchFee; }

    public void setCredits(int credits){
        this.credits = credits;
    }

    public void setResearchFee(double researchFee){
        this.researchFee = researchFee;
    }

    @Override
    public double calculateTuition(){
        return (credits * creditCost) + researchFee;
    }
}
