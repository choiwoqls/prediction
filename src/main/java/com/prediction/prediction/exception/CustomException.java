package com.prediction.prediction.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import lombok.Getter;

@Getter
public class CustomException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;
    private Exception exception;

    public CustomException(Exception e) {
        super(ErrorConstants.INVALID_INPUT__EXCEPTION_KEY, e.getMessage(), Status.BAD_REQUEST);
        this.exception = e;
    }
}
