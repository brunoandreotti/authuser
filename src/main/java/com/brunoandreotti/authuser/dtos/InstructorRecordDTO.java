package com.brunoandreotti.authuser.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record InstructorRecordDTO(@NotNull(message = "UserId is mandatory") UUID userId) {
}
