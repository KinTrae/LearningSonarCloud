package com.mas.kinga.validation.game;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;



@Documented
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GameValidator.class)
public @interface ValidGame {
    String message() default "Invalid game type associations";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
