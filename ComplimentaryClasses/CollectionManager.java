package ComplimentaryClasses;

import Collections.AdvertisementCollection;
import Collections.AdvertisementTypeCollection;
import Collections.ProductCollection;

import Advertisements.Advertisement;

import AdvertisementTypes.AdvertisementType;

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

    public static void printAdvertisementsOf(AdvertisingAgency agency, AdvertisementTypeCollection adTypes, AdvertisementCollection ads)
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

    private static int calculateTotalCostOf(AdvertisementCollection ads, AdvertisementTypeCollection adTypes)
    {
        int totalCost = 0;

        for(int i = 0; i < ads.getLength(); i++)
        {
            Advertisement curAd = ads.get(i);

            for(int j = 0; j < adTypes.getLength(); j++)
            {
                AdvertisementType curType = adTypes.get(j);

                if(curAd.getTypeCode().equals(curType.getAdCode()))
                {
                    int cost = curType.cost(curAd.getDetails(), curAd.getExtraCharacteristic(), curAd.getDurationInDays());

                    totalCost += cost;

                    System.out.printf("Cost of advertisement %s with type %s is: %d%n", curAd, curType, cost);
                    System.out.println();
                }
            }
        }

        return totalCost;
    }

    public static int printAdvertisementCostFor(AdvertisingAgency agency, AdvertisementTypeCollection adTypes, AdvertisementCollection ads)
    {
        if(agency == null || adTypes.getLength() == 0 || ads.getLength() == 0)
        {
            System.out.printf("Agency is not null %b, AdTypes length %d, Ads length %d%n", agency, adTypes.getLength(), ads.getLength());
            return 0;
        }

        AdvertisementCollection adsToCalculateCost = CollectionManager.findAdvertisementsOf(agency, adTypes, ads);

        if(adsToCalculateCost.getLength() == 0)
        {
            System.out.println("Could not find any advertisements of agent: " + agency);
            return 0;
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
        String productDescriptions[] = new String[products.getLength()];


        for(int i = 0; i < products.getLength(); i++)
        {
            countsOfAdsPerProduct[i] = 0;
            productDescriptions[i] = products.get(i).getDescription();
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

        // Sort array

        for(int i = 1; i < countsOfAdsPerProduct.length; i++)
        {
            for(int j = i; j < countsOfAdsPerProduct.length; j++)
            {
                if(countsOfAdsPerProduct[j] > countsOfAdsPerProduct[j - 1])
                {
                    int temp = countsOfAdsPerProduct[j];
                    countsOfAdsPerProduct[j] = countsOfAdsPerProduct[j - 1];
                    countsOfAdsPerProduct[j - 1] = temp;

                    String temp2 = productDescriptions[j - 1];
                    productDescriptions[j - 1] = productDescriptions[j];
                    productDescriptions[j] = temp2;
                }
            }
        }

        for(int i = 0; i < countsOfAdsPerProduct.length; i++)
        {
            System.out.printf("Number of Advertisements for Product %s is : %d%n", productDescriptions[i], countsOfAdsPerProduct[i]);
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

    public static int printAdvertisementCostFor(Product product, AdvertisementTypeCollection adTypes, AdvertisementCollection ads)
    {

        if(product == null || adTypes.getLength() == 0 || ads.getLength() == 0)
        {
            System.out.printf("Product is not null %b, AdTypes length %d, Ads length %d%n", product, adTypes.getLength(), ads.getLength());
            return 0;
        }

        AdvertisementCollection adsToCalculateCost = CollectionManager.findAdvertisementsOf(product, adTypes, ads);

        if(adsToCalculateCost.getLength() == 0)
        {
            System.out.println("Could not find any advertisements of product: " + product);
            return 0;
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

        int costsPerProduct[] = new int[products.getLength()];
        String productDescriptions[] = new String[products.getLength()];


        for(int i = 0; i < products.getLength(); i++)
        {
            costsPerProduct[i] = 0;
            productDescriptions[i] = products.get(i).getDescription();
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

        // Sort array

        for(int i = 1; i < costsPerProduct.length; i++)
        {
            for(int j = i; j < costsPerProduct.length; j++)
            {
                if(costsPerProduct[j] > costsPerProduct[j - 1])
                {
                    int temp = costsPerProduct[j];
                    costsPerProduct[j] = costsPerProduct[j - 1];
                    costsPerProduct[j - 1] = temp;

                    String temp2 = productDescriptions[j - 1];
                    productDescriptions[j - 1] = productDescriptions[j];
                    productDescriptions[j] = temp2;
                }
            }
        }

        for(int i = 0; i < costsPerProduct.length; i++)
        {
            System.out.printf("Cost of Advertisements for Product %s is : %d%n", productDescriptions[i], costsPerProduct[i]);
        }
    }
}
