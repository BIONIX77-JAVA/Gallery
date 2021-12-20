package alex.falendysh.epam_exhibition.exceptions;

/**
 * This exception is supposed to be thrown from FileUtil
 * readProperties() method, in a case when impossible to read property information
 */

public class ImpossibleToReadPropertyFile extends RuntimeException {

    public ImpossibleToReadPropertyFile() {
        super("Failed to read properties form file as");
    }

    public ImpossibleToReadPropertyFile(String message) {
        super(message);
    }

    public ImpossibleToReadPropertyFile(String message, Throwable cause) {
        super(message, cause);
    }
}