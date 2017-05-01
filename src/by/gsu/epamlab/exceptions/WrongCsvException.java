package by.gsu.epamlab.exceptions;

import by.gsu.epamlab.Constants;

public class WrongCsvException extends Exception{
    private String csvLine;

    public WrongCsvException(Exception exception) {
        super(exception);
    }

    public WrongCsvException(String cause){
        super(cause);
    }

    @Override
    public String toString() {
        return csvLine + Constants.ERROR_EXCEPTION_DELIMITER  + getMessage();
    }
}
