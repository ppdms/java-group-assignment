package AdTypes;

public class PrintedAdType extends AdType{

    public static final String FIRST_PAGE = "First";
    public static final String MIDDLE_PAGE = "Middle";
    public static final String LAST_PAGE = "Last";

    private int euroPwFirst; //Price per word on the first page
    private int euroPwMiddle; //Same for the middle
    private int euroPwLast;  //Same for the last page

    public PrintedAdType(String adCode, String description, String agencyTIN, int euroPwFirst, int euroPwMiddle, int euroPwLast){
        super(adCode, description, agencyTIN);
        this.euroPwFirst = euroPwFirst;
        this.euroPwMiddle = euroPwMiddle;
        this.euroPwLast = euroPwLast;
    }

    public int cost(String pageShowcase, int numWords, int durationInDays) {
        int euroPw;

        switch(pageShowcase) {
            case PrintedAdType.FIRST_PAGE:
                euroPw = euroPwFirst;
                break;
            case PrintedAdType.MIDDLE_PAGE:
                euroPw = euroPwMiddle;
                break;
            case PrintedAdType.LAST_PAGE:
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

    public String toString() {
        return String.format("{%s, euroPwFirst : %d, euroPwMiddle : %d, euroPwLast : %d}", super.toString(), this.euroPwFirst, this.euroPwMiddle, this.euroPwLast);
    }

    public int getTYPE()
    {
        return PRINTED;
    }
}