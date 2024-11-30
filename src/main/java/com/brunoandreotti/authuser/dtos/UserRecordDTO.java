package com.brunoandreotti.authuser.dtos;


import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



public record UserRecordDTO(
                            @NotBlank(message = "Username must not be blank!", groups = UserView.RegistrationPost.class)
                            @Size(min = 4, max = 50, message = "Username must be between 4 and 50!", groups = UserView.RegistrationPost.class)
                            @JsonView(UserView.RegistrationPost.class)
                            String username,

                            @NotBlank(message = "E-mail must not be blank!", groups = UserView.RegistrationPost.class)
                            @Email(message = "E-mail format not valid!", groups = UserView.RegistrationPost.class)
                            @JsonView(UserView.RegistrationPost.class)
                            String email,

                            @NotBlank(message = "Password must not be blank!", groups = {UserView.RegistrationPost.class, UserView.PasswordPut.class})
                            @Size(min = 6, max = 20, message = "Password must be between 6 and 20!", groups = {UserView.RegistrationPost.class, UserView.PasswordPut.class})
                            @JsonView({UserView.RegistrationPost.class, UserView.PasswordPut.class})
                            String password,

                            @NotBlank(message = "Old password must not be blank!", groups = UserView.PasswordPut.class)
                            @Size(min = 6, max = 20, message = "Old password must be between 6 and 20!", groups = UserView.PasswordPut.class)
                            @JsonView(UserView.PasswordPut.class)
                            String oldPassword,

                            @NotBlank(message = "Full name must not be blank!", groups = {UserView.RegistrationPost.class, UserView.UserPut.class})
                            @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
                            String fullName,

                            @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
                            String phoneNumber,

                            @NotBlank(message = "ImageUrl name must not be blank!", groups = UserView.ImagePut.class)
                            @JsonView(UserView.ImagePut.class)
                            String imageUrl) {

    public interface UserView {
        interface RegistrationPost {}
        interface UserPut {}
        interface PasswordPut {}
        interface ImagePut {}
    }
}
