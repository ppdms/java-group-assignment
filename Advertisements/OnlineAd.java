package Advertisements;
class OnlineAdvertisement extends Advertisement{
    boolean autoShow;

    public OnlineAdvertisement(String typeCode, String productCode, int durationInDays, String details, boolean autoShow){
        super(typeCode, productCode, durationInDays, details);
        this.autoShow = autoShow;
    }
    
    //Getters
    boolean getAutoShow() {
        return autoShow;
    }
}
