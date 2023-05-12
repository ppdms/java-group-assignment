package ComplimentaryClasses;
public class Product {
    private String productCode;
    private String description;
    private int supplierTIN;

    public Product(String productCode, String description, int supplierTIN){
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

    public int getSupplierTIN(){
        return this.supplierTIN;
    }
}
