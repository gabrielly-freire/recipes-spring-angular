package imd.ufrn.foodrecipes.infrastructure.exceptions;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    private final String message;
    private HttpStatus status;

    public BusinessException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
    
}
