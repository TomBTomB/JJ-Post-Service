package com.tombtomb.jjpostservice.repository;

import com.tombtomb.jjpostservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);

    List<User> findAllByUsernameContainingOrDisplayNameContaining(String username, String displayName);
}