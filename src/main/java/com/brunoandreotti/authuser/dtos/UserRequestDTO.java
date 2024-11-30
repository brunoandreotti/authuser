package com.brunoandreotti.authuser.dtos;

public record UserRequestDTO(String username,
                             String email,
                             String password,
                             String oldPassword,
                             String fullName,
                             String imageUrl) {

    public interface UserView {
        interface RegistrationPost {}
        interface USerPut {}
        interface PasswordPut {}
        interface ImagePut {}
    }
}
