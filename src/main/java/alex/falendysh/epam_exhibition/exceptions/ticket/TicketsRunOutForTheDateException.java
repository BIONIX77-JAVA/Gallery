package alex.falendysh.epam_exhibition.exceptions.ticket;

public class TicketsRunOutForTheDateException extends Throwable {
    public TicketsRunOutForTheDateException() {
    }

    public TicketsRunOutForTheDateException(String message) {
        super(message);
    }

    public TicketsRunOutForTheDateException(String message, Throwable cause) {
        super(message, cause);
    }

    public TicketsRunOutForTheDateException(Throwable cause) {
        super(cause);
    }
}
