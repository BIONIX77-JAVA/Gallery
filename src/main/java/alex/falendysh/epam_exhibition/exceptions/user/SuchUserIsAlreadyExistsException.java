package alex.falendysh.epam_exhibition.exceptions.user;

public class SuchUserIsAlreadyExistsException extends Throwable {

    public SuchUserIsAlreadyExistsException() {
    }

    public SuchUserIsAlreadyExistsException(String message) {
        super(message);
    }

    public SuchUserIsAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SuchUserIsAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
