package org.combs.authentication_service.repository;

import org.combs.authentication_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

    boolean  existsByEmail(String email);
    boolean existsByUsername(String username);
}
