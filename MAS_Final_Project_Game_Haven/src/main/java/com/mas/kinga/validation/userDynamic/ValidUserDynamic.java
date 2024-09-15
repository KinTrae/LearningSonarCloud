package com.mas.kinga.validation.userDynamic;

import com.mas.kinga.validation.game.GameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserDynamicValidator.class)
public @interface ValidUserDynamic {
    String message() default "Invalid user dynamic";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
