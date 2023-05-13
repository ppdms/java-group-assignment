package Advertisements;
public class OnlineAd extends Advertisement{
    private int autoShow;

    public OnlineAd(String typeCode, String productCode, int durationInDays, String details, int autoShow){
        super(typeCode, productCode, durationInDays, details);
        this.autoShow = autoShow;
    }
    
    //Getters
    public int getAutoShow() {
        return getExtraCharacteristic();
    }

    public int getExtraCharacteristic()
    {
        return autoShow;
    }
}
