package by.gsu.epamlab.exceptions;

import by.gsu.epamlab.Constants;

public class CsvLineException extends WrongCsvException{
    private String csvLine;

    public CsvLineException(String csvLine, Exception exception) {
        super(exception);
        this.csvLine = csvLine;
    }
     public CsvLineException(String csvLine, String cause){
        super(cause);
        this.csvLine = csvLine;
     }

    @Override
    public String toString() {
        return csvLine + Constants.ERROR_EXCEPTION_DELIMITER  + getMessage();
    }
}
