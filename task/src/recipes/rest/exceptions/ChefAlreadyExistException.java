package recipes.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ChefAlreadyExistException extends RuntimeException {
    private final String message;

    public ChefAlreadyExistException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}