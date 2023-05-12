package AdvertisementTypes;

class PrintedTypeAd extends AdvertisementType{

    public static final String FIRST_PAGE = "1";
    public static final String MIDDLE_PAGE = "2";
    public static final String LAST_PAGE = "3";

    private int euroPwFirst; //Price per word on the first page
    private int euroPwMiddle; //Same for the middle
    private int euroPwLast;  //Same for the last page

    public PrintedTypeAd(String adCode, String description, String agencyTIN, int euroPwFirst, int euroPwMiddle, int euroPwLast){
        super(adCode, description, agencyTIN);
        this.euroPwFirst = euroPwFirst;
        this.euroPwMiddle = euroPwMiddle;
        this.euroPwLast = euroPwLast;
    }

    public float Cost(String pageShowcase, int numWords, int durationInDays)
    {
        int euroPw;

        switch(pageShowcase)
        {
            case PrintedTypeAd.FIRST_PAGE:
                euroPw = euroPwFirst;
                break;
            case PrintedTypeAd.MIDDLE_PAGE:
                euroPw = euroPwMiddle;
                break;
            case PrintedTypeAd.LAST_PAGE:
                euroPw = euroPwLast;
                break;
            default:
                System.out.println("ERROR: WRONG pageShowcase passed to Cost function!");
                euroPw = 0;
        }

        return numWords * euroPw * durationInDays;
    }

    //Getters
    public int getEuroPwFirst(){
        return euroPwFirst;
    }
    public int getEuroPwMiddle(){
        return euroPwMiddle;
    }
    public int getEuroPwLast(){
        return euroPwLast;
    }
}