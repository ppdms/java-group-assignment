package Advertisements;
public abstract class Advertisement{
    
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

}