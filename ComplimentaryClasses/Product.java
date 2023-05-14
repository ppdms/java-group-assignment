package ComplimentaryClasses;

public class Product implements Identifiable{
    private String productCode;
    private String description;
    private String supplierTIN;

    public Product(String productCode, String description, String supplierTIN){
        this.productCode = productCode;
        this.description = description;
        this.supplierTIN = supplierTIN;
    }

    //Getters
    public String getProductCode(){
        return this.productCode;
    }

    @Override
    public String getUniqueIdentifier(){
        return getProductCode();
    }
    @Override
    public String getName(){
        return getDescription();
    }
    public String getDescription(){
        return this.description;
    }

    public String getSupplierTIN(){
        return this.supplierTIN;
    }

    public String toString()
    {
        return String.format("{productCode : %s, description : %s, supplierTIN : %s}", this.productCode, this.description, this.supplierTIN);
    }
}
