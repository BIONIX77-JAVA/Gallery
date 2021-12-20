package alex.falendysh.epam_exhibition.exceptions;

public class SuchExhibitionHallIsAlreadyExistsException extends Throwable {

    public SuchExhibitionHallIsAlreadyExistsException() {
    }

    public SuchExhibitionHallIsAlreadyExistsException(String message) {
        super(message);
    }

    public SuchExhibitionHallIsAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SuchExhibitionHallIsAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
