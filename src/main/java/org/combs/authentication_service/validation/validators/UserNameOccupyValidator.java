package org.combs.authentication_service.validation.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.combs.authentication_service.service.UserService;
import org.combs.authentication_service.validation.annotations.UserNameNotOccupy;

@Slf4j
@RequiredArgsConstructor
public class UserNameOccupyValidator implements ConstraintValidator<UserNameNotOccupy, String> {
    private final UserService userService;
    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        boolean usernameIsOccupy = userService.isExistsByUsername(username);
        log.info("User by username: {} is occupy: {}", username, usernameIsOccupy);
        return !usernameIsOccupy;
    }
}
