package org.combs.authentication_service.service;


import lombok.RequiredArgsConstructor;
import org.combs.authentication_service.UserMapper;
import org.combs.authentication_service.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationUserDetailsService implements UserDetailsService {
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.mapUserToUserDetails(userService.getUserByUserName(username));
    }
}
