package ComplimentaryClasses;

import Collections.AdCollection;
import Collections.AdTypeCollection;
import Collections.ProductCollection;

import Ads.Ad;

import AdTypes.AdType;

public class CollectionManager {

    private static AdCollection findAdsOf(AdAgency agency, AdTypeCollection adTypes, AdCollection ads) {

        AdCollection adsOfAgency = new AdCollection();

        for (int i = 0; i < adTypes.getLength(); i++) {
            
            AdType curType = adTypes.get(i);
            
            if(curType.getAgencyTIN().equals(agency.getUniqueIdentifier())) {
                // If we find an adType published by our target agency we search in what Ad
                // the AdType links to

                for(int j = 0; j < ads.getLength(); j++) {

                    Ad curAd = ads.get(j);

                    if(curType.getAdCode().equals(curAd.getTypeCode())) {
                        // We have found an agency's ad
                        adsOfAgency.push(curAd);
                    }
                }
            }
        }

        return adsOfAgency;
    }

    public static void printAdsOf(AdAgency agency, AdTypeCollection adTypes, AdCollection ads)
    {
        if(agency == null || adTypes.getLength() == 0 || ads.getLength() == 0)
        {
            System.out.printf("Agency is not null %b, AdTypes length %d, Ads length %d%n", agency, adTypes.getLength(), ads.getLength());
            return;
        }

        AdCollection adsToPrint = CollectionManager.findAdsOf(agency, adTypes, ads);
        
        if(adsToPrint.getLength() == 0)
        {
            System.out.println("Could not find any Ads of agent: " + agency);
            return;
        }

        for (int i = 0; i < adsToPrint.getLength(); i++) {
            System.out.println(adsToPrint.get(i));
        }
    }

    private static int calculateTotalCostOf(AdCollection ads, AdTypeCollection adTypes)
    {
        int totalCost = 0;

        for(int i = 0; i < ads.getLength(); i++)
        {
            Ad curAd = ads.get(i);

            for(int j = 0; j < adTypes.getLength(); j++)
            {
                AdType curType = adTypes.get(j);

                if(curAd.getTypeCode().equals(curType.getAdCode()))
                {
                    int cost = curType.cost(curAd.getDetails(), curAd.getExtraCharacteristic(), curAd.getDurationInDays());

                    totalCost += cost;

                    //System.out.printf("Cost of Ad %s with type %s is: %d%n", curAd, curType, cost);
                    //System.out.println();
                }
            }
        }

        return totalCost;
    }

    public static int printAdCostFor(AdAgency agency, AdTypeCollection adTypes, AdCollection ads)
    {
        if(agency == null || adTypes.getLength() == 0 || ads.getLength() == 0)
        {
            System.out.printf("Agency is not null %b, AdTypes length %d, Ads length %d%n", agency, adTypes.getLength(), ads.getLength());
            return 0;
        }

        AdCollection adsToCalculateCost = CollectionManager.findAdsOf(agency, adTypes, ads);

        if(adsToCalculateCost.getLength() == 0)
        {
            System.out.println("Could not find any Ads of agent: " + agency);
            return 0;
        }

        return CollectionManager.calculateTotalCostOf(adsToCalculateCost, adTypes);
    }

    public static void printNumberOfAdsPerProduct(ProductCollection products, AdCollection ads)
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
                Ad curAd = ads.get(j);

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
            System.out.printf("Number of Ads for Product %s is : %d%n", productDescriptions[i], countsOfAdsPerProduct[i]);
        }
    }

    private static AdCollection findAdsOf(Product product, AdTypeCollection adTypes, AdCollection ads) {

        AdCollection adsOfProduct = new AdCollection();

        for (int i = 0; i < ads.getLength(); i++) {
            
            Ad curAd = ads.get(i);
            
            if(ads.get(i).getProductCode().equals(product.getProductCode())) {
                // If we find an adType published by our target agency we search in what Ad
                // the AdType links to
                adsOfProduct.push(curAd);
            }
        }

        return adsOfProduct;
    }

    public static int printAdCostFor(Product product, AdTypeCollection adTypes, AdCollection ads)
    {

        if(product == null || adTypes.getLength() == 0 || ads.getLength() == 0)
        {
            System.out.printf("Product is not null %b, AdTypes length %d, Ads length %d%n", product, adTypes.getLength(), ads.getLength());
            return 0;
        }

        AdCollection adsToCalculateCost = CollectionManager.findAdsOf(product, adTypes, ads);

        if(adsToCalculateCost.getLength() == 0)
        {
            System.out.println("Could not find any Ads of product: " + product);
            return 0;
        }

        return CollectionManager.calculateTotalCostOf(adsToCalculateCost, adTypes);
    }

    public static void printCostPerProduct(ProductCollection products, AdTypeCollection adTypes, AdCollection ads)
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
                Ad curAd = ads.get(j);

                if(curProduct.getProductCode().equals(curAd.getProductCode()))
                {
                    for(int k = 0; k < adTypes.getLength(); k++)
                    {
                        AdType curType = adTypes.get(k);

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
            System.out.printf("Cost of Ads for Product %s is : %d%n", productDescriptions[i], costsPerProduct[i]);
        }
    }
}
