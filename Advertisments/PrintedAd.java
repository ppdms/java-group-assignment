package Advertisments;
class PrintedAdvertisment extends Advertisment{
    private int words;
    public PrintedAdvertisment(String typeCode, String productCode, int durationInDays, String details, int words){
        super(typeCode, productCode, durationInDays, details);
        this.words = words;
    }

    //Getters
    int getWords(){
        return words;
    }   
}