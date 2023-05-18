package ComplimentaryClasses;
public class AdAgency implements Identifiable{

    // We connect classes AdAgency and AdType
    // TIN of AdAgency == agencyTIN of AdType

    String TIN;
    String brandName;

    public AdAgency(String TIN, String brandName){
        this.TIN = TIN;
        this.brandName = brandName;
    }

    AdAgency(){}

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