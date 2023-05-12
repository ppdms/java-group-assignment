package Advertisements;
class RadioTVAdvertisement extends Advertisement{
    private int duration; //in seconds

    public RadioTVAdvertisement(String typeCode, String productCode, int durationInDays, String details, int duration){
        super(typeCode, productCode, durationInDays, details);
        this.duration = duration;
    }
    
    //Getters
    public int getDuration(){
        return duration;
    }

    public int getExtraCharacteristic()
    {
        return duration;
    }
}