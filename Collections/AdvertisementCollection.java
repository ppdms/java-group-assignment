package Collections;
import Advertisements.Advertisement;

public class AdvertisementCollection extends Collection<Advertisement>{
    
    public AdvertisementCollection() {
        super();
    }

    public String toString() {
        
        String output = "Advertisements:\n";
        
        for (Advertisement ad : super.contents) {
            output += String.format("   %s,%n", ad);
        }

        return output;
    }
}
