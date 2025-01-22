package com.brunoandreotti.authuser.dtos;


import com.brunoandreotti.authuser.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;



public record UserRecordDTO(
                            @NotBlank(message = StringUtils.USERNAME_BLANK_ERROR, groups = UserView.RegistrationPost.class)
                            @Size(min = 4, max = 50, message = StringUtils.USERNAME_SIZE_ERROR, groups = UserView.RegistrationPost.class)
                            @JsonView(UserView.RegistrationPost.class)
                            String username,

                            @NotBlank(message = StringUtils.EMAIL_BLANK_ERROR, groups = UserView.RegistrationPost.class)
                            @Email(message = StringUtils.EMAIL_FORMAT_ERROR, groups = UserView.RegistrationPost.class)
                            @JsonView(UserView.RegistrationPost.class)
                            String email,

                            @NotBlank(message = StringUtils.PASSWORD_BLANK_ERROR, groups = {UserView.RegistrationPost.class, UserView.PasswordPut.class})
                            @Size(min = 6, max = 20, message = StringUtils.PASSWORD_SIZE_ERROR, groups = {UserView.RegistrationPost.class, UserView.PasswordPut.class})
                            @JsonView({UserView.RegistrationPost.class, UserView.PasswordPut.class})
                            @Pattern(regexp = StringUtils.PASSWORD_REGEX, groups = {UserView.RegistrationPost.class, UserView.PasswordPut.class})
                            String password,

                            @NotBlank(message = StringUtils.OLD_PASSWORD_BLANK_ERROR, groups = UserView.PasswordPut.class)
                            @Size(min = 6, max = 20, message = StringUtils.OLD_PASSWORD_SIZE_ERROR, groups = UserView.PasswordPut.class)
                            @Pattern(regexp = StringUtils.PASSWORD_REGEX, groups = UserView.PasswordPut.class)
                            @JsonView(UserView.PasswordPut.class)
                            String oldPassword,

                            @NotBlank(message = StringUtils.FULL_NAME_BLANK_ERROR, groups = {UserView.RegistrationPost.class, UserView.UserPut.class})
                            @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
                            String fullName,

                            @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
                            String phoneNumber,

                            @NotBlank(message = StringUtils.IMAGE_URL_BLANK_ERROR, groups = UserView.ImagePut.class)
                            @JsonView(UserView.ImagePut.class)
                            String imageUrl) {

    public interface UserView {
        interface RegistrationPost {}
        interface UserPut {}
        interface PasswordPut {}
        interface ImagePut {}
    }
}
