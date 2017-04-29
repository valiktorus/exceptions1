package by.gsu.epamlab;

public class PurchaseFactory {
    public static Purchase getPurchase(String line) throws CsvLineException{
        String[] values = line.split(Constants.DELIMITER);
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
                    throw new CsvLineException(Constants.ERROR_WRONG_NUMBER, line);
            }
        } catch (NumberFormatException e) {
            throw new CsvLineException(Constants.ERROR_FORMAT_NUMBER, line);
        } catch (IllegalArgumentException e) {
            throw new CsvLineException(e.getMessage(), line);
        }
        return purchase;
    }
}
