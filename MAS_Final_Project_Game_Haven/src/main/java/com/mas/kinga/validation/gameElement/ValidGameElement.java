package com.mas.kinga.validation.gameElement;

import com.mas.kinga.validation.game.GameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GameElementValidator.class)
public @interface ValidGameElement {
    String message() default "Invalid game type associations";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
