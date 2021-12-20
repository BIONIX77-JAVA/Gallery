package alex.falendysh.epam_exhibition.exceptions;

/**
 * Is thrown when unable to register postgresql driver
 */
public class UnableToRegisterDriver extends RuntimeException{

    public UnableToRegisterDriver() {super();}

    public UnableToRegisterDriver(String message) {
        super("Failed to register postgresql driver");
    }

    public UnableToRegisterDriver(String message, Throwable cause) {
        super(message, cause);
    }
}