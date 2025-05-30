package org.combs.authentication_service.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.combs.authentication_service.entity.User;
import org.combs.authentication_service.mapper.UserMapper;
import org.combs.authentication_service.request.UserPersistRequest;
import org.combs.authentication_service.response.UserResponse;
import org.combs.authentication_service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        UserResponse response = userMapper.entityToResponse(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<UserResponse> getUserByName(@RequestParam("username") String username){
        User user = userService.getUserByUserName(username);
        UserResponse response = userMapper.entityToResponse(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Validated UserPersistRequest request){
        User user = userService.createUser(request);
        UserResponse response = userMapper.entityToResponse(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,
                                                   @RequestBody @Validated UserPersistRequest request){
        User user = userService.updateUser(id, request);
        UserResponse response = userMapper.entityToResponse(user);
        return ResponseEntity.ok(response);
    }
}
