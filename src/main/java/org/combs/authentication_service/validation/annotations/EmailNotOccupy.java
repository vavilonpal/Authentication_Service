package org.combs.authentication_service.validation.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.combs.authentication_service.validation.validators.UserByEmailOccupyValidator;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserByEmailOccupyValidator.class)
@Documented
public @interface EmailNotOccupy {
    String message() default "Email already uses";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
