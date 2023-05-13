package ComplimentaryClasses;
public class Product {
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

    public String getDescription(){
        return this.description;
    }

    public String getSupplierTIN(){
        return this.supplierTIN;
    }
}
