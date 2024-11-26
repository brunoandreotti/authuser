package com.brunoandreotti.authuser.services.impl;

import com.brunoandreotti.authuser.exceptions.NotFoundException;
import com.brunoandreotti.authuser.models.UserModel;
import com.brunoandreotti.authuser.repository.UserRepository;
import com.brunoandreotti.authuser.services.UserService;
import org.springframework.stereotype.Service;

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
}
