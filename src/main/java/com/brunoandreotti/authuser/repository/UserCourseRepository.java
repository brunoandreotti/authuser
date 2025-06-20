package com.brunoandreotti.authuser.repository;

import com.brunoandreotti.authuser.models.UserCourseModel;
import com.brunoandreotti.authuser.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserCourseRepository extends JpaRepository<UserCourseModel, UUID> {

    Boolean existsByUserAndCourseId(UserModel user, UUID courseId);

    @Query(value = "select * from tb_users_courses where user_id = :userId", nativeQuery = true)
    List<UserCourseModel> findAllUserCoursesIntoUser(@Param("userId")UUID userId);
}
