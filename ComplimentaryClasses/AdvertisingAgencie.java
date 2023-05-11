package ComplimentaryClasses;
class AdvertisingAgencie {
    int afm;
    String brandName;

    AdvertisingAgencie(int afm, String brandName){
        this.afm = afm;
        this.brandName = brandName;
    }

    AdvertisingAgencie(){}

    //Getters
    int getAFM(){
        return this.afm;
    }

    String getBrandName(){
        return this.brandName;
    }
}