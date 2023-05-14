package Collections;
import ComplimentaryClasses.Identifiable;
import java.util.ArrayList;

public class Collection<T extends Identifiable> {
	protected ArrayList<T> contents;

	Collection() {
		contents = new ArrayList<T>();
	}

    public void push(T data) {
        contents.add(data);
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