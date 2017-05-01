package by.gsu.epamlab;

import by.gsu.epamlab.beans.Byn;
import by.gsu.epamlab.beans.PriceDiscountPurchase;
import by.gsu.epamlab.beans.Purchase;
import by.gsu.epamlab.beans.PurchaseFactory;
import by.gsu.epamlab.comparators.PurchaseComparatorBuilder;
import by.gsu.epamlab.comparators.SearchComparator;
import by.gsu.epamlab.exceptions.CsvLineException;
import by.gsu.epamlab.exceptions.NotSortedCollectionException;
import by.gsu.epamlab.table.TableEnum;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PurchasesList {
    private List<Purchase> purchases;

    public List<Purchase> getPurchases() {
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
            purchases = setPurchases(scanner);
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
        return !(index < Constants.ZERO || (purchases.size() - Constants.ONE) < index);
    }

    private List<Purchase> setPurchases(Scanner scanner){
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

    public int binarySearch(Purchase purchase) throws NotSortedCollectionException {
        if (!SearchComparator.isCollectionSorted){
            throw new NotSortedCollectionException(Constants.COLLECTION_IS_NOT_SORTED);
        }
        return Collections.binarySearch(purchases, purchase, new SearchComparator());
    }

    public void sort(){
        Collections.sort(purchases, PurchaseComparatorBuilder.getPurchaseComparator());
        SearchComparator.collectionIsSorted();
    }

    public String toTable(){
        StringBuilder table = new StringBuilder();
        table.append(TableEnum.getTableTitle());
        appendPurchasesToTable(table);
        appendTotalPriceToTable(table);
        return table.toString();
    }

    private void appendPurchasesToTable(StringBuilder table){
        for (Purchase purchase: purchases) {
            table.append(String.format(TableEnum.NAME.getFormat(), purchase.getName()));
            table.append(String.format(TableEnum.PRICE.getFormat(), purchase.getPrice()));
            table.append(String.format(TableEnum.NUMBER.getFormat(), purchase.getNumber()));
            String discount;
            if (purchase instanceof PriceDiscountPurchase){
                discount = ((PriceDiscountPurchase) purchase).getDiscount().toString();
            }else {
                discount = Constants.MINUS;
            }
            table.append(String.format(TableEnum.DISCOUNT.getFormat(), discount));
            table.append(String.format(TableEnum.COST.getFormat(), purchase.getCost()));
            table.append(Constants.NEW_LINE);
        }
    }

    private void appendTotalPriceToTable(StringBuilder table){
        table.append(String.format(Constants.TOTAL_PRICE_FORMAT, Constants.TOTAL_COST, getTotalCost()));
    }
}
