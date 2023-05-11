package AdvertismentTypes;
abstract class AdvertismentType {
    private String adCode;
    private String description;
    private String agencieAFM;

    public AdvertismentType(String adCode, String description, String agencieAFM){
        this.adCode = adCode;
        this.description = description;
        this.agencieAFM = agencieAFM;
    }

    //Getters

    String getAdCode(){
        return this.adCode;
    }
    String getDescription() {
        return description;
    }
    String getAgencieAFM() {
        return agencieAFM;
    }
}
