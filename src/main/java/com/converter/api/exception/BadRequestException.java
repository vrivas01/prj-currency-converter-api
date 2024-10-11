package com.converter.api.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@Builder
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{
    private final String message;
    private final String code;
    public BadRequestException(String message, String code) {
        super(message);
        this.message = message;
        this.code = code;
    }
}