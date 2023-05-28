package ComplimentaryClasses;

import Collections.Collection;

import Ads.Ad;

import AdTypes.*;
//import AdTypes.AdType;

public class CollectionManager {

    private static Collection<Ad> ads;
    private static Collection<AdType> adTypes;
    //private static Collection<AdAgency> agencies;
    private static Collection<Product> products;

    // Before using collection manager make sure to bind the collections to it
    // This is used so we dont have to pass Collections in the functions all the time
    // Do this once at the start of the program

    // If we want to use different collections we have to bind another collection set for our functions to operate on them

    public static void bind(Collection<Ad> ads, Collection<AdType> adTypes, Collection<AdAgency> agencies, Collection<Product> products)
    {
        CollectionManager.ads = ads;
        CollectionManager.adTypes = adTypes;
        //CollectionManager.agencies = agencies;
        CollectionManager.products = products;
    }

    public static AdType getAdTypeByTypeCode(String typeCode) {

        for(int i = 0; i < adTypes.getLength(); i++)
        {
            if(adTypes.get(i).getUniqueIdentifier().equals(typeCode)) return adTypes.get(i);
        }
        
        return null;
    }

    private static Collection<Ad> findAdsOf(AdAgency agency) {

        Collection<Ad> adsOfAgency = new Collection<Ad>(Ad.class);

        for (int i = 0; i < adTypes.getLength(); i++) {
            
            AdType curType = adTypes.get(i);
            
            if(curType.getAgencyTIN().equals(agency.getTIN())) {
                // If we find an adType published by our target agency we search in what Ad
                // the AdType links to

                for(int j = 0; j < ads.getLength(); j++) {

                    Ad curAd = ads.get(j);

                    if(curType.getUniqueIdentifier().equals(curAd.getAdTypeCode())) {
                        // We have found an agency's ad
                        adsOfAgency.push(curAd);
                    }
                }
            }
        }

        return adsOfAgency;
    }

    public static void printAdsOf(AdAgency agency)
    {
        if(agency == null || adTypes.getLength() == 0 || ads.getLength() == 0)
        {
            System.out.printf("Agency is not null %b, AdTypes length %d, Ads length %d%n", agency, adTypes.getLength(), ads.getLength());
            return;
        }

        Collection<Ad> adsToPrint = CollectionManager.findAdsOf(agency);
        
        if(adsToPrint.getLength() == 0)
        {
            System.out.println("Could not find any Ads of agent: " + agency);
            return;
        }

        for (int i = 0; i < adsToPrint.getLength(); i++) {
            System.out.println(adsToPrint.get(i));
        }
    }
    // 💀
    private static int calculateTotalCostOf(Collection<Ad> m_ads)
    {
        int totalCost = 0;

        for(int i = 0; i < m_ads.getLength(); i++)
        {
            Ad curAd = m_ads.get(i);

            for(int j = 0; j < adTypes.getLength(); j++)
            {
                AdType curType = adTypes.get(j);

                if(curAd.getAdTypeCode().equals(curType.getUniqueIdentifier()))
                {   
                    System.out.print("Ad :["+curAd.getUniqueIdentifier()+"] with the following cost: ");
                    int cost = curType.cost(curAd.getDetails(), curAd.getExtraCharacteristic(), curAd.getDurationInDays());

                    totalCost += cost;
                }
            }
        }

        return totalCost;
    }

    public static int printAdCostFor(AdAgency agency)
    {
        if(agency == null || adTypes.getLength() == 0 || ads.getLength() == 0)
        {
            //System.out.printf("Agency is not null %b, AdTypes length %d, Ads length %d%n", agency, adTypes.getLength(), ads.getLength());
            return 0;
        }

        Collection<Ad> adsToCalculateCost = CollectionManager.findAdsOf(agency);

        if(adsToCalculateCost.getLength() == 0)
        {
            //System.out.println("Could not find any Ads of agent: " + agency);
            return 0;
        }

        return CollectionManager.calculateTotalCostOf(adsToCalculateCost);
    }

    public static void printNumberOfAdsPerProduct()
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

        // Sort array 💀

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

    private static Collection<Ad> findAdsOf(Product product) {

        Collection<Ad> adsOfProduct = new Collection<Ad>(Ad.class);

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

    public static int printAdCostFor(Product product)
    {

        if(product == null || adTypes.getLength() == 0 || ads.getLength() == 0)
        {
            //System.out.printf("Product is not null %b, AdTypes length %d, Ads length %d%n", product, adTypes.getLength(), ads.getLength());
            return 0;
        }

        Collection<Ad> adsToCalculateCost = CollectionManager.findAdsOf(product);

        if(adsToCalculateCost.getLength() == 0)
        {
            //System.out.println("Could not find any Ads of product: " + product);
            return 0;
        }

        return CollectionManager.calculateTotalCostOf(adsToCalculateCost);
    }

    public static void printCostPerProduct()
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
            System.out.println("The cost of "+curProduct.getDescription()+" is the sum of all the following:");

            for(int j = 0; j < ads.getLength(); j++)
            {
                Ad curAd = ads.get(j);

                if(curProduct.getProductCode().equals(curAd.getProductCode()))
                {
                    for(int k = 0; k < adTypes.getLength(); k++)
                    {
                        AdType curType = adTypes.get(k);

                        if(curType.getUniqueIdentifier().equals(curAd.getAdTypeCode()))
                        {
                            costsPerProduct[i] += curType.cost(curAd.getDetails(), curAd.getExtraCharacteristic(), curAd.getDurationInDays());
                        }
                    }
                }
            }
            System.out.println("\n");
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
