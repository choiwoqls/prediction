package com.prediction.prediction.exception;

import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.prediction.prediction.util.ApiResponse;
import com.prediction.prediction.util.ExceptionUtils;

import java.util.Locale;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableWebMvc
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customException(CustomException ex) {
        System.out.println("customException");
        return ExceptionUtils.handleControllerExceptions(ex.getException());

    }

    @ExceptionHandler(BadRequestAlertException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> badRequestAlert(BadRequestAlertException ex, Locale locale) {
        System.out.println("badRequestAlert");
//        String errorMessage = messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale);
        String errorMessage = ex.getMessage();
        return new ApiResponse<>(errorMessage, (Object) "", HttpStatus.BAD_REQUEST).toResponseEntity();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> methodArgumentNotValid(MethodArgumentNotValidException ex, Locale locale){
        String errorMessage = ex.getMessage();
        return new ApiResponse<>(errorMessage, (Object) "", HttpStatus.BAD_REQUEST).toResponseEntity();
    }
}
