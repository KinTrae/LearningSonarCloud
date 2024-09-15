package com.mas.kinga.validation.order;

import com.mas.kinga.models.ORDERS_STATUS;
import com.mas.kinga.models.Order;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StatusDateValidator implements ConstraintValidator<ValidStatusDate, Order> {
    @Override
    public boolean isValid(Order order, ConstraintValidatorContext context) {
        if((order.getStatus().equals(ORDERS_STATUS.DONE) && order.getSendDate() == null) ||
                (order.getStatus().equals(ORDERS_STATUS.CANCELLED) && order.getSendDate() == null) ||
                (order.getStatus().equals(ORDERS_STATUS.CANCELLED) && order.getCancelDate() == null) ||
                (!order.getStatus().equals(ORDERS_STATUS.CANCELLED) && order.getCancelDate() != null) ||
                (order.getStatus().equals(ORDERS_STATUS.IN_PROGRESS) && order.getOrderDate() == null)
        ){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("When a status is assigned, appropriate date must be set")
                    .addPropertyNode("ORDER_STATUS")
                    .addConstraintViolation();
            return false;
        }
        if(order.getStatus().equals(ORDERS_STATUS.CANCELLED) && (order.getCancelDate().before(order.getSendDate()))){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Anul-date > send_date > in_progress_date")
                    .addPropertyNode("ORDER_STATUS dates")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

    @Override
    public void initialize(ValidStatusDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
