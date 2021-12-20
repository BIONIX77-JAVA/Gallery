package alex.falendysh.epam_exhibition.exceptions.exhibition;

public class NoSuchExhibitionException extends Throwable {

    public NoSuchExhibitionException() {
    }

    public NoSuchExhibitionException(String message) {
        super(message);
    }

    public NoSuchExhibitionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchExhibitionException(Throwable cause) {
        super(cause);
    }
}
