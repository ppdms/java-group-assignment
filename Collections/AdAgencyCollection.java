package Collections;
import ComplimentaryClasses.AdAgency;

public class AdAgencyCollection extends Collection<AdAgency>{

    public AdAgencyCollection() {
        super();
    }

    public String toString() {
        String output = "Advertising Agencies:\n";
        
        for (AdAgency adAgency : super.contents) {
            output += String.format("   %s,%n", adAgency);
        }

        return output;
    }
    
}
