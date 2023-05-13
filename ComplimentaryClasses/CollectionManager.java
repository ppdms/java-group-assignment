package ComplimentaryClasses;

import Collections.AdvertisementCollection;
import Collections.AdvertisementTypeCollection;
import Collections.ProductCollection;

import Advertisements.Advertisement;

import AdvertisementTypes.AdvertisementType;

import java.util.Arrays; // Sort algorithm

public class CollectionManager {

    private static AdvertisementCollection findAdvertisementsOf(AdvertisingAgency agency, AdvertisementTypeCollection adTypes, AdvertisementCollection ads) {

        AdvertisementCollection adsOfAgency = new AdvertisementCollection();

        for (int i = 0; i < adTypes.getLength(); i++) {
            
            AdvertisementType curType = adTypes.get(i);
            
            if(curType.getAgencyTIN().equals(agency.getTIN())) {
                // If we find an adType published by our target agency we search in what advertisement
                // the advertisementType links to

                for(int j = 0; j < ads.getLength(); j++) {

                    Advertisement curAd = ads.get(j);

                    if(curType.getAdCode().equals(curAd.getTypeCode())) {
                        // We have found an agency's ad
                        adsOfAgency.push(curAd);
                    }
                }
            }
        }

        return adsOfAgency;
    }

    public void printAdvertisementsOf(AdvertisingAgency agency, AdvertisementTypeCollection adTypes, AdvertisementCollection ads)
    {
        if(agency == null || adTypes.getLength() == 0 || ads.getLength() == 0)
        {
            System.out.printf("Agency is not null %b, AdTypes length %d, Ads length %d%n", agency, adTypes.getLength(), ads.getLength());
            return;
        }

        AdvertisementCollection adsToPrint = CollectionManager.findAdvertisementsOf(agency, adTypes, ads);
        
        if(adsToPrint.getLength() == 0)
        {
            System.out.println("Could not find any advertisements of agent: " + agency);
            return;
        }

        for (int i = 0; i < adsToPrint.getLength(); i++) {
            System.out.println(adsToPrint.get(i));
        }
    }

    private static float calculateTotalCostOf(AdvertisementCollection ads, AdvertisementTypeCollection adTypes)
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

    public static float calculateCostFor(AdvertisingAgency agency, AdvertisementTypeCollection adTypes, AdvertisementCollection ads)
    {
        if(agency == null || adTypes.getLength() == 0 || ads.getLength() == 0)
        {
            System.out.printf("Agency is not null %b, AdTypes length %d, Ads length %d%n", agency, adTypes.getLength(), ads.getLength());
            return 0.0f;
        }

        AdvertisementCollection adsToCalculateCost = CollectionManager.findAdvertisementsOf(agency, adTypes, ads);

        if(adsToCalculateCost.getLength() == 0)
        {
            System.out.println("Could not find any advertisements of agent: " + agency);
            return 0.0f;
        }

        return CollectionManager.calculateTotalCostOf(adsToCalculateCost, adTypes);
    }

    public static void printNumberOfAdsPerProduct(ProductCollection products, AdvertisementCollection ads)
    {
        if(products.getLength() == 0 || ads.getLength() == 0)
        {
            System.out.printf("Products length: %d, Ads length: %d", products.getLength(), ads.getLength());
            return;
        }

        int countsOfAdsPerProduct[] = new int[products.getLength()];


        for(int i = 0; i < products.getLength(); i++)
        {
            countsOfAdsPerProduct[i] = 0;
        }


        // Find number of ads per product

        for(int i = 0; i < products.getLength(); i++)
        {
            Product curProduct = products.get(i);

            for(int j = 0; j < ads.getLength(); j++)
            {
                Advertisement curAd = ads.get(j);

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

    private static AdvertisementCollection findAdvertisementsOf(Product product, AdvertisementTypeCollection adTypes, AdvertisementCollection ads) {

        AdvertisementCollection adsOfProduct = new AdvertisementCollection();

        for (int i = 0; i < ads.getLength(); i++) {
            
            Advertisement curAd = ads.get(i);
            
            if(ads.get(i).getProductCode().equals(product.getProductCode())) {
                // If we find an adType published by our target agency we search in what advertisement
                // the advertisementType links to
                adsOfProduct.push(curAd);
            }
        }

        return adsOfProduct;
    }

    public static float calculateAdvertisementCostFor(Product product, AdvertisementTypeCollection adTypes, AdvertisementCollection ads)
    {

        if(product == null || adTypes.getLength() == 0 || ads.getLength() == 0)
        {
            System.out.printf("Product is not null %b, AdTypes length %d, Ads length %d%n", product, adTypes.getLength(), ads.getLength());
            return 0.0f;
        }

        AdvertisementCollection adsToCalculateCost = CollectionManager.findAdvertisementsOf(product, adTypes, ads);

        if(adsToCalculateCost.getLength() == 0)
        {
            System.out.println("Could not find any advertisements of product: " + product);
            return 0.0f;
        }

        return CollectionManager.calculateTotalCostOf(adsToCalculateCost, adTypes);
    }

    public static void printCostPerProduct(ProductCollection products, AdvertisementTypeCollection adTypes, AdvertisementCollection ads)
    {
        if(products.getLength() == 0 || adTypes.getLength() == 0 || ads.getLength() == 0)
        {
            System.out.printf("Products length %d, AdTypes length %d, Ads length %d%n", products.getLength(), adTypes.getLength(), ads.getLength());
            return;
        }

        float costsPerProduct[] = new float[products.getLength()];


        for(int i = 0; i < products.getLength(); i++)
        {
            costsPerProduct[i] = 0.0f;
        }


        // Find number of ads per product

        for(int i = 0; i < products.getLength(); i++)
        {
            Product curProduct = products.get(i);

            for(int j = 0; j < ads.getLength(); j++)
            {
                Advertisement curAd = ads.get(j);

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
