package org.combs.authentication_service.mapper;


import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.combs.authentication_service.entity.User;
import org.combs.authentication_service.request.UserRegisterRequest;
import org.combs.authentication_service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    public User fromRequestToUser(UserRegisterRequest request){
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .passwordHash(
                        passwordEncoder.encode(request.getPassword())
                )
                .fullName(request.getFullName())
                .role(roleService.getRoleByRoleType(request.getRole()))
                .build();
    }
}
