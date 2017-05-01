package by.gsu.epamlab.beans;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.beans.PriceDiscountPurchase;
import by.gsu.epamlab.beans.Purchase;
import by.gsu.epamlab.exceptions.CsvLineException;

public class PurchaseFactory {
    public static Purchase getClassFromFactory(String csvLine) throws CsvLineException {
        String[] values = csvLine.split(Constants.DELIMITER);
        int valuesLength = values.length;
        Purchase purchase;
        String name;
        int price;
        int number;
        int discount;
        try {
            switch (valuesLength){
                case 3:
                    name = values[Constants.NAME_INDEX];
                    price = Integer.parseInt(values[Constants.PRICE_INDEX]);
                    number = Integer.parseInt(values[Constants.UNITS_NUMBER_INDEX]);
                    purchase = new Purchase(name, price, number);
                    break;
                case 4:
                    name = values[Constants.NAME_INDEX];
                    price = Integer.parseInt(values[Constants.PRICE_INDEX]);
                    number = Integer.parseInt(values[Constants.UNITS_NUMBER_INDEX]);
                    discount = Integer.parseInt(values[Constants.DISCOUNT_INDEX]);
                    purchase = new PriceDiscountPurchase(name, price, number, discount);
                    break;
                default:
                    throw new CsvLineException(csvLine, Constants.ERROR_WRONG_NUMBER);
            }
        } catch (NumberFormatException e) {
            throw new CsvLineException(csvLine, Constants.ERROR_FORMAT_NUMBER);
        } catch (IllegalArgumentException e) {
            throw new CsvLineException(csvLine, e);
        }
        return purchase;
    }
}
