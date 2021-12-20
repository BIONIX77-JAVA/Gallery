package alex.falendysh.epam_exhibition.exceptions.ticket;

public class NoSuchTicketException extends Throwable {

    public NoSuchTicketException() {
    }

    public NoSuchTicketException(String message) {
        super(message);
    }

    public NoSuchTicketException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchTicketException(Throwable cause) {
        super(cause);
    }
}
