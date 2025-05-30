package org.combs.authentication_service.controllerAdvice;


import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.combs.authentication_service.request.UserPersistRequest;
import org.combs.authentication_service.validation.ValidationErrorResponse;
import org.combs.authentication_service.validation.Violation;
import org.combs.authentication_service.validation.annotations.PasswordMatch;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ValidationErrorHandlingControllerAdvice {
    /**
     * Метод для обработки ошибок валидации внутри тела запроса
     *
     * @param e - ConstraintViolationException - метод ловит это исключение и обрабаывает его получая из его тела все ошибки
     * @return ErrorResponse - который хранит в себе поле в котором была ошибка и сам текст ошибки
     */
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)

    public ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
        e.getConstraintViolations().forEach((el) -> log.info(el.getMessage()));
        final List<Violation> violations = e.getConstraintViolations().stream()
                .map(
                        violation -> new Violation(
                                violation.getPropertyPath().toString(),
                                violation.getMessage()
                        )
                )
                .collect(Collectors.toList());

        return new ValidationErrorResponse(violations);
    }


    /**
     * Метод ообрабатывает ошибки валидации в параметрах метода
     *
     * @param e MethodArgumentNotValidException - исключение из тела которого мы получаем ошибки валдации.
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {

        final BindingResult bindingResult = e.getBindingResult();
        final List<Violation> violations = bindingResult.getFieldErrors().stream()
                .map(error -> Violation.builder()
                        .fieldName(error.getField())
                        .message(error.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());

        passwordMatchViolationHandle(bindingResult, violations);
        return new ValidationErrorResponse(violations);
    }

    /**
     * Метод позволяет добавлятб ошибку не совпадения пароля в конечный массив ошибок
     * */
    private void passwordMatchViolationHandle(final BindingResult bindingResult, List<Violation> violations) {
        if (bindingResult.getTarget() instanceof UserPersistRequest request) {
            if (!request.isPasswordsIsMatch()) {
                Violation violation = new Violation("password", request.getClass().getAnnotation(PasswordMatch.class).message());
                violations.add(violation);
            }
        }
    }
}
