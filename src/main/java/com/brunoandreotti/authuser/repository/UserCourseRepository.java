package com.brunoandreotti.authuser.repository;

import com.brunoandreotti.authuser.models.UserCourseModel;
import com.brunoandreotti.authuser.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserCourseRepository extends JpaRepository<UserCourseModel, UUID> {

    Boolean existsByUserAndCourseId(UserModel user, UUID courseId);
}
