package com.brunoandreotti.authuser.services;

import com.brunoandreotti.authuser.dtos.CourseRecordDTO;
import com.brunoandreotti.authuser.dtos.UserCourseRecordDTO;
import com.brunoandreotti.authuser.models.UserCourseModel;
import com.brunoandreotti.authuser.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

public interface UserCourseService {

    UserCourseModel saveSubscriptionUserInCourse(UUID userId, UserCourseRecordDTO userCourseRecordDTO);
    Boolean checkIfSubscriptionExists (UserModel user, UUID courseId);
    UserCourseModel save(UserCourseModel userCourseModel);
    void deleteUserCourseByCourse(UUID courseId);
    Page<CourseRecordDTO> getAllCoursesByUser(UUID userId, Pageable pageable);

}
