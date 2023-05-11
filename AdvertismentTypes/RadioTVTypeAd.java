package AdvertismentTypes;
class RadioTVTypeAd extends AdvertismentType{
    private int euroPsMorning; //Price persecond in the morning
    private int euroPsNoon; //Same for the noon
    private int euroPsAfternoon; // Same for the afternoon
    private int euroPsEvening; //Same for the evening

    public RadioTVTypeAd(String adCode, String description, String agencieAFM, int euroPsMorning, int euroPsNoon,
    int euroPsAfternoon, int euroPsEvening){
        super(adCode, description, agencieAFM);
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
