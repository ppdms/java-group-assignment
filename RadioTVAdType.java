public class RadioTVAdType extends AdType{

    public static final int TYPE = RADIO_TV;

    public static final String MORNING = "Morning";
    public static final String NOON = "Noon";
    public static final String AFTERNOON = "Afternoon";
    public static final String EVENING = "Evening";

    private int euroPsMorning; //Price per second in the morning
    private int euroPsNoon; //Same for the noon
    private int euroPsAfternoon; // Same for the afternoon
    private int euroPsEvening; //Same for the evening

    public RadioTVAdType(String adCode, String description, String agencyTIN, int euroPsMorning, int euroPsNoon,
    int euroPsAfternoon, int euroPsEvening){
        super(adCode, description, agencyTIN);
        this.euroPsMorning = euroPsMorning;
        this.euroPsNoon = euroPsNoon;
        this.euroPsAfternoon = euroPsAfternoon;
        this.euroPsEvening = euroPsEvening;
    }

    public int cost(String dayTime , int adDuration, int numDaysPlayed) {
        int pricePs;

        switch(dayTime) {
            case RadioTVAdType.MORNING:
                pricePs = euroPsMorning;
                break;
            case RadioTVAdType.NOON:
                pricePs = euroPsNoon;
                break;
            case RadioTVAdType.AFTERNOON:
                pricePs = euroPsAfternoon;
                break;
            case RadioTVAdType.EVENING:
                pricePs = euroPsEvening;
                break;
            default:
                System.out.println("ERROR: WRONG dayTime passed to Cost function!");
                pricePs = 0;
        }
        int res = adDuration * pricePs * numDaysPlayed;
        System.out.println(adDuration+" * "+pricePs+" * "+numDaysPlayed+" = "+res);
        return res;
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

    public String toString() {
        return String.format("{%s, euroPsMorning : %d, euroPsNoon : %d, euroPsAfternoon : %d, euroPsEvening : %d}", super.toString(), this.euroPsMorning, this.euroPsNoon, this.euroPsAfternoon, this.euroPsEvening);
    }

    public int getTYPE()
    {
        return RADIO_TV;
    }
}
