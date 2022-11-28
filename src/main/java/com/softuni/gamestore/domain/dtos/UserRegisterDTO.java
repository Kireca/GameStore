package com.softuni.gamestore.domain.dtos;

import java.util.regex.Pattern;

import static com.softuni.gamestore.constants.Messages.*;
import static com.softuni.gamestore.constants.Patterns.EMAIL_PATTERN;
import static com.softuni.gamestore.constants.Patterns.PASSWORD_PATTERN;

public class UserRegisterDTO {

    private String email;

    private String password;

    private String confirmPassword;

    private String fullName;


    public UserRegisterDTO(String email, String password, String confirmPassword, String fullName) {
        setEmail(email);
        setPassword(password);
        setConfirmPassword(confirmPassword);
        setFullName(fullName);

    }


    public String successfulRegisterFormat() {
      return   String.format(SUCCESSFUL_REGISTER_USER, getFullName());
    }


    public void setEmail(String email) {
        final boolean isEmailValid = Pattern.matches(EMAIL_PATTERN, email);

        if (!isEmailValid) {
            throw new IllegalArgumentException(EMAIL_NOT_VALID_MESSAGE);
        }

        this.email = email;
    }

    public void setPassword(String password) {
        final boolean isPasswordValid = Pattern.matches(PASSWORD_PATTERN, password);

        if (!isPasswordValid) {
            throw new IllegalArgumentException(USERNAME_OR_PASSWORD_NOT_VALID_MESSAGE);
        }
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        if (!this.password.equals(confirmPassword)) {
            throw new IllegalArgumentException(PASSWORDS_MISS_MATCH_MESSAGE);
        }
        this.confirmPassword = confirmPassword;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public String getEmail() {
        return email;
    }
}
