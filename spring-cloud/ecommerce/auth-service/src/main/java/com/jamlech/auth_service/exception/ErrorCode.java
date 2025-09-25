package com.jamlech.auth_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND("USER_NOT_FOUND", "User not found with id::%s", NOT_FOUND),

    CHANGE_PASSWORD_MISMATCH("CHANGE_PASSWORD_MISMATCH", "Current password and ne password are not the same", BAD_REQUEST),
    INVALID_CURRENT_PASSWORD("INVALID_CURRENT_PASSWORD", "Old password is not correct", BAD_REQUEST),
    ACCOUNT_ALREADY_DEACTIVATED("ACCOUNT_ALREADY_DEACTIVATED", "Account is already deactivated", BAD_REQUEST),
    ACCOUNT_ALREADY_REACTIVATED("ACCOUNT_ALREADY_REACTIVATED", "Account is already reactivated", BAD_REQUEST),
    EMAIL_ALREADY_EXISTS("EMAIL_ALREADY_EXISTS", "Email already exists", BAD_REQUEST),
    PHONE_NUMBER_ALREADY_EXISTS("PHONE_NUMBER_ALREADY_EXISTS", "Phone number already exists", BAD_REQUEST),
    ERR_USER_DISABLED("ERR_USER_DISABLED",
            "User account is disabled, please activate your account or contact the administrator",
            UNAUTHORIZED),
    BAD_CREDENTIALS("BAD_CREDENTIALS", "Username and / or password is incorrect", UNAUTHORIZED),
    INTERNAL_EXCEPTION("INTERNAL_EXCEPTION",
            "An internal exception occurred, please try again or contact the admin",
            HttpStatus.INTERNAL_SERVER_ERROR),
    USERNAME_NOT_FOUND("USERNAME_NOT_FOUND", "Cannot find user with the provided username", NOT_FOUND),
    CATEGORY_ALREADY_EXISTS_FOR_USER("CATEGORY_ALREADY_EXISTS_FOR_USER","Category already exists for user", CONFLICT);

    private final String code;
    private final String defaultMessage;
    private final HttpStatus httpStatus;


    ErrorCode(String code, String defaultMessage, HttpStatus httpStatus) {
        this.code = code;
        this.defaultMessage = defaultMessage;
        this.httpStatus = httpStatus;
    }
}
