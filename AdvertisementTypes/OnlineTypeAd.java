package AdvertisementTypes;
class OnlineTypeAd extends AdvertisementType{
    private int pricePerDay;
    //private boolean hasAutomaticDisplay;
    private int automaticDisplayCost;
    private int pricePerExtraPage;

    public OnlineTypeAd(String adCode, String description, String agencyTIN,int pricePerDay,boolean hasAutomaticDisplay, int automaticDisplayCost, int pricePerExtraPage){
        super(adCode, description, agencyTIN);
        this.pricePerDay = pricePerDay;
        //this.hasAutomaticDisplay = hasAutomaticDisplay;
        this.automaticDisplayCost = automaticDisplayCost;
        this.pricePerExtraPage = pricePerExtraPage;
    }

    public float Cost(String duration, int autoShow, int extraPages)
    {
        int result = pricePerDay * Integer.parseInt(duration) + extraPages * pricePerExtraPage;

        if(autoShow == 1)
            result += automaticDisplayCost;
        
        return result;
    }

    //Getters
    public int getPricePerDay(){
        return pricePerDay;
    }
    public int getAutomaticDisplayCost(){
        return automaticDisplayCost;
    }
    public int getPricePerExtraPage(){
        return pricePerExtraPage;
    }
}
