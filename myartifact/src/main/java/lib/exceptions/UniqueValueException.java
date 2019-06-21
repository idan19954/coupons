package lib.exceptions;

public class UniqueValueException extends Exception {
    private String errorValue;

    public UniqueValueException( String message, String errorValue ) {
        super( message );
        this.errorValue = errorValue;
    }
}
