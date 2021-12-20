package alex.falendysh.epam_exhibition.exceptions.exhibition;

public class PriceBelowZeroException extends Throwable {
    public PriceBelowZeroException() {
    }

    public PriceBelowZeroException(String message) {
        super(message);
    }

    public PriceBelowZeroException(String message, Throwable cause) {
        super(message, cause);
    }

    public PriceBelowZeroException(Throwable cause) {
        super(cause);
    }
}
