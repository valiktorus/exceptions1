package by.gsu.epamlab.comparators;

import by.gsu.epamlab.beans.Purchase;

import java.util.Comparator;

public class SearchComparator implements Comparator<Purchase> {
    private static boolean isCollectionSorted = false;

    public static boolean isIsCollectionSorted() {
        return isCollectionSorted;
    }

    public static void collectionIsSorted(){
        isCollectionSorted = true;
    }
    public static void collectionIsNotSorted(){
        isCollectionSorted = false;
    }
    @Override
    public int compare(Purchase first, Purchase second) {
        return first.getName().compareTo(second.getName()) +
                first.getClass().getSimpleName().compareTo(second.getClass().getSimpleName()) +
                first.getCost().compareTo(second.getCost());
    }
}
