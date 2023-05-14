package ComplimentaryClasses;
public class AdvertisingAgency implements Identifiable{

    // We connect classes AdvertisingAgency and AdvertisementType
    // TIN of AdvertisingAgency == agencyTIN of AdvertisementType

    String TIN;
    String brandName;

    public AdvertisingAgency(String TIN, String brandName){
        this.TIN = TIN;
        this.brandName = brandName;
    }

    AdvertisingAgency(){}

    //Getters
    public String getTIN(){
        return this.TIN;
    }

    @Override
    public String getUniqueIdentifier(){
        return this.TIN;
    }
    @Override
    public String getName(){
        return this.brandName;
    }

    public String getBrandName(){
        return this.brandName;
    }

    public String toString()
    {
        return String.format("{TIN : %s, brandName : %s}", this.TIN, this.brandName);
    }
}