package by.gsu.epamlab.comparators;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.beans.Purchase;

import java.util.Comparator;

public class PurchaseComparatorBuilder {
    private static Comparator<Purchase> purchaseComparator;

    private PurchaseComparatorBuilder() {
    }

    public static Comparator<Purchase> getPurchaseComparator() {
        return purchaseComparator;
    }
    public static void buildPurchaseComparator(String comparatorName){
        if (purchaseComparator != null){
            return;
        }
        String fullComparatorName = Constants.BY_GSU_EPAMLAB_COMPARATORS + comparatorName;

        try {
            Class comparatorClass = Class.forName(fullComparatorName);
            purchaseComparator = (Comparator<Purchase>) comparatorClass.newInstance();
        } catch (Exception e) {
            purchaseComparator = new PurchaseComparatorV1();
        }
    }
}
