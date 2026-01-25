package Exceptions;

public class WrongPasswordException extends Exception {
    public WrongPasswordException(String message) {
        super(
                "WRONG Password"
        );
    }
}
