package ComplimentaryClasses;
public class Product {
    private int productCode;
    private String description;
    private int supplierTIN;

    public Product(int productCode, String description, int supplierTIN){
        this.productCode = productCode;
        this.description = description;
        this.supplierTIN = supplierTIN;
    }

    //Getters
    public int getProductCode(){
        return this.productCode;
    }

    public String getDescription(){
        return this.description;
    }

    public int getSupplierTIN(){
        return this.supplierTIN;
    }
}
