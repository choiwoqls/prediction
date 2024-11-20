package com.prediction.prediction.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MinDaysFromTodayValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MinDaysFromToday {String message() default "날짜는 오늘부터 최소 {days}일 이후여야 합니다.";
    int days(); // 최소 날짜 간격
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
