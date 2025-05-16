package org.combs.authentication_service.validation.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.combs.authentication_service.validation.annotations.PasswordMatch;
import org.combs.authentication_service.request.UserRegisterRequest;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, UserRegisterRequest> {

    @Override
    public boolean isValid(UserRegisterRequest userRequest, ConstraintValidatorContext context) {
        String password = userRequest.getPassword();
        String passwordConfirm = userRequest.getPasswordConfirm();

        if (password.isBlank() || passwordConfirm.isBlank()){
            return false;
        }
        if (!password.equals(passwordConfirm)){
            return false;
        }
        return true;
    }
}
