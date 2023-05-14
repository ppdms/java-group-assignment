package Collections;
import ComplimentaryClasses.Identifiable;
import java.util.ArrayList;

public class Collection<T extends Identifiable> {
	protected ArrayList<T> contents;

	Collection() {
		contents = new ArrayList<T>();
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

        for (int i=0; i<contents.size(); i++) {
                if (contents.get(i).getUniqueIdentifier().equals(data.getUniqueIdentifier())) {
                    contents.add(0, data);
                    System.out.println("collision");
                    return contents.remove(i+1);
                }
        }
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

}