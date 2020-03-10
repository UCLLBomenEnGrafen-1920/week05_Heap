package domain;

import java.util.List;

public class Checker {

    /**
     * Controleert of lijst givenValues een geldige minHeap is
     *
     * @param givenValues: de lijst die gecontroleerd moet worden
     */
    public static <E extends Comparable> boolean isValidArrayOfValues(List<E> givenValues) {
        if (givenValues == null || givenValues.size() == 0)
            return false;
        return isValidArrayOfValues(givenValues, 0);
    }

    /**
     * Controleert of lijst givenValues een geldige minHeap is, te vertrekken bij gegeven index
     *
     * @param givenValues: de lijst die gecontroleerd moet worden
     * @param index:       vanwaar de lijst gecontroleerd wordt
     */
    private static <E extends Comparable> boolean isValidArrayOfValues(List<E> givenValues, int index) {
        // als index heeft geen kinderen heeft, is het zeker een minheap
        int indexLinkerkind = 2 * index + 1;
        if (indexLinkerkind >= givenValues.size())
            return true;

        // indien linkerkind bestaat
        // controleren of ouder < kind && rest van boom controleren
        boolean left = givenValues.get(index).compareTo(givenValues.get(indexLinkerkind)) < 0
                && isValidArrayOfValues(givenValues, indexLinkerkind);

        // indien rechterkind bestaat
        // controleren of ouder < kind && rest van boom controleren
        int indexRechterkind = 2 * index + 2;
        boolean right = true;
        if (indexRechterkind < givenValues.size())
            right = givenValues.get(index).compareTo(givenValues.get(indexRechterkind)) < 0
                    && isValidArrayOfValues(givenValues, indexRechterkind);
        return left && right;
    }

}
