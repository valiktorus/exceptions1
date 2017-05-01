package by.gsu.epamlab.beans;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.exceptions.EmptyLineException;
import by.gsu.epamlab.exceptions.NonPositiveArgumentException;
import by.gsu.epamlab.exceptions.NumField;

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

    public void setName(String name){
        if (Constants.EMPTY_LINE.equals(name)){
            throw new EmptyLineException(Constants.ERROR_EMPTY_NAME);
        }
        this.name = name;
    }

    public Byn getPrice() {
        return price;
    }

    public void setPrice(Byn price){
        int coinsPrice = price.getPriceInCoins();
        if (coinsPrice <= Constants.ZERO){
            throw new NonPositiveArgumentException(coinsPrice, NumField.PRICE);
        }
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number){
        if (number <= Constants.ZERO){
            throw new NonPositiveArgumentException(number, NumField.NUMBER);
        }
        this.number = number;
    }

    public Byn getCost(){
        return new Byn(price).mul(number);
    }

    protected String fieldsToString(){
        return name + Constants.DELIMITER + price + Constants.DELIMITER + number;
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
