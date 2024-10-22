package com.employee.rest.api.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SizeRangeValidator.class) // Specify the validator class
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER}) // Where this annotation can be applied
@Retention(RetentionPolicy.RUNTIME) // Annotation is available at runtime
public @interface SizeRange {
    String message() default "Size must be between {min} and {max} characters"; // Default error message

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int min(); // Minimum size
    int max(); // Maximum size
}
