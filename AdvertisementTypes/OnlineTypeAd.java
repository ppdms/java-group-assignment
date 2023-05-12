package AdvertisementTypes;
class OnlineTypeAd extends AdvertisementType{
    private int pricePerDay;
    private int automaticDisplayCost;
    private int pricePerExtraPage;

    public OnlineTypeAd(String adCode, String description, String agencyTIN,int pricePerDay, int automaticDisplayCost, int pricePerExtraPage){
        super(adCode, description, agencyTIN);
        this.pricePerDay = pricePerDay;
        this.automaticDisplayCost = automaticDisplayCost;
        this.pricePerExtraPage = pricePerExtraPage;
    }

    //Getters
    int getPricePerDay(){
        return pricePerDay;
    }
    int getAutomaticDisplayCost(){
        return automaticDisplayCost;
    }
    int getPricePerExtraPage(){
        return pricePerExtraPage;
    }
}
