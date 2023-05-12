package Advertisements;
class OnlineAdvertisement extends Advertisement{
    private int autoShow;

    public OnlineAdvertisement(String typeCode, String productCode, int durationInDays, String details, int autoShow){
        super(typeCode, productCode, durationInDays, details);
        this.autoShow = autoShow;
    }
    
    //Getters
    public int getAutoShow() {
        return autoShow;
    }
}
