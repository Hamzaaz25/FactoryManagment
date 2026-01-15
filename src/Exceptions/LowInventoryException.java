package Exceptions;

public class LowInventoryException extends RuntimeException {
    public LowInventoryException(String message) {
        super(message);
    }
}
