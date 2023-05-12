package ComplimentaryClasses;
class AdvertisingAgency {
    int TIN;
    String brandName;

    AdvertisingAgency(int TIN, String brandName){
        this.TIN = TIN;
        this.brandName = brandName;
    }

    AdvertisingAgency(){}

    //Getters
    int getTIN(){
        return this.TIN;
    }

    String getBrandName(){
        return this.brandName;
    }
}