package by.gsu.epamlab;

public class PriceDiscountPurchase extends Purchase {
    public static final String NON_POSITIVE_VALUE = "non positive value ";
    public static final String IN_DISCOUNT = " in discount";
    private Byn discount;

    public PriceDiscountPurchase() {
        super();
    }

    public PriceDiscountPurchase(String name, int price, int unitsNumber, int discount) {
        super(name, price, unitsNumber);
        if (discount > 0 && discount < 100) {
            this.discount = new Byn(discount);
        }else {
            throw new IllegalArgumentException(NON_POSITIVE_VALUE + discount + IN_DISCOUNT);
        }
    }

    @Override
    protected String fieldsToString() {

        return String.format("%s%10s%10s",super.fieldsToString(), discount, getCost());
    }

    @Override
    public String toString() {
        return fieldsToString();
    }

    @Override
    public Byn getCost() {
        return new Byn(getPrice()).sub(discount).mul(getNumber());
    }
}
