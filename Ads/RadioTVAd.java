package Ads;
public class RadioTVAd extends Ad{
    private int durationSeconds; // Extra characteristic

    public RadioTVAd(String UID, String typeCode, String productCode, int durationInDays, String details, int durationSeconds){
        super(UID, typeCode, productCode, durationInDays, details);
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