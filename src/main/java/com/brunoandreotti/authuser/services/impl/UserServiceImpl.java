package com.brunoandreotti.authuser.services.impl;

import com.brunoandreotti.authuser.dtos.UserRequestDTO;
import com.brunoandreotti.authuser.enums.UserStatus;
import com.brunoandreotti.authuser.enums.UserType;
import com.brunoandreotti.authuser.exceptions.NotFoundException;
import com.brunoandreotti.authuser.exceptions.DataAlreadyExistsException;
import com.brunoandreotti.authuser.models.UserModel;
import com.brunoandreotti.authuser.repository.UserRepository;
import com.brunoandreotti.authuser.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
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
    public UserModel findById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public void deleteById(UUID userId) {
         userRepository.delete(findById(userId));
    }

    @Override
    public UserModel registerUser(UserRequestDTO userRequestDTO) {

        String username = userRequestDTO.username();
        String email = userRequestDTO.email();

        if (userRepository.existsByUsername(username)) {
            throw new DataAlreadyExistsException(String.format("User with username '%s' already exists! ", username));
        }

        if (userRepository.existsByEmail(email)) {
            throw new DataAlreadyExistsException(String.format("User with email '%s' already exists! ", email));
        }


        var userModel = new UserModel();
        BeanUtils.copyProperties(userRequestDTO, userModel);

        userModel.setUserStatus(UserStatus.ACTIVE);
        userModel.setUserType(UserType.USER);

        userModel.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));

        return userRepository.save(userModel);
    }


}
