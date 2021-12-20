package alex.falendysh.epam_exhibition.exceptions.ticket;

public class TicketIsAlreadyPaidException extends Throwable {

    public TicketIsAlreadyPaidException() {
    }

    public TicketIsAlreadyPaidException(String message) {
        super(message);
    }

    public TicketIsAlreadyPaidException(String message, Throwable cause) {
        super(message, cause);
    }

    public TicketIsAlreadyPaidException(Throwable cause) {
        super(cause);
    }
}
