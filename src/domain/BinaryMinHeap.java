package domain;

import java.util.ArrayList;

public class BinaryMinHeap<E extends Comparable<E>> {
    private ArrayList<E> values;

    private boolean isEmpty() {
        return values == null || values.size() == 0;
    }

    public void print() {
        if (this.isEmpty())
            System.out.println("De heap is leeg");
        else
            System.out.println(values);
    }

    public E getMin() {
        if (this.isEmpty())
            throw new IllegalStateException("Kan niet zoeken in een lege heap");
        return values.get(0);
    }

    public boolean addValue(E value) {
        // geen null toevoegen aan de heap
        if (value == null) throw new IllegalArgumentException();
        // indien de heap leeg is: eerst initialiseren
        if (this.isEmpty())
            values = new ArrayList<E>();

        values.add(value);//achteraan toevoegen
        this.bubbleUp();//bubbleUp vanaf de laatste zie slides theorie
        return true;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < values.size();
    }

    private int getIndexRechterkind(int index) {
        return 2 * index + 2;
    }

    private int getIndexLinkerkind(int index) {
        return 2 * index + 1;
    }

    private int getIndexOuder(int index) {
        return index == 0 ? -1 : (index - 1) / 2;
    }

    private boolean heeftRechterKind(int index) {
        return isValidIndex(getIndexRechterkind(index));
    }

    private boolean heeftLinkerKind(int index) {
        return isValidIndex(getIndexLinkerkind(index));
    }


    private void bubbleUp() {
        int index = values.size() - 1;
        int parent = getIndexOuder(index);
        while (isValidIndex(parent) && values.get(index).compareTo(values.get(parent)) < 0) {
            verwissel(index, parent);
            index = parent;
            parent = getIndexOuder(index);
        }
    }

    private void verwissel(int index1, int index2) {
        if (!isValidIndex(index1) || !isValidIndex(index2))
            throw new IllegalArgumentException("No valid index for verwissel");
        E temp = values.get(index1);
        values.set(index1, values.get(index2));
        values.set(index2, temp);
    }

    public E removeSmallest() {
        if (this.isEmpty())
            throw new IllegalStateException("Kan niets verwijderen uit een lege boom");
        E res = this.getMin();// res bevat de kleinste = eerste element van de lijst
        this.values.set(0, this.values.get(this.values.size() - 1));// verwissel eerste met de laatste
        this.values.remove(this.values.size() - 1); // verwijder de laatste
        this.bubbleDown(); // bubble down van eerste naar beneden zie theorie
        return res;
    }

    private int getIndexSmallestChild(int index) {
        int childLeft = getIndexLinkerkind(index);
        int childRight = getIndexRechterkind(index);
        if (isValidIndex(childRight))
            return values.get(childLeft).compareTo(values.get(childRight)) < 0 ? childLeft : childRight;
        if (isValidIndex(childLeft))
            return childLeft;
        throw new IllegalArgumentException("No valid child in getIndexSmallestChild");
    }

    private int getIndexLargestChild(int index) {
        int childLeft = getIndexLinkerkind(index);
        int childRight = getIndexRechterkind(index);
        if (isValidIndex(childRight))
            return values.get(childLeft).compareTo(values.get(childRight)) > 0 ? childLeft : childRight;
        if (isValidIndex(childLeft))
            return childLeft;
        throw new IllegalArgumentException("No valid child in getIndexLargestChild");

    }

    private void bubbleDown() {
        int parent = 0;
        boolean goOn = true;

        while (goOn) {
            goOn = false;
            if (heeftLinkerKind(parent)) {
                int indexSmallestChild = getIndexSmallestChild(parent);
                if (values.get(parent).compareTo(values.get(getIndexLargestChild(parent))) > 0) {
                    verwissel(parent, indexSmallestChild);
                    parent = indexSmallestChild;
                    goOn = true;
                }
            }
        }
    }


    public ArrayList<E> getPath(E value) {
        // TODO zie oefening 6;
        return null;
    }
}
