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
        try {
            switch (valuesLength){
                case 3:
                    purchase = new Purchase(values[Constants.NAME_INDEX],
                            Integer.parseInt(values[Constants.PRICE_INDEX]),
                            Integer.parseInt(values[Constants.UNITS_NUMBER_INDEX]));
                    break;
                case 4:
                    purchase = new PriceDiscountPurchase(values[Constants.NAME_INDEX],
                            Integer.parseInt(values[Constants.PRICE_INDEX]),
                            Integer.parseInt(values[Constants.UNITS_NUMBER_INDEX]),
                            Integer.parseInt(values[Constants.DISCOUNT_INDEX]));
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
