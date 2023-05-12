package ComplimentaryClasses;
class Product {
    private int productCode;
    private String description;
    private int supplierTIN;

    public Product(int productCode, String description, int supplierTIN){
        this.productCode = productCode;
        this.description = description;
        this.supplierTIN = supplierTIN;
    }

    //Getters
    int getProductCode(){
        return this.productCode;
    }

    String getDescription(){
        return this.description;
    }

    int getSupplierTIN(){
        return this.supplierTIN;
    }
}
