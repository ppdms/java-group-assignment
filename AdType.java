public abstract class AdType implements Identifiable {

    public static final int ONLINE = 0;
    public static final int PRINTED = 1;
    public static final int RADIO_TV = 2;

    // We connect classes AdType and AdAgency
    // agencyTIN of AdType == TIN of AdAgency

    private String agencyTIN;
    private String adCode;
    private String description;

    public AdType(String adCode, String description, String agencyTIN) {
        this.adCode = adCode;
        this.description = description;
        this.agencyTIN = agencyTIN;
    }

    // Go to subclasses to see what extraCharacteristic mean for a specific type of
    // Ad
    public abstract int cost(String details, int extraCharacteristic, int durationInDays);

    // Getters
    @Override
    public String getUniqueIdentifier() {
        return getAdCode();
    }

    @Override
    public String getName() {
        return getDescription();
    }

    public String getAdCode() {
        return this.adCode;
    }

    public String getDescription() {
        return description;
    }

    public String getAgencyTIN() {
        return agencyTIN;
    }

    public String toString() {
        return String.format("adCode : %s, description : %s, agencyTIN : %s", this.adCode, this.description,
                this.agencyTIN);
    }

    public abstract int getTYPE();
}
