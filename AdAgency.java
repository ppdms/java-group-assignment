public class AdAgency implements Identifiable {

    // We connect classes AdAgency and AdType
    // TIN of AdAgency == agencyTIN of AdType

    private String TIN;
    private String brandName;

    private String UID;
    private String name;

    public AdAgency(String TIN, String brandName) {
        this.TIN = TIN;
        this.brandName = brandName;

        this.UID = TIN;
        this.name = "Agency " + brandName;
    }

    AdAgency() {
    }

    // Getters
    public String getTIN() {
        return this.TIN;
    }

    @Override
    public String getUniqueIdentifier() {
        return this.UID;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public String getBrandName() {
        return this.brandName;
    }

    public String toString() {
        return String.format("{TIN : %s, brandName : %s}", this.TIN, this.brandName);
    }
}