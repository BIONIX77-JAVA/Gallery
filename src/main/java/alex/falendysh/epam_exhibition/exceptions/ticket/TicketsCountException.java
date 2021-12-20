package alex.falendysh.epam_exhibition.exceptions.ticket;

public class TicketsCountException extends RuntimeException {
    public TicketsCountException() {
    }

    public TicketsCountException(String message) {
        super(message);
    }

    public TicketsCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public TicketsCountException(Throwable cause) {
        super(cause);
    }
}
