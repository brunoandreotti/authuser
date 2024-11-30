package com.brunoandreotti.authuser.services;

import com.brunoandreotti.authuser.dtos.UserRecordDTO;
import com.brunoandreotti.authuser.models.UserModel;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserModel> findAll();

    UserModel findById(UUID id);

    void deleteById(UUID userId);

    UserModel registerUser(UserRecordDTO userRecordDTO);

    UserModel updateUser(UUID userId, UserRecordDTO userRecordDTO);

    void updateUserPassword(UUID userId, UserRecordDTO userRecordDTO);

    UserModel updateUserImage(UUID userId, UserRecordDTO userRecordDTO);
}
