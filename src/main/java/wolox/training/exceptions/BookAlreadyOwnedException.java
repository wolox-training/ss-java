package wolox.training.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookAlreadyOwnedException extends ResponseStatusException {

    public BookAlreadyOwnedException(HttpStatus status) {
        super(status);
    }

    public BookAlreadyOwnedException(HttpStatus status,
            String reason) {
        super(status, reason);
    }

    public BookAlreadyOwnedException(HttpStatus status,
            String reason, Throwable cause) {
        super(status, reason, cause);
    }

    public BookAlreadyOwnedException(int rawStatusCode, String reason, Throwable cause) {
        super(rawStatusCode, reason, cause);
    }
}
