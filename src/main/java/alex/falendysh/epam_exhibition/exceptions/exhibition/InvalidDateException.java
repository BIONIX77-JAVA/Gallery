package alex.falendysh.epam_exhibition.exceptions.exhibition;

public class InvalidDateException extends Throwable {
    public InvalidDateException() {
    }

    public InvalidDateException(String message) {
        super(message);
    }

    public InvalidDateException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDateException(Throwable cause) {
        super(cause);
    }
}
