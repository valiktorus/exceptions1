package by.gsu.epamlab;

import by.gsu.epamlab.beans.Byn;
import by.gsu.epamlab.beans.Purchase;
import by.gsu.epamlab.beans.PurchaseFactory;
import by.gsu.epamlab.comparators.SearchComparator;
import by.gsu.epamlab.exceptions.CsvLineException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PurchasesList {
    private List<Purchase> purchases;

    public List<Purchase> getListOfPurchases() {
        return purchases;
    }

    public PurchasesList() {
        this.purchases = new ArrayList<>();
    }

    public PurchasesList(String fileName){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader(Constants.SRC + fileName + Constants.CSV));
            scanner.useLocale(Locale.ENGLISH);
            purchases = getPurchases(scanner);
        } catch (IOException e) {
            purchases = new ArrayList<>();
        } finally {
            if (scanner != null){
                scanner.close();
            }
        }
    }

    public void insert(int index, Purchase purchase){
        try {
            purchases.add(index, purchase);
        } catch (IndexOutOfBoundsException e){
            purchases.add(purchase);
        }
        SearchComparator.collectionIsNotSorted();
    }

    public void delete(int index){
        if (isIndexCorrect(index)){
            purchases.remove(index);
        }
    }

    private boolean isIndexCorrect(int index){
        return !(index < 0 || (purchases.size() - 1) < index);
    }

    private List<Purchase> getPurchases(Scanner scanner){
        List<Purchase> purchases = new ArrayList<>();
            while (scanner.hasNextLine()){
                try {
                    Purchase purchase = getPurchase(scanner);
                    purchases.add(purchase);
                } catch (CsvLineException e) {
                    System.err.println(e);
                }
            }
            return purchases;
    }

    public Purchase getPurchase(Scanner scanner) throws CsvLineException {
        String csvLine = scanner.nextLine();
        return PurchaseFactory.getClassFromFactory(csvLine);
    }

    public Byn getTotalCost(){
        Byn totalCost = new Byn(Constants.ZERO);
        for (Purchase purchase: purchases) {
            totalCost.add(purchase.getCost());
        }
        return totalCost;
    }

    public void printPurchases(){
        System.out.printf(Constants.TITLE_LINE_FORMAT, Constants.NAME, Constants.PRICE, Constants.NUMBER, Constants.DISCOUNT, Constants.COST);
        for (Purchase purchase: purchases) {
            System.out.println(purchase.getCheckLine());
        }
        System.out.printf(Constants.TOTAL_PRICE_FORMAT, Constants.TOTAL_COST, getTotalCost());
    }

    public void binarySearch(PurchasesList list, int index){
        if (!SearchComparator.isIsCollectionSorted()){
            System.err.println("Collection is not sorted");
            return;
        }
        Purchase requiredPurchase = list.getListOfPurchases().get(index);
        Collections.binarySearch(purchases, requiredPurchase, new SearchComparator());
        int requiredIndex = Collections.binarySearch(purchases, requiredPurchase, new SearchComparator());
        System.out.print(requiredPurchase);
        if (requiredIndex >= Constants.ZERO){
            System.out.printf(Constants.SEARCH_FORMAT, Constants.IS_FOUNDED_AT_POSITION, requiredIndex);
        }else {
            System.out.println(Constants.ISN_T_FOUND);
        }
    }

    public void sortPurchases(Comparator<Purchase> comparator){
        Collections.sort(purchases, comparator);
        SearchComparator.collectionIsNotSorted();
    }
}
