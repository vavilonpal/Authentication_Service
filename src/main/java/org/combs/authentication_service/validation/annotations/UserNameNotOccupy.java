package org.combs.authentication_service.validation.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.combs.authentication_service.validation.validators.UserNameOccupyValidator;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserNameOccupyValidator.class)
@Documented
public @interface UserNameNotOccupy {
    String message() default "Username already in use";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
