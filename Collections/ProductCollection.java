package Collections;

import ComplimentaryClasses.Product;

public class ProductCollection extends Collection<Product> {

    public ProductCollection() {
        super();
    }

    public boolean containsProductCode(String productCode) {
        for (Product product : super.contents) {
            if (product.getProductCode().equals(productCode)) return true;
        }
        return false;
    }

    public String toString() {
        String output = "Products:\n";
        
        for (Product product : super.contents) {
            output += String.format("   %s,%n", product);
        }

        return output;
    }
}
