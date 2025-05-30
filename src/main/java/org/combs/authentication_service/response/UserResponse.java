package org.combs.authentication_service.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {
    private String fullName;
    private String email;
    private String username;
    private String role;
}
