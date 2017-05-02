package by.gsu.epamlab.comparators;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.beans.PriceDiscountPurchase;
import by.gsu.epamlab.beans.Purchase;

import java.util.Comparator;

public class PurchaseComparatorV1 implements Comparator<Purchase> {

    @Override
    public int compare(Purchase first, Purchase second) {
        int compareResult = first.getName().compareTo(second.getName());
        if (compareResult == Constants.ZERO){
            int firstPurchasePriority = getPurchasePriority(first);
            int secondPurchasePriority = getPurchasePriority(second);

            if (firstPurchasePriority == secondPurchasePriority){
                compareResult = first.getCost().compareTo(second.getCost());
            }else {
                compareResult = firstPurchasePriority;
            }
        }
        return compareResult;
    }

    private static int getPurchasePriority(Purchase purchase){
        return purchase instanceof PriceDiscountPurchase ? Constants.ONE : Constants.MINUS_ONE;
    }
}
