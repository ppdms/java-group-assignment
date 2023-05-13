package Advertisements;
public class RadioTvAd extends Advertisement{
    private int durationSeconds; // Extra characteristic

    public RadioTvAd(String typeCode, String productCode, int durationInDays, String details, int durationSeconds){
        super(typeCode, productCode, durationInDays, details);
        this.durationSeconds = durationSeconds;
    }
    
    //Getters
    public int getDuration(){
        return getExtraCharacteristic();
    }

    public int getExtraCharacteristic() {
        return durationSeconds;
    }
}