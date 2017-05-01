package by.gsu.epamlab.exceptions;

public class EmptyLineException extends IllegalArgumentException {
    public EmptyLineException(String cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
