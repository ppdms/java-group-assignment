package Collections;
import AdTypes.AdType;

public class AdTypeCollection extends Collection<AdType>{
    
    public AdTypeCollection() {
        super();
    }

    public AdType getAdTypeByTypeCode(String typeCode) {
        for (AdType adType : super.contents) {
            if (adType.getAdCode().equals(typeCode)) return adType;
        }
        return null;
    }

    public String toString() {
        String output = "AdTypes:\n";
        
        for (AdType adType : super.contents) {
            output += String.format("   %s,%n", adType);
        }

        return output;
    }


}
