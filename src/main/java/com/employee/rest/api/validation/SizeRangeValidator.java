package com.employee.rest.webservices.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SizeRangeValidator implements ConstraintValidator<SizeRange, String> {

    private int min;
    private int max;

    @Override
    public void initialize(SizeRange constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Consider null as valid (use @NotNull for null checks)
        }
        return value.length() >= min && value.length() <= max; // Check length
    }
}
