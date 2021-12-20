package alex.falendysh.epam_exhibition.exceptions;

/**
 * This exception appears as a higher layer db exception when it is impossible
 * to get a connection from the connection pool
 */
public class NoConnectionToDbException extends RuntimeException{
    public NoConnectionToDbException() {
        super();
    }

    public NoConnectionToDbException(String message) {
        super("Failed to add new connection");
    }

    public NoConnectionToDbException(String message, Throwable cause) {
        super(message, cause);
    }
}
