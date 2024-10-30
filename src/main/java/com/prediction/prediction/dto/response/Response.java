package com.prediction.prediction.dto.response;

import com.prediction.prediction.enumerations.ResponseType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private ResponseType type;
    private T payload;
}
