package by.gsu.epamlab.exceptions;

import by.gsu.epamlab.Constants;

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
        return Constants.NON_POSITIVE_VALUE + nonPositiveValue + Constants.IN + numField;
    }
}
