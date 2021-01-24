package ai.auctus.jokenpo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PartidaException extends ResponseStatusException {

    public PartidaException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
