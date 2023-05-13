package Collections;

import Advertisements.Advertisement;

import java.util.ArrayList;


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
        
        String output = "Advertisements:\n";
        
        for (Advertisement ad : advertisements) {
            output += String.format("   %s,%n", ad);
        }

        return output;
    }
}
