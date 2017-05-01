import by.gsu.epamlab.beans.Purchase;
import by.gsu.epamlab.PurchasesList;
import by.gsu.epamlab.comparators.PurchaseComparatorBuilder;
import by.gsu.epamlab.exceptions.NotSortedCollectionException;

public class Runner {
    public static void main(String[] args) {
        PurchaseComparatorBuilder.buildPurchaseComparator(args[2]);
        PurchasesList purchasesList = new PurchasesList(args[Constants.FILE_NAME_INDEX]);

        printTable(purchasesList);

        PurchasesList addonPurchasesList = new PurchasesList(args[Constants.ADDON_FILE_NAME_INDEX]);

        purchasesList.insert(Constants.ZERO_INDEX, addonPurchasesList.getPurchases().get(addonPurchasesList.getPurchases().size() - Constants.ONE));
        purchasesList.insert(Constants.ONE_THOUSAND_INDEX, addonPurchasesList.getPurchases().get(Constants.ZERO_INDEX));
        purchasesList.insert(Constants.SECOND_INDEX, addonPurchasesList.getPurchases().get(Constants.SECOND_INDEX));

        deletePurchaseFromList(purchasesList, Constants.INDEX_THREE);
        deletePurchaseFromList(purchasesList, Constants.INDEX_TEN);
        deletePurchaseFromList(purchasesList, Constants.INDEX_MINUS_FIVE);

        printTable(purchasesList);

        purchasesList.sort();

        printTable(purchasesList);

        System.out.println(Constants.SEARCH_RESULTS);
        try {
            Purchase firstRequiredPurchase = addonPurchasesList.getPurchases().get(Constants.FIRST_SEARCH_INDEX);
            int firstRequiredIndex = purchasesList.binarySearch(firstRequiredPurchase);
            printSearchResult(firstRequiredIndex, firstRequiredPurchase);

            Purchase secondRequiredPurchase = addonPurchasesList.getPurchases().get(Constants.SECOND_SEARCH_INDEX);
            int secondRequiredIndex = purchasesList.binarySearch(secondRequiredPurchase);
            printSearchResult(secondRequiredIndex, secondRequiredPurchase);
        } catch (NotSortedCollectionException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void printSearchResult(int index, Purchase purchase){
        System.out.print(purchase);
        if (index >= Constants.ZERO){
            System.out.printf(Constants.SEARCH_FORMAT, Constants.IS_FOUNDED_AT_POSITION, index);
        }else {
            System.out.println(Constants.ISN_T_FOUND);
        }
    }

    private static void printTable(PurchasesList purchasesList){
        System.out.println(purchasesList.toTable());
    }

    private static void deletePurchaseFromList(PurchasesList purchasesList, int index){
        purchasesList.delete(index);
    }
}
