package ComplimentaryClasses;
class Product {
    private int productCode;
    private String description;
    private int supplierAFM;

    public Product(int productCode, String description, int supplierAFM){
        this.productCode = productCode;
        this.description = description;
        this.supplierAFM = supplierAFM;
    }

    //Getters
    int getProductCode(){
        return this.productCode;
    }

    String getDescription(){
        return this.description;
    }

    int getSupplierAFM(){
        return this.supplierAFM;
    }
}
