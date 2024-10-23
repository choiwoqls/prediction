package com.prediction.prediction.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Exception {

    INVALIDATE_USER(1000, "해당하는 사용자가 존재하지 않습니다.");


    private final int code;
    private final String message;
}
