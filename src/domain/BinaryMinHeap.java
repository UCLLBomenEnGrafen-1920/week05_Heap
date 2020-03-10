package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    /**
     * Geeft true als gegeven index een bestaande index is voor deze heap
     */
    private boolean isValidIndex(int index) {
        return index >= 0 && index < values.size();
    }

    /**
     * Geeft index van rechterkind van gegeven index als rechterkind bestaat
     * Geeft -1 terug als rechterkind niet bestaat of gegeven index niet geldig is
     */
    private int getIndexRechterkind(int index) {
        if (!isValidIndex(index))
            return -1;
        int temp = 2 * index + 2;
        return isValidIndex(temp) ? temp : -1;
    }

    /**
     * Geeft index van linkerkind van gegeven index als linkerkind bestaat
     * Geeft -1 als linkerkind niet bestaat of gegeven index niet geldig is
     */
    private int getIndexLinkerkind(int index) {
        if (!isValidIndex(index))
            return -1;
        int temp = 2 * index + 1;
        return isValidIndex(temp) ? temp : -1;
    }

    /**
     * Geeft index van ouder van gegeven index als ouder bestaat
     * Geeft -1 terug als ouder niet bestaat of gegeven index niet geldig is
     */
    private int getIndexOuder(int index) {
        if (!isValidIndex(index) || index == 0)
            return -1;
        return (index - 1) / 2;
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

    /**
     * Verwisselt waarden op index1 en index2 van plaats
     */
    private void verwissel(int index1, int index2) {
        if (!isValidIndex(index1) || !isValidIndex(index2))
            throw new IllegalArgumentException("No valid index for verwissel");
        E temp = values.get(index1);
        values.set(index1, values.get(index2));
        values.set(index2, temp);
        //Collections.swap(values,index1,index2);
    }

    /**
     * Verwijdert het kleinste element van deze binary min heap
     *
     * @return het kleinste element
     */
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
        return geefIndexKleinste(drie, geefIndexKleinste(een, twee));
    }

    private int geefIndexKleinste(int een, int twee) {
        if (values.get(een).compareTo(values.get(twee)) < 0)
            return een;
        else
            return twee;
    }


    public ArrayList<E> getPath(E value) {
        int index = values.indexOf(value);
        if (index == -1)
            return null;
        ArrayList<E> result = new ArrayList<>();
        result.add(value);
        index = getIndexOuder(index);
        while (index >= 0) {
            result.add(0, values.get(index));
            index = getIndexOuder(index);
        }
        return result;
    }



    /**
     * Geef deelboom van deze minheap met gegeven data als wortel
     *
     * @return
     */
    public BinaryMinHeap<E> geefDeelboom(E data) {
        int index = values.indexOf(data);
        if (index < 0)
            return null;
        BinaryMinHeap<E> result = new BinaryMinHeap<>();
        result.addValue(data);
        List<Integer> kinderen = new ArrayList<>();
        List<Integer> kleinkinderen;
        if (getIndexLinkerkind(index) >= 0)
            kinderen.add(getIndexLinkerkind(index));
        if (getIndexRechterkind(index) >= 0)
            kinderen.add(getIndexRechterkind(index));
        while (kinderen.size() > 0) {
            kleinkinderen = new ArrayList<>();
            for (int i : kinderen) {
                // voeg kinderen toe aan result
                result.addValue(values.get(i));
                // bereken voor elk kind het kleinkind en bewaar het
                if (getIndexLinkerkind(i) >= 0)
                    kleinkinderen.add(getIndexLinkerkind(i));
                if (getIndexRechterkind(i) >= 0)
                    kleinkinderen.add(getIndexRechterkind(i));
            }
            // kopieer kleinkinderen naar lijst kinderen
            kinderen = new ArrayList<>();
            for (int i : kleinkinderen) {
                kinderen.add(i);
            }

        }

        return result;

    }

}
