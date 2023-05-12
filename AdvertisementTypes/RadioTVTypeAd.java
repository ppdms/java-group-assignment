package AdvertisementTypes;
class RadioTVTypeAd extends AdvertisementType{
    private int euroPsMorning; //Price persecond in the morning
    private int euroPsNoon; //Same for the noon
    private int euroPsAfternoon; // Same for the afternoon
    private int euroPsEvening; //Same for the evening

    public RadioTVTypeAd(String adCode, String description, String agencyTIN, int euroPsMorning, int euroPsNoon,
    int euroPsAfternoon, int euroPsEvening){
        super(adCode, description, agencyTIN);
        this.euroPsMorning = euroPsMorning;
        this.euroPsNoon = euroPsNoon;
        this.euroPsAfternoon = euroPsAfternoon;
        this.euroPsEvening = euroPsEvening;
    }

    //Getters
    int getEuroPsMorning(){
        return euroPsMorning;
    }
    int getEuroPsNoon(){
        return euroPsNoon;
    }
    int getEuroPsAfternoon(){
        return euroPsAfternoon;
    }
    int getEuroPsEvening(){
        return euroPsEvening;
    }
}
