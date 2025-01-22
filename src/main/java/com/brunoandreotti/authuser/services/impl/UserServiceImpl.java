package com.brunoandreotti.authuser.services.impl;

import com.brunoandreotti.authuser.dtos.UserRecordDTO;
import com.brunoandreotti.authuser.enums.UserStatus;
import com.brunoandreotti.authuser.enums.UserType;
import com.brunoandreotti.authuser.exceptions.NotFoundException;
import com.brunoandreotti.authuser.exceptions.DataAlreadyExistsException;
import com.brunoandreotti.authuser.models.UserModel;
import com.brunoandreotti.authuser.repository.UserRepository;
import com.brunoandreotti.authuser.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<UserModel> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public UserModel findById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found!"));
    }

    @Override
    public void deleteById(UUID userId) {
        userRepository.delete(findById(userId));
    }

    @Override
    public UserModel registerUser(UserRecordDTO userRecordDTO) {

        String username = userRecordDTO.username();
        String email = userRecordDTO.email();

        if (userRepository.existsByUsername(username)) {
            throw new DataAlreadyExistsException(String.format("User with username '%s' already exists! ", username));
        }

        if (userRepository.existsByEmail(email)) {
            throw new DataAlreadyExistsException(String.format("User with email '%s' already exists! ", email));
        }


        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDTO, userModel);

        userModel.setUserStatus(UserStatus.ACTIVE);
        userModel.setUserType(UserType.USER);

        userModel.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));

        return userRepository.save(userModel);
    }

    @Override
    public UserModel updateUser(UUID userId, UserRecordDTO userRecordDTO) {
        var user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found!"));

        user.setFullName(userRecordDTO.fullName());
        user.setPhoneNumber(userRecordDTO.phoneNumber());
        user.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));

        return userRepository.save(user);
    }

    @Override
    public void updateUserPassword(UUID userId, UserRecordDTO userRecordDTO) {
        var user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found!"));

        if (!Objects.equals(user.getPassword(), userRecordDTO.oldPassword())) {
            throw new NotFoundException("Passwords do not match!");
        }

        user.setPassword(userRecordDTO.password());
        user.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));

        userRepository.save(user);
    }

    @Override
    public UserModel updateUserImage(UUID userId, UserRecordDTO userRecordDTO) {
        var user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found!"));

        user.setImageUrl(userRecordDTO.imageUrl());
        user.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));

        return userRepository.save(user);
    }




}
