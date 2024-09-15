package com.mas.kinga.validation.person;

import com.mas.kinga.models.Person;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PersonBirthdateValidator implements ConstraintValidator<ValidPersonBirthdate, Person> {
    @Override
    public boolean isValid(Person person, ConstraintValidatorContext context) {
        if(person == null || person.getBirthdate() == null || ChronoUnit.YEARS.between(person.getBirthdate(), LocalDate.now())<Person.MIN_AGE){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Person age must be at least " + Person.MIN_AGE + ". Check the birthdate again.")
                    .addPropertyNode("person - age")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

    @Override
    public void initialize(ValidPersonBirthdate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
