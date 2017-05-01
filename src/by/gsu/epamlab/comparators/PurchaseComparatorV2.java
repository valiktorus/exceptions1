package by.gsu.epamlab.comparators;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.beans.Purchase;

import java.util.Comparator;

public class PurchaseComparatorV2 implements Comparator<Purchase> {
    @Override
    public int compare(Purchase first, Purchase second) {
        int compareResult = first.getName().compareTo(second.getName());
        if (compareResult == Constants.ZERO){
            if (first.getClass() == second.getClass()){
                compareResult = first.getCost().getPriceInCoins() - second.getCost().getPriceInCoins();
            }else {
                compareResult = Constants.PURCHASE.equals(first.getClass().getSimpleName().toLowerCase()) ? Constants.MINUS_ONE : Constants.ONE;
            }
        }
        return compareResult;
    }
}
