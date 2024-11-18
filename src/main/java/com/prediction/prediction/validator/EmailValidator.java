//package com.prediction.prediction.validator;
//
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//
//public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
//    @Override
//    public boolean isValid(String value, ConstraintValidatorContext context) {
//        return value != null && value.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
//    }
//}
