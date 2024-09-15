package com.mas.kinga.validation.userDynamic;

import com.mas.kinga.models.ROLE;
import com.mas.kinga.models.User;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.EnumSet;
import static com.mas.kinga.models.ROLE.ADMIN;
import static com.mas.kinga.models.ROLE.CUSTOMER;

public class UserDynamicValidator implements ConstraintValidator<ValidUserDynamic, User> {
    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        EnumSet<ROLE> roles = EnumSet.of(CUSTOMER, ADMIN);
        ROLE userRole = user.getRole();

        //rola musi być podana oraz zawierać się w wartościach CUSTOMER, ADMIN
        if (userRole == null || !roles.contains(userRole)) {
            return false;
        }

        //ADMIN - logi
        if ((userRole.equals(ADMIN)) && (user.getOrders() != null || user.getGameReviews() != null || user.getLogs() == null)) {
            return false;
        }

        //CUSTOMER - gameReviews, Orders - nie muszą być wypełnione
        if((userRole.equals(CUSTOMER)) && user.getLogs() != null){
            return false;
        }

        return true;
    }

    @Override
    public void initialize(ValidUserDynamic constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
