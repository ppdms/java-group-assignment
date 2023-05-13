import ComplimentaryClasses.AdvertisingAgency;
import ComplimentaryClasses.CollectionManager;
//import ComplimentaryClasses.CollectionManager;
import ComplimentaryClasses.Product;
import Collections.*;
import AdvertisementTypes.OnlineTypeAd;
//import AdvertisementTypes.AdvertisementType;
import AdvertisementTypes.PrintedTypeAd;
import AdvertisementTypes.RadioTVTypeAd;
import Advertisements.*;

public class AppTest {

    public static void main(String[] args) {
        
        AdvertisementCollection ads = new AdvertisementCollection();
        AdvertisementTypeCollection adTypes = new AdvertisementTypeCollection();
        AdvertisingAgencyCollection adAgencies = new AdvertisingAgencyCollection();
        ProductCollection products = new ProductCollection();
        
        PrintedTypeAd printTypeAd1 = new PrintedTypeAd("1", "AdPrint1", "1", 10, 5, 1);   
        adTypes.push(printTypeAd1);

        RadioTVTypeAd radioTypeAd1 = new RadioTVTypeAd("2", "AdRadio1", "2", 30, 20, 50, 10);
        adTypes.push(radioTypeAd1);

        OnlineTypeAd onlineTypeAd1 = new OnlineTypeAd("3", "AdOnline1", "1", 20, 300, 500);
        adTypes.push(onlineTypeAd1);

        PrintedAd printAd1 = new PrintedAd("1", "1", 2, PrintedTypeAd.FIRST_PAGE, 50);
        ads.push(printAd1);

        RadioTvAd radioAd1 = new RadioTvAd("2", "1", 5, RadioTVTypeAd.EVENING, 2);
        ads.push(radioAd1);

        OnlineAd onlineAd1 = new OnlineAd("3", "3", 3, "3", 1);
        ads.push(onlineAd1);

        Product product1 = new Product("1", "Product1", "1");
        products.push(product1);

        Product product500 = new Product("500", "Product500", "22332");
        products.push(product500);

        Product product2 = new Product("2", "Product2", "22332");
        products.push(product2);

        Product product3 = new Product("3", "Product3", "22332");
        products.push(product3);

        AdvertisingAgency agency1 = new AdvertisingAgency("1", "Brand1");
        adAgencies.push(agency1);

        System.out.println(ads);
        System.out.println(adTypes);
        System.out.println(adAgencies);
        System.out.println(products);

        int cost1 = CollectionManager.printAdvertisementCostFor(product1, adTypes, ads);
        int cost3 = CollectionManager.printAdvertisementCostFor(product3, adTypes, ads);
        int cost2 = CollectionManager.printAdvertisementCostFor(agency1, adTypes, ads);
        int cost500 = CollectionManager.printAdvertisementCostFor(product500, adTypes, ads);

        System.out.println("Cost of product1 is: " + cost1);
        System.out.println("Cost of agency1 is: " + cost2);
        System.out.println("Cost of product3 is: " + cost3);
        System.out.println("Cost of product500 is: " + cost500);

        CollectionManager.printCostPerProduct(products, adTypes, ads);

        CollectionManager.printNumberOfAdsPerProduct(products, ads);

        CollectionManager.printAdvertisementsOf(agency1, adTypes, ads);
    }    
}
