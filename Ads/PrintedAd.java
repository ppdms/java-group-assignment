package Ads;
public class PrintedAd extends Ad{
    private int words; // Extra characteristic
    public PrintedAd(String UID, String typeCode, String productCode, int durationInDays, String details, int words){
        super(UID, typeCode, productCode, durationInDays, details);
        this.words = words;
    }

    //Getters
    public int getWords(){
        return getExtraCharacteristic();
    }   

    public int getExtraCharacteristic() {
        return words;
    }
}