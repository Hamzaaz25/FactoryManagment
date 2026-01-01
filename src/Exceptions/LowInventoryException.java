package Exceptions;

public class LowInventoryException extends Exception {
    public LowInventoryException(String message) {
        super(message);
    }
}
