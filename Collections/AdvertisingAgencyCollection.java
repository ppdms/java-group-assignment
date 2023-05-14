package Collections;

import ComplimentaryClasses.AdvertisingAgency;

import java.util.ArrayList;

public class AdvertisingAgencyCollection extends Collection<AdvertisingAgency>{

    public AdvertisingAgencyCollection() {
        super();
    }

    public String toString() {
        String output = "Advertising Agencies:\n";
        
        for (AdvertisingAgency adAgency : super.contents) {
            output += String.format("   %s,%n", adAgency);
        }

        return output;
    }
    
}
