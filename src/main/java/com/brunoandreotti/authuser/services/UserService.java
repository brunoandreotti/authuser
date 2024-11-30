package com.brunoandreotti.authuser.services;

import com.brunoandreotti.authuser.dtos.UserRequestDTO;
import com.brunoandreotti.authuser.models.UserModel;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserModel> findAll();

    UserModel findById(UUID id);

    void deleteById(UUID userId);

    UserModel registerUser(UserRequestDTO userRequestDTO);
}
