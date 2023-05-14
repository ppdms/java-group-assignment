package Collections;
import AdvertisementTypes.AdvertisementType;

public class AdvertisementTypeCollection extends Collection<AdvertisementType>{
    
    //TODO: add checks with containsTypeCode to push to assure integrity

    public AdvertisementTypeCollection() {
        super();
    }

    public boolean containsTypeCode(String typeCode) {
        for (AdvertisementType adType : super.contents) {
            if (adType.getAdCode().equals(typeCode)) return true;
        }
        return false;
    }

    public AdvertisementType getAdvertisementTypeByTypeCode(String typeCode) {
        for (AdvertisementType adType : super.contents) {
            if (adType.getAdCode().equals(typeCode)) return adType;
        }
        return null;
    }

    public String toString() {
        String output = "AdvertisementTypes:\n";
        
        for (AdvertisementType adType : super.contents) {
            output += String.format("   %s,%n", adType);
        }

        return output;
    }


}
