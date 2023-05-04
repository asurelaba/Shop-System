package entities.customexceptions;

import java.time.DateTimeException;

public class InvalidBestBeforeException extends DateTimeException {
    public InvalidBestBeforeException(String message) {
        super(message);
    }
}
