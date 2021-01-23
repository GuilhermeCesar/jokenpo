package ai.auctus.jokenpo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class JogadorException extends ResponseStatusException {

    public JogadorException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
