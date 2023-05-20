package Ads;
import ComplimentaryClasses.Identifiable;

public abstract class Ad implements Identifiable{
    
    // We connect classes Ad and AdType
    // typeCode of Ad == adCode of AdType

    private String adTypeCode; 

    // We also connect classes Ad and Product
    // productCode in Ad == productCode in Product

    private String productCode;


    private int durationInDays;
    private String details;

    private String UID;
    private String name;

    public Ad(String UID, String adTypeCode, String productCode, int durationInDays, String details){
        this.UID = UID;
        this.adTypeCode = adTypeCode;
        this.productCode = productCode;
        this.durationInDays = durationInDays;
        this.details = details;

        this.name = "Ad " + UID;
    }

    //Getters
    @Override
    public String getUniqueIdentifier(){
        return this.UID;
    }
    @Override
    public String getName(){
        return this.name;
    }

    public String getAdTypeCode(){
        return adTypeCode;
    }
    public String getProductCode(){
        return productCode;
    }
    public int getDurationInDays(){
        return durationInDays;
    }
    public String getDetails(){
        return details;
    }

    public abstract int getExtraCharacteristic();

    public String toString() {
        return String.format("{UID: %s, adTypeCode : %s, productCode : %s, durInDays : %d, details : %s, extraCharacteristic : %d}", this.UID, this.adTypeCode, this.productCode, this.durationInDays, this.details, this.getExtraCharacteristic());
    }

}