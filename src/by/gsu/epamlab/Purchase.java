package by.gsu.epamlab;

public class Purchase {

    public static final String NON_POSITIVE_VALUE = "non positive value ";
    public static final String ERROR_NULL_NUMBER = "Error null units number";
    public static final String IN_PRICE = " in price";

    private String name;
    private Byn price;
    private int number;

    public Purchase() {
        super();
        name = null;
    }

    public Purchase(String name, int price, int number) {
        super();
        setName(name);
        setPrice(new Byn(price));
        setNumber(number);
    }

    public String getName() {
        return name;
    }

    public void setName(String name)  throws IllegalArgumentException{
        if (name == null){
            throw new IllegalArgumentException(Constants.ERROR_NULL_NAME);
        }
        if ("".equals(name)){
            throw new IllegalArgumentException(Constants.ERROR_EMPTY_NAME);
        }
        this.name = name;
    }

    public Byn getPrice() {
        return price;
    }

    public void setPrice(Byn price) throws IllegalArgumentException{
        if (price.getPriceInCoins() <= 0){
            throw new IllegalArgumentException(NON_POSITIVE_VALUE + price.getPriceInCoins() + IN_PRICE);
        }
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) throws IllegalArgumentException{
        if (number == 0){
            throw new IllegalArgumentException(ERROR_NULL_NUMBER);
        }
        this.number = number;
    }

    public Byn getCost(){
        return new Byn(price).mul(number);
    }

    protected String fieldsToString(){
        return String.format("%-10s%10s%10s", name, price, number);
    }

    @Override
    public String toString() {
        return String.format("%s%10s%10s",fieldsToString(), "-", getCost());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Purchase)) return false;

        Purchase purchase = (Purchase) o;

        if (!name.equals(purchase.name)) return false;
        return price.equals(purchase.price);
    }
}
