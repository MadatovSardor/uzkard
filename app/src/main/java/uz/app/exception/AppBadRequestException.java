package uz.app.exception;

public class AppBadRequestException extends RuntimeException {
    public AppBadRequestException(String message) {
        super(message);
    }
}
