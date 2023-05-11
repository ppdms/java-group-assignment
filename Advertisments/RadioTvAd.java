package Advertisments;
class RadioTVAdvertisment extends Advertisment{
    private int duration; //in seconds

    public RadioTVAdvertisment(String typeCode, String productCode, int durationInDays, String details, int duration){
        super(typeCode, productCode, durationInDays, details);
        this.duration = duration;
    }
    
    //Getters
    int getDuration(){
        return duration;
    }
}