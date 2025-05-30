package org.combs.authentication_service.mapper;


import lombok.RequiredArgsConstructor;
import org.combs.authentication_service.entity.User;
import org.combs.authentication_service.request.UserPersistRequest;
import org.combs.authentication_service.response.UserResponse;
import org.combs.authentication_service.service.RoleService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    /**
     * Method map register and update request to User Entity
     *
     * */
    public User fromRegisterRequestToUser(UserPersistRequest request){
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

    public void updateUserFromRequest(User user, UserPersistRequest request){
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setRole(roleService.getRoleByRoleType(request.getRole()));
    }

    public UserResponse entityToResponse(User user) {
        return UserResponse.builder()
                .fullName(user.getFullName())
                .email(user.getEmail())
                .username(user.getUsername())
                .role(user.getRole()
                        .getName()
                        .toString()
                )
                .build();
    }
}
