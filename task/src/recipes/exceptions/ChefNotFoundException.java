package recipes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ChefNotFoundException extends RuntimeException {
    private final String message;

    public ChefNotFoundException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
