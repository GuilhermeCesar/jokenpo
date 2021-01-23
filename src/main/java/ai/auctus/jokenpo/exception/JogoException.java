package ai.auctus.jokenpo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class JogoException extends ResponseStatusException {

    public JogoException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
