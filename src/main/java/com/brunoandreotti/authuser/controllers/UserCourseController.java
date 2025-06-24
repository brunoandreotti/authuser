package com.brunoandreotti.authuser.controllers;

import com.brunoandreotti.authuser.clients.CourseClient;
import com.brunoandreotti.authuser.dtos.CourseRecordDTO;
import com.brunoandreotti.authuser.dtos.UserCourseRecordDTO;
import com.brunoandreotti.authuser.services.UserCourseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserCourseController {


    private final UserCourseService userCourseService;

    public UserCourseController(UserCourseService userCourseService) {
        this.userCourseService = userCourseService;
    }

    @GetMapping("/users/{userId}/courses")
    public ResponseEntity<Page<CourseRecordDTO>> getAllCoursesByUser(
            @PathVariable(value = "userId") UUID userId,
            @PageableDefault(sort = "courseId", direction = Sort.Direction.ASC) Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK).body(userCourseService.getAllCoursesByUser(userId, pageable));
    }

    @PostMapping("/users/{userId}/courses/subscription")
    public ResponseEntity<Object> saveSubscriptionUserInCourse(
            @PathVariable(value = "userId") UUID userId,
            @RequestBody @Valid UserCourseRecordDTO userCourseRecordDTO
            ) {

        return ResponseEntity.status(HttpStatus.CREATED).body(userCourseService.saveSubscriptionUserInCourse(userId, userCourseRecordDTO));
    }

    @DeleteMapping("/users/courses/{courseId}")
    public ResponseEntity<Object> deleteUserCourseByCourse(
            @PathVariable(value = "courseId") UUID courseId
    ) {
        userCourseService.deleteUserCourseByCourse(courseId);
        return ResponseEntity.status(HttpStatus.OK).body("UserCourse deleted successfully");
    }
}
