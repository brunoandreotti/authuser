package com.brunoandreotti.authuser.controllers;

import com.brunoandreotti.authuser.dtos.UserRecordDTO;
import com.brunoandreotti.authuser.models.UserModel;
import com.brunoandreotti.authuser.services.UserService;
import com.brunoandreotti.authuser.specifications.SpecificationTemplate;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<UserModel>> getAllUsers(SpecificationTemplate.UserSpec spec, Pageable pageable, @RequestParam(required = false) UUID courseId) {


        Page<UserModel> userPages = courseId == null
                ? userService.findAll(spec, pageable)
                : userService.findAll(SpecificationTemplate.userCourseId(courseId).and(spec), pageable);

        if (!userPages.isEmpty()) {
            for (UserModel user : userPages.toList()) {
                user.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).getUserById(user.getUserId())).withSelfRel());
            }
        }
        log.info("c=UserController m=getAllUsers msg=GET getAllUsers");
        return ResponseEntity.status(HttpStatus.OK).body(userPages);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable UUID userId) {
        log.info("c=UserController m=getUserById msg=GET getUserById userId={}", userId);
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUserById(@PathVariable UUID userId) {
        userService.deleteById(userId);
        log.info("c=UserController m=deleteUserById msg=DELETE deleteUserById userId={}", userId);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUserById(@PathVariable UUID userId,
                                                 @RequestBody
                                                 @Validated(UserRecordDTO.UserView.UserPut.class)
                                                 @JsonView(UserRecordDTO.UserView.UserPut.class)
                                                 UserRecordDTO userRecordDTO) {
        log.info("c=UserController m=updateUserById msg=PUT updateUserById userId={} userData={}", userId, userRecordDTO);
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userId, userRecordDTO));
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<Object> updateUserPasswordById(@PathVariable UUID userId,
                                                         @RequestBody
                                                         @Validated(UserRecordDTO.UserView.PasswordPut.class)
                                                         @JsonView(UserRecordDTO.UserView.PasswordPut.class)
                                                         UserRecordDTO userRecordDTO) {
        userService.updateUserPassword(userId, userRecordDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Password updated successfully!");
    }

    @PutMapping("/{userId}/image")
    public ResponseEntity<Object> updateUserImageById(@PathVariable UUID userId,
                                                      @RequestBody
                                                      @Validated(UserRecordDTO.UserView.ImagePut.class)
                                                      @JsonView(UserRecordDTO.UserView.ImagePut.class)
                                                      UserRecordDTO userRecordDTO) {

        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserImage(userId, userRecordDTO));
    }
}
