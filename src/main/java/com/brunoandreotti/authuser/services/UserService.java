package com.brunoandreotti.authuser.services;

import com.brunoandreotti.authuser.dtos.UserRecordDTO;
import com.brunoandreotti.authuser.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

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

    Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable);
}
