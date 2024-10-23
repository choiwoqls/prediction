package com.prediction.prediction.controller;

import com.prediction.prediction.common.exception.Exception;
import com.prediction.prediction.common.exception.InvalidateUserException;
import com.prediction.prediction.response.CommonResponse;
import com.prediction.prediction.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {

    private final ResponseService responseService;

    @ExceptionHandler(InvalidateUserException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private CommonResponse invalidateUserException(InvalidateUserException e){
        log.info(e.getMessage());
        return responseService.getErrorResponse(Exception.INVALIDATE_USER.getCode(), Exception.INVALIDATE_USER.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private CommonResponse methodArgumentNotValidException(MethodArgumentNotValidException e){
        log.info(e.getMessage());

        return responseService.getErrorResponse(400, e.getMessage());
    }



}
