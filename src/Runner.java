import by.gsu.epamlab.beans.Purchase;
import by.gsu.epamlab.PurchasesList;
import java.util.Comparator;

public class Runner {
    public static void main(String[] args) {
        PurchasesList purchasesList = new PurchasesList(args[Constants.FILE_NAME_INDEX]);
        purchasesList.printPurchases();

        PurchasesList addonPurchasesList = new PurchasesList(args[Constants.ADDON_FILE_NAME_INDEX]);

        purchasesList.insert(Constants.ZERO_INDEX, addonPurchasesList.getListOfPurchases().get(addonPurchasesList.getListOfPurchases().size() - Constants.ONE));

        purchasesList.insert(Constants.ONE_THOUSAND_INDEX, addonPurchasesList.getListOfPurchases().get(Constants.ZERO_INDEX));

        purchasesList.insert(Constants.SECOND_INDEX, addonPurchasesList.getListOfPurchases().get(Constants.SECOND_INDEX));

        purchasesList.delete(Constants.INDEX_THREE);
        purchasesList.delete(Constants.INDEX_TEN);
        purchasesList.delete(Constants.INDEX_MINUS_FIVE);

        purchasesList.printPurchases();
        try {
           purchasesList.sortPurchases((Comparator<Purchase>) Class.forName(Constants.BY_GSU_EPAMLAB_COMPARATORS + args[2]).newInstance());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.err.println(Constants.WRONG_COMPARATOR);
        }

        purchasesList.printPurchases();
        System.out.println(Constants.SEARCH_RESULTS);
        purchasesList.binarySearch(addonPurchasesList, Constants.FIRST_SEARCH_INDEX);
        purchasesList.binarySearch(addonPurchasesList, Constants.SECOND_SEARCH_INDEX);
    }
}
