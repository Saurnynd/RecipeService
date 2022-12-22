package recipes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class NoAccessException extends RuntimeException {
    private final String message;

    public NoAccessException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
