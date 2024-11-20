package com.prediction.prediction.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class MinDaysFromTodayValidator implements ConstraintValidator<MinDaysFromToday, LocalDate> {

    private int days;

    @Override
    public void initialize(MinDaysFromToday constraintAnnotation) {
        this.days = constraintAnnotation.days();
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // @NotNull로 별도 검증
        }
        LocalDate minDate = LocalDate.now().plusDays(days); // 현재 날짜 + 최소 일수
        return value.isEqual(minDate) || value.isAfter(minDate);
    }
}
