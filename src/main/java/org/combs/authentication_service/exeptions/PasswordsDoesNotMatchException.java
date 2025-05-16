package org.combs.authentication_service.exeptions;

public class PasswordsDoesNotMatchException extends RuntimeException{
    public PasswordsDoesNotMatchException() {
        super();
    }

    public PasswordsDoesNotMatchException(String message) {
        super(message);
    }

    public PasswordsDoesNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
