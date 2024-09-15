package com.mas.kinga.validation.order;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StatusDateValidator.class)
public @interface ValidStatusDate {
    String message() default "Anul date can only be set, when the order was already finalized";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
