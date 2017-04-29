package by.gsu.epamlab.comparators;

import by.gsu.epamlab.PriceDiscountPurchase;
import by.gsu.epamlab.Purchase;

import java.util.Comparator;

public class PurchaseComparatorV1 implements Comparator<Purchase> {
    @Override
    public int compare(Purchase first, Purchase second) {
        int compareResult = first.getName().compareTo(second.getName());
        if (compareResult == 0){
            if (first instanceof PriceDiscountPurchase){
                compareResult = -1;
            }
        }
        return compareResult;
    }
}
