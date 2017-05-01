package by.gsu.epamlab.beans;


import by.gsu.epamlab.Constants;
import by.gsu.epamlab.exceptions.NonPositiveArgumentException;
import by.gsu.epamlab.exceptions.NumField;

public class PriceDiscountPurchase extends Purchase {
    private Byn discount;

    public PriceDiscountPurchase() {
        super();
    }

    public PriceDiscountPurchase(String name, int price, int unitsNumber, int discount) {
        super(name, price, unitsNumber);
        if (discount > Constants.ZERO && discount < Constants.MAX_DISCOUNT) {
            this.discount = new Byn(discount);
        }else {
            throw new NonPositiveArgumentException(discount, NumField.DISCOUNT);
        }
    }
    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + Constants.DELIMITER + discount;
    }

    @Override
    public String getCheckLine() {
        return String.format(Constants.CHECK_LINE_FORMAT,getName(), getPrice(), getNumber(), discount, getCost());
    }

    @Override
    public Byn getCost() {
        return new Byn(getPrice()).sub(discount).mul(getNumber());
    }
}
