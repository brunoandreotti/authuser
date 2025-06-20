package com.brunoandreotti.authuser.controllers;

import com.brunoandreotti.authuser.dtos.InstructorRecordDTO;
import com.brunoandreotti.authuser.models.UserModel;
import com.brunoandreotti.authuser.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("instructors")
public class InstructorController {

    private final UserService userService;

    public InstructorController(UserService userService) {
        this.userService = userService;    }

    @PostMapping("subscription")
    public ResponseEntity<UserModel> saveSubscriptionInstructor(@RequestBody @Valid InstructorRecordDTO instructorRecordDTO) {

        return ResponseEntity.status(HttpStatus.OK).body(userService.registerInstructor(instructorRecordDTO));
    }
}
