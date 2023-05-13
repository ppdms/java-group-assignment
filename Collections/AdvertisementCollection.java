package Collections;

import Advertisements.Advertisement;
import AdvertisementTypes.AdvertisementType;
import ComplimentaryClasses.AdvertisingAgency;
import ComplimentaryClasses.Product;

import java.util.ArrayList;

import java.util.Arrays; // Sort algorithm

public class AdvertisementCollection {
    
    private ArrayList<Advertisement> advertisements;

    public AdvertisementCollection() {
        advertisements = new ArrayList<Advertisement>();
    }

    public void push(Advertisement ad) {
        advertisements.add(ad);
    }

    public Advertisement get(int index) {
        if(index < 0 || index > this.getLength() - 1)
            return null;

        return advertisements.get(index);
    }

    public int getLength() {
        return advertisements.size();
    }

    public String toString() {
        
        String output = "";
        
        for (Advertisement ad : advertisements) {
            output += String.format("{ %s }%n", ad);
        }

        return output;
    }

    // To find all the advertisements of a specific advertising agency we have to 
    // AdvertisingAgency -TIN-> AdvetisementType -adCode-> Advertisement
    // We have to do 2 matchings

    private AdvertisementCollection findAdvertisementsOf(AdvertisingAgency agency, AdvertisementTypeCollection adTypes) {

        AdvertisementCollection adsOfAgency = new AdvertisementCollection();

        for (int i = 0; i < adTypes.getLength(); i++) {
            
            AdvertisementType curType = adTypes.get(i);
            
            if(curType.getAgencyTIN().equals(agency.getTIN())) {
                // If we find an adType published by our target agency we search in what advertisement
                // the advertisementType links to

                for(int j = 0; j < this.getLength(); j++) {

                    Advertisement curAd = this.get(j);

                    if(curType.getAdCode().equals(curAd.getTypeCode())) {
                        // We have found an agency's ad
                        adsOfAgency.push(curAd);
                    }
                }
            }
        }

        return adsOfAgency;
    }

    public void printAdvertisementsOf(AdvertisingAgency agency, AdvertisementTypeCollection adTypes)
    {
        AdvertisementCollection adsToPrint = this.findAdvertisementsOf(agency, adTypes);
        
        for (int i = 0; i < adsToPrint.getLength(); i++) {
            System.out.println(adsToPrint.get(i));
        }
    }

    private float calculateTotalCostOf(AdvertisementCollection ads, AdvertisementTypeCollection adTypes)
    {
        float totalCost = 0;

        for(int i = 0; i < ads.getLength(); i++)
        {
            Advertisement curAd = ads.get(i);

            for(int j = 0; j < adTypes.getLength(); j++)
            {
                AdvertisementType curType = adTypes.get(j);

                if(curAd.getTypeCode().equals(curType.getAdCode()))
                {
                    float cost = curType.cost(curAd.getDetails(), curAd.getExtraCharacteristic(), curAd.getDurationInDays());

                    totalCost += cost;

                    System.out.printf("Cost of advertisement %s with type %s is: %f%n", curAd, curType, cost);
                }
            }
        }

        return totalCost;
    }

    public float calculateCostFor(AdvertisingAgency agency, AdvertisementTypeCollection adTypes)
    {

        AdvertisementCollection adsToCalculateCost = this.findAdvertisementsOf(agency, adTypes);

        return this.calculateTotalCostOf(adsToCalculateCost, adTypes);
    }

    public void printNumberOfAdsPerProduct(ProductCollection products)
    {
        int countsOfAdsPerProduct[] = new int[products.getLength()];


        for(int i = 0; i < products.getLength(); i++)
        {
            countsOfAdsPerProduct[i] = 0;
        }


        // Find number of ads per product

        for(int i = 0; i < products.getLength(); i++)
        {
            Product curProduct = products.get(i);

            for(int j = 0; j < this.getLength(); j++)
            {
                Advertisement curAd = this.get(j);

                if(curProduct.getProductCode().equals(curAd.getProductCode()))
                {
                    countsOfAdsPerProduct[i]++;
                }
            }
        }

        // Print them in descending order

        Arrays.sort(countsOfAdsPerProduct);

        for(int i = 0; i < countsOfAdsPerProduct.length; i++)
        {
            System.out.println(countsOfAdsPerProduct[i]);
        }
    }

    private AdvertisementCollection findAdvertisementsOf(Product product, AdvertisementTypeCollection adTypes) {

        AdvertisementCollection adsOfProduct = new AdvertisementCollection();

        for (int i = 0; i < this.getLength(); i++) {
            
            Advertisement curAd = this.get(i);
            
            if(this.get(i).getProductCode().equals(product.getProductCode())) {
                // If we find an adType published by our target agency we search in what advertisement
                // the advertisementType links to
                adsOfProduct.push(curAd);
            }
        }

        return adsOfProduct;
    }

    public float calculateAdvertisementCostFor(Product product, AdvertisementTypeCollection adTypes)
    {
        AdvertisementCollection adsToCalculateCost = this.findAdvertisementsOf(product, adTypes);

        return this.calculateTotalCostOf(adsToCalculateCost, adTypes);
    }

    public void printCostPerProduct(ProductCollection products, AdvertisementTypeCollection adTypes)
    {
        float costsPerProduct[] = new float[products.getLength()];


        for(int i = 0; i < products.getLength(); i++)
        {
            costsPerProduct[i] = 0.0f;
        }


        // Find number of ads per product

        for(int i = 0; i < products.getLength(); i++)
        {
            Product curProduct = products.get(i);

            for(int j = 0; j < this.getLength(); j++)
            {
                Advertisement curAd = this.get(j);

                if(curProduct.getProductCode().equals(curAd.getProductCode()))
                {
                    for(int k = 0; k < adTypes.getLength(); k++)
                    {
                        AdvertisementType curType = adTypes.get(k);

                        if(curType.getAdCode().equals(curAd.getTypeCode()))
                        {
                            costsPerProduct[i] += curType.cost(curAd.getDetails(), curAd.getExtraCharacteristic(), curAd.getDurationInDays());
                        }
                    }
                }
            }
        }

        // Print them in descending order

        Arrays.sort(costsPerProduct);

        for(int i = 0; i < costsPerProduct.length; i++)
        {
            System.out.println(costsPerProduct[i]);
        }
    }
}
