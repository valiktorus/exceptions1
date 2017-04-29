import by.gsu.epamlab.PurchasesList;
import by.gsu.epamlab.comparators.PurchaseComparatorV1;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        PurchasesList purchasesList = new PurchasesList("in");

        purchasesList.printPurchases();
        purchasesList.sortPurchases(new PurchaseComparatorV1());
        purchasesList.printPurchases();


    }
}
