package Collections;

import Advertisements.Advertisement;
import AdvertisementTypes.AdvertisementType;
import ComplimentaryClasses.AdvertisingAgency;

import java.util.ArrayList;

public class AdvertisementCollection {
    
    private ArrayList<Advertisement> advertisements;

    public AdvertisementCollection() {
        advertisements = new ArrayList<Advertisement>();
    }

    public void push(Advertisement ad) {
        advertisements.add(ad);
    }

    public Advertisement get(int index)
    {
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

    private AdvertisementCollection FindAdvertisementsOf(AdvertisingAgency agency, AdvertisementTypeCollection adTypes, AdvertisementCollection ads) {

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

    public void PrintAdvertisementsOf(AdvertisingAgency agency, AdvertisementTypeCollection adTypes, AdvertisementCollection ads)
    {
        AdvertisementCollection adsToPrint = this.FindAdvertisementsOf(agency, adTypes, ads);
        
        for (int i = 0; i < adsToPrint.getLength(); i++) {
            System.out.println(adsToPrint.get(i));
        }
    }

    private 
}
