package org.combs.authentication_service.service;


import lombok.RequiredArgsConstructor;

import org.combs.authentication_service.entity.User;
import org.combs.authentication_service.exeptions.UserNotFoundException;
import org.combs.authentication_service.mapper.UserMapper;
import org.combs.authentication_service.repository.UserRepository;
import org.combs.authentication_service.request.UserPersistRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;


    public User getUserByUserName(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found by name:" + username));
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found by id:" + id));
    }

    public User createUser(UserPersistRequest request) {
        User user = userMapper.fromRegisterRequestToUser(request);
        return userRepository.save(user);
    }

    public User updateUser(Long id, UserPersistRequest request) {
        User user = getUserById(id);
        userMapper.updateUserFromRequest(user, request);
        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }


    public boolean isExistsByUsername(String username){
        return userRepository.existsByUsername(username);
    }
    public boolean isExistsByEmail(String email){
        return userRepository.existsByEmail(email);
    }
}
