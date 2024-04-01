package uz.app.exception;

public class AppForbiddenException extends RuntimeException{
    public AppForbiddenException(String message) {
        super(message);
    }
}
