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

//    public boolean addValue(E value) {
//        // geen null toevoegen aan de heap
//        if (value == null) throw new IllegalArgumentException();
//        // indien de heap leeg is: eerst initialiseren
//        if (this.isEmpty())
//            values = new ArrayList<E>();
//
//        values.add(value);//achteraan toevoegen
//        this.bubbleUp();//bubbleUp vanaf de laatste zie slides theorie
//        return true;
//    }

    public boolean addValue(E value) {
        if (value == null)
            throw new IllegalArgumentException("Geen lege waarde toevoegen");
        if (isEmpty())
            values = new ArrayList<>();
        values.add(value);
        this.bubbleUp();
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
        int kind = values.size() - 1;
        int ouder = getIndexOuder(kind);
        while (isValidIndex(ouder) && values.get(kind).compareTo(values.get(ouder)) < 0) {
            verwissel(kind, ouder);
            kind = ouder;
            ouder = getIndexOuder(kind);
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

    private void bubbleDown() {
        int ouder = 0;
        int kindLinks;
        int kindRechts;
        int indexKleinste = ouder;
        while (indexKleinste >= 0) {
            kindLinks = this.getIndexLinkerkind(ouder);
            kindRechts = this.getIndexRechterkind(ouder);
            // zoek kleinste van ouder, kindRechts en kindLinks (indien bestaande)
            if (isValidIndex(kindRechts))
                indexKleinste = geefIndexKleinste(ouder, kindLinks, kindRechts);
            else if (isValidIndex(kindLinks))
                indexKleinste = geefIndexKleinste(ouder, kindLinks);

            // stop iteratie als ouder kleinste is
            if (indexKleinste == ouder) {
                indexKleinste = -1;
            } else {
                // verwissel ouder met kleinste kind en ga verder
                verwissel(indexKleinste, ouder);
                ouder = indexKleinste;
            }
        }
    }

    private int geefIndexKleinste(int een, int twee, int drie) {
        int indexKleinste = geefIndexKleinste(een, twee);
        if (values.get(drie).compareTo(values.get(indexKleinste)) < 0)
            indexKleinste = drie;
        return indexKleinste;
    }

    private int geefIndexKleinste(int een, int twee) {
        if (values.get(een).compareTo(values.get(twee)) < 0)
            return een;
        else
            return twee;
    }


    public ArrayList<E> getPath(E value) {
        // TODO zie oefening 6;
        return null;
    }
}
