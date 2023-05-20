package Ads;
public class OnlineAd extends Ad{
    private int autoShow;

    public OnlineAd(String UID, String typeCode, String productCode, int durationInDays, String details, int autoShow){
        super(UID, typeCode, productCode, durationInDays, details);
        this.autoShow = autoShow;
    }
    
    //Getters
    public int getAutoShow() {
        return getExtraCharacteristic();
    }

    public int getExtraCharacteristic() {
        return autoShow;
    }
}
