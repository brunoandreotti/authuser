package com.brunoandreotti.authuser.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserCourseRecordDTO(@NotNull(message = "CourseId is mandatory") UUID courseId
                                  ) {
}
