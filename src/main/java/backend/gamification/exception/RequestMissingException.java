package backend.gamification.exception;

public class RequestMissingException extends Exception{
    public RequestMissingException() {
    }

    public RequestMissingException(String message) {
        super(message);
    }

    public RequestMissingException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestMissingException(Throwable cause) {
        super(cause);
    }

    public RequestMissingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
