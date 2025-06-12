package com.brunoandreotti.authuser.controllers;

import com.brunoandreotti.authuser.clients.CourseClient;
import com.brunoandreotti.authuser.dtos.CourseRecordDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserCourseController {

    private final CourseClient courseClient;

    public UserCourseController(CourseClient courseClient) {
        this.courseClient = courseClient;
    }

    @GetMapping("/users/{userId}/courses")
    public ResponseEntity<Page<CourseRecordDTO>> getAllCoursesByUser(
            @PathVariable(value = "userId") UUID userId,
            @PageableDefault(sort = "courseId", direction = Sort.Direction.ASC) Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK).body(courseClient.getAllCoursesByUser(userId, pageable));
    }
}
