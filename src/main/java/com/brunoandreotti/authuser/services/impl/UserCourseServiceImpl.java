package com.brunoandreotti.authuser.services.impl;

import com.brunoandreotti.authuser.repository.UserCourseRepository;
import com.brunoandreotti.authuser.services.UserCourseService;

public class UserCourseServiceImpl implements UserCourseService {

    final UserCourseRepository userCourseRepository;


    public UserCourseServiceImpl(UserCourseRepository userCourseRepository) {
        this.userCourseRepository = userCourseRepository;
    }
}
