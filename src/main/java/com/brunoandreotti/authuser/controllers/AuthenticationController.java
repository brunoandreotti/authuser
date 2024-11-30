package com.brunoandreotti.authuser.controllers;

import com.brunoandreotti.authuser.dtos.UserRecordDTO;
import com.brunoandreotti.authuser.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody
                                               @JsonView(UserRecordDTO.UserView.RegistrationPost.class)
                                               UserRecordDTO userRecordDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(userRecordDTO));
    }
}
