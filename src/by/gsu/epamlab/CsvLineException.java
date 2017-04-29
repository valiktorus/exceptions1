package by.gsu.epamlab;

public class CsvLineException extends WrongCsvException{
    private String csvLine;

    public CsvLineException(Exception exception, String csvLine) {
        super(exception);
        this.csvLine = csvLine;
    }
     public CsvLineException(String cause, String csvLine){
        super(cause);
        this.csvLine = csvLine;
     }

    @Override
    public String toString() {
        return csvLine + Constants.ERROR_EXCEPTION_DELIMITER  + getMessage();
    }
}
