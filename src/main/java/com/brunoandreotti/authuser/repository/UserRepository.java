package com.brunoandreotti.authuser.repository;

import com.brunoandreotti.authuser.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface UserRepository extends JpaRepository<UserModel, UUID> {
}
