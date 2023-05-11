package Advertisments;
class OnlineAdvertisment extends Advertisment{
    String autoShow; //Logika 8elei boolean

    public OnlineAdvertisment(String typeCode, String productCode, int durationInDays, String details, String autoShow){
        super(typeCode, productCode, durationInDays, details);
        this.autoShow = autoShow;
    }
    
    //Getters
    String getAutoShow() {
        return autoShow;
    }
}
