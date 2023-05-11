package Advertisments;
abstract class Advertisment{
    private String typeCode; 
    private String productCode;
    private int durationInDays;
    private String details;

    public Advertisment(String typeCode, String productCode, int durationInDays, String details){
        this.typeCode = typeCode;
        this.productCode = productCode;
        this.durationInDays = durationInDays;
        this.details = details;
    }

    //Getters
    String getTypeCode(){
        return typeCode;
    }
    String getProductCode(){
        return productCode;
    }
    int getDurationInDays(){
        return durationInDays;
    }
    String getDetails(){
        return details;
    }

}