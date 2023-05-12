package Collections;

import ComplimentaryClasses.Product;

import java.util.ArrayList;

public class ProductCollection {
    private ArrayList<Product> products;

    public ProductCollection()
    {
        products = new ArrayList<Product>();
    }

    public void Push(Product type)
    {
        products.add(type);
    }

    public Product get(int index)
    {
        if(index < 0 || index > this.getLength() - 1)
            return null;

        return products.get(index);
    }

    public int getLength()
    {
        return products.size();
    }

    public String toString()
    {
        String output = "";
        
        for (Product adAgency : products) {
            output += adAgency.toString() + '\n';
        }

        return output;
    }
}
