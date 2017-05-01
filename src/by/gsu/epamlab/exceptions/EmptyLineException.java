package by.gsu.epamlab.exceptions;

/**
 * Created by Torus on 01.05.2017.
 */
public class EmptyLineException extends IllegalArgumentException {
    public EmptyLineException(String cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
