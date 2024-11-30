package com.brunoandreotti.authuser.repository;

import com.brunoandreotti.authuser.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<UserModel, UUID> {

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
