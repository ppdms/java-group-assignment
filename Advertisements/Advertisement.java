package Advertisements;
import ComplimentaryClasses.Identifiable;

public abstract class Advertisement implements Identifiable{
    
    // We connect classes Advertisement and AdvertisementType
    // typeCode of Advertisement == adCode of AdvertisementType

    private String typeCode; 

    // We also connect classes Advertisement and Product
    // productCode in Advertisement == productCode in Product

    private String productCode;


    private int durationInDays;
    private String details;

    public Advertisement(String typeCode, String productCode, int durationInDays, String details){
        this.typeCode = typeCode;
        this.productCode = productCode;
        this.durationInDays = durationInDays;
        this.details = details;
    }

    //Getters
    @Override
    public String getUniqueIdentifier(){
        return Integer.toString(this.hashCode());
    }
    @Override
    public String getName(){
        return getProductCode();
    }

    public String getTypeCode(){
        return typeCode;
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
        return String.format("{typeCode : %s, productCode : %s, durInDays : %d, details : %s, extraCharacteristic : %d}", this.typeCode, this.productCode, this.durationInDays, this.details, this.getExtraCharacteristic());
    }

}