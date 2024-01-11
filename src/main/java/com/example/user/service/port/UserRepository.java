package com.example.user.service.port;

import com.example.user.domain.User;
import com.example.user.domain.UserStatus;
import com.example.user.infrastructure.UserEntity;

import java.util.Optional;

public interface UserRepository {

    User getById(long id);
    Optional<User> findByIdAndStatus(long id, UserStatus userStatus);

    Optional<User> findByEmailAndStatus(String email, UserStatus userStatus);

    User save(User user);

    Optional<User> findById(long id);
}
