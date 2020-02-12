package kasianov.fx.exceptions;

public class BaseError extends Exception{
    private String message;

    public BaseError(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
