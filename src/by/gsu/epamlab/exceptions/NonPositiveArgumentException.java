package by.gsu.epamlab.exceptions;

public class NonPositiveArgumentException extends IllegalArgumentException {
    int nonPositiveValue;
    NumField numField;

    public NonPositiveArgumentException(String cause) {
        super(cause);
    }

    public NonPositiveArgumentException(int nonPositiveValue, NumField field){
        this.nonPositiveValue = nonPositiveValue;
        this.numField = field;
    }

    @Override
    public String toString() {
        return "\t->non positive value " + nonPositiveValue + " in " + numField;
    }
}
