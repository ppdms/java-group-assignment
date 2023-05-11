package AdvertismentTypes;
class OnlineTypeAd extends AdvertismentType{
    private int pricePerDay;
    private int automaticShowingCost;
    private int pricePerExtraPage;

    public OnlineTypeAd(String adCode, String description, String agencieAFM,int pricePerDay, int automaticShowingCost, int pricePerExtraPage){
        super(adCode, description, agencieAFM);
        this.pricePerDay = pricePerDay;
        this.automaticShowingCost = automaticShowingCost;
        this.pricePerExtraPage = pricePerExtraPage;
    }

    //Getters
    int getPricePerDay(){
        return pricePerDay;
    }
    int getAutomaticShowingCost(){
        return automaticShowingCost;
    }
    int getPricePerExtraPage(){
        return pricePerExtraPage;
    }
}
