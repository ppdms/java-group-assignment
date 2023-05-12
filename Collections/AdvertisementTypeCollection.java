package Collections;


import AdvertisementTypes.AdvertisementType;

import java.util.ArrayList;

public class AdvertisementTypeCollection {
    
    private ArrayList<AdvertisementType> advertisementTypes;

    public AdvertisementTypeCollection()
    {
        advertisementTypes = new ArrayList<AdvertisementType>();
    }

    public void Push(AdvertisementType type)
    {
        advertisementTypes.add(type);
    }

    public AdvertisementType get(int index)
    {
        if(index < 0 || index > this.getLength() - 1)
            return null;

        return advertisementTypes.get(index);
    }

    public int getLength()
    {
        return advertisementTypes.size();
    }

    public String toString()
    {
        String output = "";
        
        for (AdvertisementType adType : advertisementTypes) {
            output += adType.toString() + '\n';
        }

        return output;
    }


}
