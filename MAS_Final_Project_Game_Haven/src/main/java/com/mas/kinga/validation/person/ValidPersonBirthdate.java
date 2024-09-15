package com.mas.kinga.validation.person;

import com.mas.kinga.validation.gameElement.GameElementValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PersonBirthdateValidator.class)
public @interface ValidPersonBirthdate {
    String message() default "Invalid person birthdate";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
