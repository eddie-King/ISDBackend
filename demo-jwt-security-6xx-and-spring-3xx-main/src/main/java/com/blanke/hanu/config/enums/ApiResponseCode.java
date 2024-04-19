package com.blanke.hanu.config.enums;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiResponseCode {
    SUCCESS(HttpStatus.OK, "200", "GLOBAL.SUCCESS"),
    UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500", "GLOBAL.SERVER_ERROR"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "400", "GLOBAL.BAD_REQUEST"),
    INPUT_PARAMS_INVALID(HttpStatus.BAD_REQUEST, "400", "INPUT_PARAMS_INVALID"),
    USERNAME_OR_PASSWORD_EMPTY(HttpStatus.BAD_REQUEST, "400", "USERNAME_OR_PASSWORD_EMPTY"),
    USERNAME_EXIST(HttpStatus.BAD_REQUEST, "400", "USERNAME_EXIST"),
    USER_NOT_EXIST(HttpStatus.BAD_REQUEST, "400", "USER.USER_NOT_EXIST"),

    PASSWORD_NOT_SAME(HttpStatus.BAD_REQUEST, "400", "USER.PASSWORD_NOT_SAME"),
    CONFIRM_PASSWORD_NOT_SAME_NEW_PASSWORD(HttpStatus.BAD_REQUEST, "400", "USER.CONFIRM_PASSWORD_NOT_SAME_NEW_PASSWORD"),
    EMAIL_OR_PASSWORD_INVALID(HttpStatus.BAD_REQUEST, "400", "USER.EMAIL_OR_PASSWORD_INVALID"),
    NOT_FOUND(HttpStatus.NOT_FOUND,"404","PRODUCT_NOT_FOUND")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

    ApiResponseCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}