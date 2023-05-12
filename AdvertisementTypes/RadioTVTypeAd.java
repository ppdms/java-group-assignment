package AdvertisementTypes;
class RadioTVTypeAd extends AdvertisementType{

    public static final String MORNING = "0";
    public static final String NOON = "1";
    public static final String AFTERNOON = "2";
    public static final String EVENING = "3";

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

    public float Cost(String dayTime , int adDuration, int numDaysPlayed)
    {
        int pricePs;

        switch(dayTime)
        {
            case RadioTVTypeAd.MORNING:
                pricePs = euroPsMorning;
                break;
            case RadioTVTypeAd.NOON:
                pricePs = euroPsNoon;
                break;
            case RadioTVTypeAd.AFTERNOON:
                pricePs = euroPsAfternoon;
                break;
            case RadioTVTypeAd.EVENING:
                pricePs = euroPsEvening;
                break;
            default:
                System.out.println("ERROR: WRONG dayTime passed to Cost function!");
                pricePs = 0;
        }

        return adDuration * pricePs * numDaysPlayed;
    }

    //Getters
    public int getEuroPsMorning(){
        return euroPsMorning;
    }
    public int getEuroPsNoon(){
        return euroPsNoon;
    }
    public int getEuroPsAfternoon(){
        return euroPsAfternoon;
    }
    public int getEuroPsEvening(){
        return euroPsEvening;
    }
}
