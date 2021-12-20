package alex.falendysh.epam_exhibition.exceptions.user;

public class InvalidUserNameException extends InvalidUserException {
    public InvalidUserNameException() {
    }

    public InvalidUserNameException(String message) {
        super(message);
    }

    public InvalidUserNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidUserNameException(Throwable cause) {
        super(cause);
    }
}
