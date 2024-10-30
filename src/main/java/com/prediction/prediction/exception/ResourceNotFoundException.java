package com.prediction.prediction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
@AllArgsConstructor
public class ResourceNotFoundException extends Exception {
    private String message = "exceptions.notFound";
    private Object[] args;

    public ResourceNotFoundException(Object... args) {
        this.args = args;
    }
}
