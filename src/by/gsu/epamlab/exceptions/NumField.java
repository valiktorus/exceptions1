package by.gsu.epamlab.exceptions;

public enum NumField {
    NUMBER, PRICE, DISCOUNT;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
