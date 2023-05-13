package Advertisements;
class PrintedAdvertisement extends Advertisement{
    private int words; // Extra characteristic
    public PrintedAdvertisement(String typeCode, String productCode, int durationInDays, String details, int words){
        super(typeCode, productCode, durationInDays, details);
        this.words = words;
    }

    //Getters
    public int getWords(){
        return getExtraCharacteristic();
    }   

    public int getExtraCharacteristic()
    {
        return words;
    }
}