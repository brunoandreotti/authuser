package com.brunoandreotti.authuser.services.impl;

import com.brunoandreotti.authuser.dtos.UserCourseRecordDTO;
import com.brunoandreotti.authuser.exceptions.SubscriptionAlreadyExistsException;
import com.brunoandreotti.authuser.models.UserCourseModel;
import com.brunoandreotti.authuser.models.UserModel;
import com.brunoandreotti.authuser.repository.UserCourseRepository;
import com.brunoandreotti.authuser.services.UserCourseService;
import com.brunoandreotti.authuser.services.UserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserCourseServiceImpl implements UserCourseService {

    final UserCourseRepository userCourseRepository;
    final UserService userService;


    public UserCourseServiceImpl(UserCourseRepository userCourseRepository, UserService userService) {
        this.userCourseRepository = userCourseRepository;
        this.userService = userService;
    }

  @Override
  public UserCourseModel saveSubscriptionUserInCourse(UUID userId, UserCourseRecordDTO userCourseRecordDTO) {

      UUID courseId = userCourseRecordDTO.courseId();
      UserModel user = userService.findById(userId);

      if (checkIfSubscriptionExists(user, courseId)) {
          throw new SubscriptionAlreadyExistsException("User: " + user.getUserId() + " already subscribed in course " + courseId);
      }

      return save(user.toUserCourseModel(courseId));

  }

    @Override
    public Boolean checkIfSubscriptionExists (UserModel user, UUID courseId) {
        return userCourseRepository.existsByUserAndCourseId(user, courseId);

    }

    @Override
    public UserCourseModel save(UserCourseModel userCourseModel) {
        return userCourseRepository.save(userCourseModel);
    }
}
