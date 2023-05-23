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

    private String name;

    public Ad(String adTypeCode, String productCode, int durationInDays, String details){
        this.adTypeCode = adTypeCode;
        this.productCode = productCode;
        this.durationInDays = durationInDays;
        this.details = details;
    }

    //Getters

    //this are here just to make sure Identifiable works
    @Override
    public String getUniqueIdentifier(){
        return Integer.toString(this.hashCode());
    }
    @Override
    public String getName(){
        return Integer.toString(this.hashCode());
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
        String extraCharacteristic;
        if (this instanceof OnlineAd){
            extraCharacteristic = "Autoshow (0->off | 1->on)";
        }
        else if (this instanceof PrintedAd){
            extraCharacteristic = "Words";
        }
        else{
            extraCharacteristic = "Duration in seconds";
        }
        return String.format("{Ad type code : %s ,  Product code : %s ,  Duration in days : %d ,  Details : %s ,  %s : %d}", this.adTypeCode, this.productCode, this.durationInDays, this.details, extraCharacteristic, this.getExtraCharacteristic());
    }

}
