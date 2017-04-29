package by.gsu.epamlab;

public class Purchase {



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
        if (Constants.EMPTY_LINE.equals(name)){
            throw new IllegalArgumentException(Constants.ERROR_EMPTY_NAME);
        }
        this.name = name;
    }

    public Byn getPrice() {
        return price;
    }

    public void setPrice(Byn price) throws IllegalArgumentException{
        if (price.getPriceInCoins() <= Constants.ZERO){
            throw new IllegalArgumentException(Constants.NON_POSITIVE_VALUE + price.getPriceInCoins() + Constants.IN_PRICE);
        }
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) throws IllegalArgumentException{
        if (number == Constants.ZERO){
            throw new IllegalArgumentException(Constants.ERROR_NULL_NUMBER);
        }
        this.number = number;
    }

    public Byn getCost(){
        return new Byn(price).mul(number);
    }

    protected String fieldsToString(){
        return name + Constants.DELIMITER + price + Constants.DELIMITER + number;
    }

    public String getCheckLine(){
        return String.format(Constants.CHECK_LINE_FORMAT, name, price, number, Constants.MINUS, getCost());
    }
    @Override
    public String toString() {
        return fieldsToString() + Constants.DELIMITER + getCost();
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
