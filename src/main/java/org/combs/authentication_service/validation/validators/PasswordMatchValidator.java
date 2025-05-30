package org.combs.authentication_service.validation.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.combs.authentication_service.validation.annotations.PasswordMatch;
import org.combs.authentication_service.request.UserPersistRequest;

@Slf4j
public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, UserPersistRequest> {

    @Override
    public boolean isValid(UserPersistRequest userRequest, ConstraintValidatorContext context) {
        String password = userRequest.getPassword();
        String passwordConfirm = userRequest.getPasswordConfirm();

        if (password.isBlank() || passwordConfirm.isBlank()){
            return false;
        }
        if (!password.equals(passwordConfirm)){
            log.info("Passwrod doesnt match");
            return false;
        }
        userRequest.setPasswordsIsMatch(true);
        return true;
    }
}
