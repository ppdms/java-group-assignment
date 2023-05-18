package Collections;
import Ads.Ad;

public class AdCollection extends Collection<Ad>{
    
    public AdCollection() {
        super();
    }

    public String toString() {
        
        String output = "Ads:\n";
        
        for (Ad ad : super.contents) {
            output += String.format("   %s,%n", ad);
        }

        return output;
    }
}
