package com.brunoandreotti.authuser.dtos;

import com.brunoandreotti.authuser.enums.CourseLevel;
import com.brunoandreotti.authuser.enums.CourseStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record CourseRecordDTO(UUID courseId,
                              String name,
                              String description,
                              String imageUrl,
                              CourseStatus courseStatus,
                              CourseLevel courseLevel,
                              UUID userInstructor) {
}
