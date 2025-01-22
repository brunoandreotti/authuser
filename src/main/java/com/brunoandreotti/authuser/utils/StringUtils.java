package com.brunoandreotti.authuser.utils;

public class StringUtils {

    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{6,20}$";
    public static final String USERNAME_BLANK_ERROR = "Username must not be blank!";
    public static final String USERNAME_SIZE_ERROR = "Username must not be blank!";
    public static final String EMAIL_BLANK_ERROR = "E-mail must not be blank!";
    public static final String EMAIL_FORMAT_ERROR = "E-mail format not valid!";
    public static final String PASSWORD_BLANK_ERROR = "Password must not be blank!!";
    public static final String PASSWORD_SIZE_ERROR = "Password must be between 6 and 20!";
    public static final String OLD_PASSWORD_BLANK_ERROR = "Old password must not be blank!!";
    public static final String OLD_PASSWORD_SIZE_ERROR = "Old password must be between 6 and 20!";
    public static final String FULL_NAME_BLANK_ERROR = "Full name must not be blank!";
    public static final String IMAGE_URL_BLANK_ERROR = "ImageUrl name must not be blank!";


}
