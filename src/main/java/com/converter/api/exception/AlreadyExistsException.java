package com.converter.api.exception;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Setter
@Getter
@Builder
@ResponseStatus(value = HttpStatus.CONFLICT)
public class AlreadyExistsException extends RuntimeException {
    private final String message;
    private final String code;
    public AlreadyExistsException(String message, String code) {
        super(message);
        this.code = code;
        this.message = message;
    }
}