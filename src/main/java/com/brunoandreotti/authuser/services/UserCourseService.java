package com.brunoandreotti.authuser.services;

import com.brunoandreotti.authuser.dtos.UserCourseRecordDTO;
import com.brunoandreotti.authuser.models.UserCourseModel;
import com.brunoandreotti.authuser.models.UserModel;

import java.util.UUID;

public interface UserCourseService {

    UserCourseModel saveSubscriptionUserInCourse(UUID userId, UserCourseRecordDTO userCourseRecordDTO);
    Boolean checkIfSubscriptionExists (UserModel user, UUID courseId);
    UserCourseModel save(UserCourseModel userCourseModel);
}
