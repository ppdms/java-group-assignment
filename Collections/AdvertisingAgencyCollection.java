package Collections;

import ComplimentaryClasses.AdvertisingAgency;

import java.util.ArrayList;

public class AdvertisingAgencyCollection {
    private ArrayList<AdvertisingAgency> advertisingAgencies;

    public AdvertisingAgencyCollection()
    {
        advertisingAgencies = new ArrayList<AdvertisingAgency>();
    }

    public void push(AdvertisingAgency type)
    {
        advertisingAgencies.add(type);
    }

    public AdvertisingAgency get(int index)
    {
        if(index < 0 || index > this.getLength() - 1)
            return null;

        return advertisingAgencies.get(index);
    }

    public int getLength()
    {
        return advertisingAgencies.size();
    }

    public String toString()
    {
        String output = "Advertising Agencies:\n";
        
        for (AdvertisingAgency adAgency : advertisingAgencies) {
            output += String.format("   %s,%n", adAgency);
        }

        return output;
    }
    
}
