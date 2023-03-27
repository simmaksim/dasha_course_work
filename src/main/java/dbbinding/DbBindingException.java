package dbbinding;

public class DbBindingException extends Exception{
    public DbBindingException() {
    }

    public DbBindingException(String message) {
        super(message);
    }

    public DbBindingException(String message, Throwable cause) {
        super(message, cause);
    }

    public DbBindingException(Throwable cause) {
        super(cause);
    }
}
