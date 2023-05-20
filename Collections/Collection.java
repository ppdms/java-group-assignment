package Collections;
import ComplimentaryClasses.Identifiable;
import java.util.ArrayList;

public class Collection<T extends Identifiable> {
	protected ArrayList<T> contents;

    private int sequenceNumber;
    private Class<T> itemsClassType;

	public Collection(Class<T> itemsClassType) {
		contents = new ArrayList<T>();
        this.itemsClassType = itemsClassType;
        this.sequenceNumber = 0;
	}

    public String getSequenceNumber()
    {
        String current = Integer.toString(sequenceNumber);

        return current;
    }

    public boolean containsUniqueIdentifier(String uniqueIdentifier) {
        for (T t : contents) {
            if (t.getUniqueIdentifier().equals(uniqueIdentifier)) return true;
        }
        return false;
    }

    public T push(T data) {
        // if contents already contains some T with the same uniqueIdentifier as data,
        // replace it with data and return the old T.

        /*for (int i=0; i<contents.size(); i++) {
                if (contents.get(i).getUniqueIdentifier().equals(data.getUniqueIdentifier())) {
                    contents.add(0, data);
                    return contents.remove(i+1);
                }
        }*/
        sequenceNumber++;
        contents.add(data);
        return null;
    }

    public T get(int index) {
        if (index < 0 || index > this.getLength() - 1)
            return null;

        return (T)contents.get(index);
    }

    public int getLength() {
        return contents.size();
    }

    public String toString() {
        String output = itemsClassType.getName() + "(s):\n";
        
        for (T item : contents) {
            output += String.format("   %s,%n", item.toString());
        }

        return output;
    }
}