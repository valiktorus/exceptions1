package by.gsu.epamlab;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class PurchasesList {

    public static final String ERROR_WRONG_NUMBER = "wrong number of arguments";
    public static final String ERROR_FORMAT_NUMBER = "wrong format of arguments";
    public static final int GENERAL_PURCHASE_LENGTH = 3;
    public static final int DISCOUNT_PURCHASE_LENGTH = 4;
    public static final String SRC = "src/";
    public static final String CSV = ".csv";
    public static final String ERROR_WRONG_SOURCE = "error wrong source";

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

            scanner = new Scanner(new FileReader(SRC + fileName + CSV));
            scanner.useLocale(Locale.ENGLISH);
                purchases = getPurchases(scanner);


        } catch (FileNotFoundException e) {
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
    }

    public void delete(int index){
        purchases.remove(index);
    }

    public Byn totalCost(){
        Byn resultCost = new Byn(0);
        for (Purchase purchase: purchases) {
            resultCost.add(purchase.getCost());
        }
        return resultCost;
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
        String[] values = csvLine.split(Constants.DELIMITER);
        int valuesLength = values.length;
        if (valuesLength != GENERAL_PURCHASE_LENGTH && valuesLength != DISCOUNT_PURCHASE_LENGTH) {
            throw new CsvLineException(ERROR_WRONG_NUMBER, csvLine);
        }
        String name = values[Constants.NAME_INDEX];

        try {
            int price = Integer.parseInt(values[Constants.PRICE_INDEX]);
            int unitsNumber = Integer.parseInt(values[Constants.UNITS_NUMBER_INDEX]);
            if (values.length == DISCOUNT_PURCHASE_LENGTH) {
                int discount = Integer.parseInt(values[Constants.DISCOUNT_INDEX]);
                return new PriceDiscountPurchase(name, price, unitsNumber, discount);
            } else {
                return new Purchase(name, price, unitsNumber);
            }
        } catch (NumberFormatException e) {
            throw new CsvLineException(ERROR_FORMAT_NUMBER, csvLine);
        } catch (IllegalArgumentException e) {
            throw new CsvLineException(e.getMessage(), csvLine);
        }
    }
    public Byn getTotalCost(){
        Byn totalCost = new Byn(0);
        for (Purchase purchase: purchases) {
            totalCost.add(purchase.getCost());
        }
        return totalCost;
    }

    public void printPurchases(){
        System.out.printf("%10s%10s%10s%10s%10s%n", "Name" , "Price" , "Number", "Discount", "Cost");
        for (Purchase purchase: purchases) {

            System.out.println(purchase);
        }
        System.out.printf("%10s%40s%n", "Total cost", getTotalCost());
    }

    public void sortPurchases(Comparator<Purchase> comparator){
        Collections.sort(purchases, comparator);
    }

}
