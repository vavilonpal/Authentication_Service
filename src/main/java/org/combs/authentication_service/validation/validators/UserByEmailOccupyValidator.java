package org.combs.authentication_service.validation.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.combs.authentication_service.service.UserService;
import org.combs.authentication_service.validation.annotations.EmailNotOccupy;
@Slf4j
@RequiredArgsConstructor
public class UserByEmailOccupyValidator implements ConstraintValidator<EmailNotOccupy, String> {
    private final UserService userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        boolean isOccupy = userService.isExistsByEmail(email);
        log.info("Email {} is occupy {}", email, isOccupy);
        return !isOccupy;
    }
}
