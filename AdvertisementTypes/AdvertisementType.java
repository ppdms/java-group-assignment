package AdvertisementTypes;
abstract class AdvertisementType {
    private String adCode;
    private String description;
    private String agencyTIN;

    public AdvertisementType(String adCode, String description, String agencyTIN){
        this.adCode = adCode;
        this.description = description;
        this.agencyTIN = agencyTIN;
    }

    //Getters

    String getAdCode(){
        return this.adCode;
    }
    String getDescription() {
        return description;
    }
    String getagencyTIN() {
        return agencyTIN;
    }
}
