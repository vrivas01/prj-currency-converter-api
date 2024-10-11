package com.converter.api.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Setter
@Getter
@Builder
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerErrorException extends RuntimeException {
    private final String message;
    private final String code;
    public ServerErrorException(String message, String code) {
        super(message);
        this.message = message;
        this.code = code;
    }

}