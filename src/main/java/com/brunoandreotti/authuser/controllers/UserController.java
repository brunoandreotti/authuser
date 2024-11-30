package com.brunoandreotti.authuser.controllers;

import com.brunoandreotti.authuser.dtos.UserRecordDTO;
import com.brunoandreotti.authuser.models.UserModel;
import com.brunoandreotti.authuser.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable UUID userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUserById(@PathVariable UUID userId) {
        userService.deleteById(userId);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUserById(@PathVariable UUID userId,
                                                 @RequestBody
                                                 @JsonView(UserRecordDTO.UserView.UserPut.class)
                                                 UserRecordDTO userRecordDTO) {

        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userId, userRecordDTO));
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<Object> updateUserPasswordById(@PathVariable UUID userId,
                                                         @RequestBody
                                                         @JsonView(UserRecordDTO.UserView.PasswordPut.class)
                                                         UserRecordDTO userRecordDTO) {
        userService.updateUserPassword(userId, userRecordDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Password updated successfully!");
    }

    @PutMapping("/{userId}/image")
    public ResponseEntity<Object> updateUserImageById(@PathVariable UUID userId,
                                                      @RequestBody
                                                      @JsonView(UserRecordDTO.UserView.ImagePut.class)
                                                      UserRecordDTO userRecordDTO) {

        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserImage(userId, userRecordDTO));
    }
}
