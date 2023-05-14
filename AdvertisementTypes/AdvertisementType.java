package AdvertisementTypes;
import ComplimentaryClasses.Identifiable;

public abstract class AdvertisementType implements Identifiable {
    
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
    public abstract int cost(String details, int extraCharacteristic, int durationInDays);

    //Getters
    @Override
    public String getUniqueIdentifier(){
        return getAdCode();
    }
    @Override
    public String getName(){
        return getDescription();
    }

    public String getAdCode(){
        return this.adCode;
    }
    public String getDescription() {
        return description;
    }
    public String getAgencyTIN() {
        return agencyTIN;
    }

    public String toString() {
        return String.format("adCode : %s, description : %s, agencyTIN : %s", this.adCode, this.description, this.agencyTIN);
    }
}
