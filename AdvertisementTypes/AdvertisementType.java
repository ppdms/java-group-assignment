package AdvertisementTypes;
public abstract class AdvertisementType {
    
    // We connect classes AdvertisementType and Advertisement
    // adCode of AdvertisementType == typeCode of Advertisement
    
    private String adCode;
    
    // We connect classes AdvertisementType and AdvertisingAgency 
    // agencyTIN of AdvertisementType == TIN of AdvertisingAgency

    private String agencyTIN;
    
    private String description;

    public AdvertisementType(String adCode, String description, String agencyTIN){
        this.adCode = adCode;
        this.description = description;
        this.agencyTIN = agencyTIN;
    }

    // Go to subclasses to see what extraCharacteristic mean for a specific type of advertisement
    public abstract float cost(String details, int extraCharacteristic, int durationInDays);

    //Getters

    public String getAdCode(){
        return this.adCode;
    }
    public String getDescription() {
        return description;
    }
    public String getAgencyTIN() {
        return agencyTIN;
    }
}
