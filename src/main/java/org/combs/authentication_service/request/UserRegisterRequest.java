package org.combs.authentication_service.request;


import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.combs.authentication_service.enums.RoleType;
import org.combs.authentication_service.validation.annotations.EmailNotOccupy;
import org.combs.authentication_service.validation.annotations.PasswordMatch;
import org.combs.authentication_service.validation.annotations.UserNameNotOccupy;

@Getter
@Setter
@PasswordMatch
public class UserRegisterRequest {

    @NotBlank(message = "Username is empty")
    @Size(min = 4, max = 20, message = "Length of username should be within 4 and 20 chars")
    @UserNameNotOccupy
    private String username;

    @Email(message = "Incorrect email")
    @NotBlank(message = "Email is empty")
    @EmailNotOccupy
    private String email;

    @NotBlank(message = "Password is empty")
    private String password;

    @NotBlank(message = "Confirm password is empty")
    private String passwordConfirm;

    @NotBlank(message = "Full name is empty")
    private String fullName;

    @NotBlank(message = "Role is empty")
    private RoleType role;

    @JsonCreator
    public static RoleType fromString(String stringRole) {
        return stringRole == null ? RoleType.STUDENT : RoleType.valueOf(stringRole.toUpperCase());
    }
}
