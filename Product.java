public class Product implements Identifiable{
    private String productCode;
    private String description;
    private String supplierTIN;

    private String UID;
    private String name;

    public Product(String productCode, String description, String supplierTIN){
        this.productCode = productCode;
        this.description = description;
        this.supplierTIN = supplierTIN;

        this.UID = productCode;
        this.name = "Product " + description;
    }

    //Getters
    public String getProductCode(){
        return this.productCode;
    }

    @Override
    public String getUniqueIdentifier(){
        return this.UID;
    }
    @Override
    public String getName(){
        return this.name;
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
