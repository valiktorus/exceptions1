package by.gsu.epamlab.comparators;

import by.gsu.epamlab.Purchase;

import java.util.Comparator;

public class PurchaseComparatorV2 implements Comparator<Purchase> {
    @Override
    public int compare(Purchase first, Purchase second) {
        int compareResult = first.getName().compareTo(second.getName());
        if (compareResult == 0){
            if (first.getClass() == second.getClass()){
                compareResult = first.getCost().getPriceInCoins() - second.getCost().getPriceInCoins();
            }else {
                compareResult = "purchase".equals(first.getClass().getSimpleName().toLowerCase()) ? -1 : 1;
            }
        }
        return compareResult;
    }
}
