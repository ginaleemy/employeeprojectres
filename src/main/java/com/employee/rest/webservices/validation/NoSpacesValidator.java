package com.employee.rest.webservices.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NoSpacesValidator implements ConstraintValidator<NoSpaces, String> {

    @Override
    public void initialize(NoSpaces constraintAnnotation) {
        // Initialization logic, if needed
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Consider null as valid (use @NotNull for null checks)
        }
        return !value.trim().isEmpty();  // Return false if the value is empty or contains only spaces
    }
}
