package com.prediction.prediction.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.prediction.prediction.util.ApiResponse;
import com.prediction.prediction.util.ExceptionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
//        String errorMessage = messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale);
        String errorMessage = ex.getMessage();
        return new ApiResponse<>(errorMessage, HttpStatus.BAD_REQUEST).toResponseEntity();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, Locale locale) {
        String errorMessage = messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale);
        return new ApiResponse<>(errorMessage, HttpStatus.NOT_FOUND).toResponseEntity();
    }

    //Valid 실패할 때 발생 (requestBody)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> methodArgumentNotValid(MethodArgumentNotValidException ex, Locale locale){
        System.out.println("???????");
            String firstErrorMessage =  ex.getBindingResult().getFieldErrors()
            .stream()
            .sorted((e1, e2) -> e1.getField().compareTo(e2.getField())) // 필드 이름 기준으로 정렬
            .findFirst() // 첫 번째 에러만 선택
            .map(FieldError::getDefaultMessage) // 첫 번째 에러의 메시지만 추출
            .orElse("Unknown error"); // 에러가 없을 때의 기본 메시지

          return new ApiResponse<>(firstErrorMessage, HttpStatus.BAD_REQUEST).toResponseEntity();
    }

//    //Valid 실패할 때 발생 (requestParam)
//    @ExceptionHandler(HandlerMethodValidationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<?> HandlerMethodValidationException(HandlerMethodValidationException ex, Locale locale){
//
//        System.out.println(Arrays.toString(ex.getDetailMessageArguments().));
//        return new ApiResponse<>(Arrays.toString(ex.getDetailMessageArguments()), HttpStatus.BAD_REQUEST).toResponseEntity();
//    }

    //로그인 시 비밀번호 틀렸을 때 발생
    @ExceptionHandler(IncorrectPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<?> incorrectPasswordException(IncorrectPasswordException ex){
        String errorMessage = ex.getMessage();
        return new ApiResponse<>(errorMessage, HttpStatus.UNAUTHORIZED).toResponseEntity();
    }

    //헤더에 토큰 없을 때 발생
    @ExceptionHandler(MissingTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<?> missingTokenException(MissingTokenException ex){
        String errorMessage = ex.getMessage();
        return new ApiResponse<>(errorMessage, HttpStatus.UNAUTHORIZED).toResponseEntity();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> notFoundException(NotFoundException ex){
        String errorMessage = ex.getMessage();
        return new ApiResponse<>(errorMessage, HttpStatus.NOT_FOUND).toResponseEntity();
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<?> unauthorizedException(UnauthorizedException ex){
        String errorMessage = ex.getMessage();
        return new ApiResponse<>(errorMessage, HttpStatus.UNAUTHORIZED).toResponseEntity();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
        String errorMessage = "parameter is missing";
        return new ApiResponse<>(errorMessage, HttpStatus.BAD_REQUEST).toResponseEntity();
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, Locale locale) throws JsonProcessingException {
        String message = ex.getMessage();
        Object error = ex.getMessage();

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ex.getClass().getSimpleName().equals("InternalAuthenticationServiceException"))
            status = HttpStatus.UNAUTHORIZED;

        if (ex.getClass().getSimpleName().equals("HttpMessageNotReadableException")) {
            status = HttpStatus.BAD_REQUEST;
            message = "Malformed JSON request format";
        }

        String errorMessage = messageSource.getMessage("exceptions.validation.server", null, locale);
        return new ApiResponse<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR).toResponseEntity();
    }
}
