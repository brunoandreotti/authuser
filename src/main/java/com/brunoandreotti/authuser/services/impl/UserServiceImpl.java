package com.brunoandreotti.authuser.services.impl;

import com.brunoandreotti.authuser.dtos.InstructorRecordDTO;
import com.brunoandreotti.authuser.dtos.UserRecordDTO;
import com.brunoandreotti.authuser.enums.UserStatus;
import com.brunoandreotti.authuser.enums.UserType;
import com.brunoandreotti.authuser.exceptions.NotFoundException;
import com.brunoandreotti.authuser.exceptions.DataAlreadyExistsException;
import com.brunoandreotti.authuser.models.UserCourseModel;
import com.brunoandreotti.authuser.models.UserModel;
import com.brunoandreotti.authuser.repository.UserCourseRepository;
import com.brunoandreotti.authuser.repository.UserRepository;
import com.brunoandreotti.authuser.services.UserService;
import com.brunoandreotti.authuser.specifications.SpecificationTemplate;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;
    final private UserCourseRepository userCourseRepository;

    public UserServiceImpl(UserRepository userRepository, UserCourseRepository userCourseRepository) {
        this.userRepository = userRepository;
        this.userCourseRepository = userCourseRepository;
    }

    @Override
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable) {
        return userRepository.findAll(spec, pageable);
    }

    @Override
    public UserModel registerInstructor(InstructorRecordDTO instructorRecordDTO) {
        UserModel user = findById(instructorRecordDTO.userId());

        user.setUserType(UserType.INSTRUCTOR);
        user.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));

        return userRepository.save(user);
    }

    @Override
    public UserModel findById(UUID userId) {
        log.info("c=UserServiceImpl m=findById msg=Getting user by id userId={}", userId);
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found!"));
    }

    @Transactional
    @Override
    public void deleteById(UUID userId) {
        List<UserCourseModel> userCourseList = userCourseRepository.findAllUserCoursesIntoUser(userId);

        if (!userCourseList.isEmpty()) {
            userCourseRepository.deleteAll(userCourseList);
        }

        log.info("c=UserController m=deleteById msg=Deleting user by id userId={}", userId);
        userRepository.delete(findById(userId));
    }

    @Override
    public UserModel registerUser(UserRecordDTO userRecordDTO) {

        String username = userRecordDTO.username();
        String email = userRecordDTO.email();

        if (userRepository.existsByUsername(username)) {
            log.warn("c=UserServiceImpl m=registerUser msg=Username {} already exists", userRecordDTO.username());
            throw new DataAlreadyExistsException(String.format("User with username '%s' already exists! ", username));
        }

        if (userRepository.existsByEmail(email)) {
            log.warn("c=UserServiceImpl m=registerUser msg=Email {} already exists", userRecordDTO.email());
            throw new DataAlreadyExistsException(String.format("User with email '%s' already exists! ", email));
        }


        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDTO, userModel);

        userModel.setUserStatus(UserStatus.ACTIVE);
        userModel.setUserType(UserType.USER);

        userModel.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));

        log.info("c=UserServiceImpl m=registerUser userName={} email={} msg=User saved successfully", userRecordDTO.username(), userRecordDTO.email());
        return userRepository.save(userModel);

    }

    @Override
    public UserModel updateUser(UUID userId, UserRecordDTO userRecordDTO) {
        var user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found!"));

        user.setFullName(userRecordDTO.fullName());
        user.setPhoneNumber(userRecordDTO.phoneNumber());
        user.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        log.info("c=UserServiceImpl m=updateUser msg=Updating user userId={} userData={}", userId, userRecordDTO);
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
