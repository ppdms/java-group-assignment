package AdvertismentTypes;
class PrintedTypeAd extends AdvertismentType{
    private int euroPwFirst; //Price per word on the first page
    private int euroPwMiddle; //Same for the middle
    private int euroPwLast;  //Same for the last page

    public PrintedTypeAd(String adCode, String description, String agencieAFM, int euroPwFirst, int euroPwMiddle, int euroPwLast){
        super(adCode, description, agencieAFM);
        this.euroPwFirst = euroPwFirst;
        this.euroPwMiddle = euroPwMiddle;
        this.euroPwLast = euroPwLast;
    }

    //Getters
    int getEuroPwFirst(){
        return euroPwFirst;
    }
    int getEuroPwMiddle(){
        return euroPwMiddle;
    }
    int getEuroPwLast(){
        return euroPwLast;
    }
}