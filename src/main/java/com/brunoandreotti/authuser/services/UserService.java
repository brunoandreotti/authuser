package com.brunoandreotti.authuser.services;

import com.brunoandreotti.authuser.models.UserModel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserModel> findAll();

    UserModel findById(UUID id);

    void deleteById(UUID userId);
}
