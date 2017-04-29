package by.gsu.epamlab.comparators;

import by.gsu.epamlab.Purchase;

import java.util.Comparator;

/**
 * Created by Torus on 30.04.2017.
 */
public class SearchComparator implements Comparator<Purchase> {
    @Override
    public int compare(Purchase first, Purchase second) {
        return first.getName().compareTo(second.getName()) +
                first.getClass().getSimpleName().compareTo(second.getClass().getSimpleName()) +
                first.getCost().compareTo(second.getCost());
    }
}
