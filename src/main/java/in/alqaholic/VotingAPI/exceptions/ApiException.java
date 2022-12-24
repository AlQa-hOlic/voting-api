package in.alqaholic.VotingAPI.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class ApiException extends RuntimeException {
    @Getter
    private final String message;
    @Getter
    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public ApiException(String message) {
        super(message);
        this.message = message;
    }

    public ApiException(String message, HttpStatus status) {
        this(message);
        this.status = status;
    }

}
