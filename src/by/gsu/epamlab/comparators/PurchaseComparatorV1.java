package by.gsu.epamlab.comparators;

import by.gsu.epamlab.beans.PriceDiscountPurchase;
import by.gsu.epamlab.beans.Purchase;

import java.util.Comparator;

public class PurchaseComparatorV1 implements Comparator<Purchase> {
    @Override
    public int compare(Purchase first, Purchase second) {
        int compareResult = first.getName().compareTo(second.getName());
        if (compareResult == 0){
            boolean isFirstInstanceOfPricePurchase = first instanceof PriceDiscountPurchase;
            boolean isSecondInstanceOfPricePurchase = second instanceof PriceDiscountPurchase;

            if (isSameClass(isFirstInstanceOfPricePurchase, isSecondInstanceOfPricePurchase)){
                compareResult = first.getCost().getPriceInCoins() - second.getCost().getPriceInCoins();
            }else {
                compareResult = isFirstInstanceOfPricePurchase ? 1 : -1;
            }
        }
        return compareResult;
    }

    private boolean isSameClass(boolean isFirstInstanceOfPricePurchase, boolean isSecondInstanceOfPricePurchase){
        return (isFirstInstanceOfPricePurchase && isSecondInstanceOfPricePurchase) ||
                (!(isFirstInstanceOfPricePurchase) && !(isSecondInstanceOfPricePurchase));
    }
}
