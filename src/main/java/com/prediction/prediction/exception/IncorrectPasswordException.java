package com.prediction.prediction.exception;

public class IncorrectPasswordException extends RuntimeException{
    public  IncorrectPasswordException(String message){
        super(message);
    }
}
