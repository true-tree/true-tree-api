package com.truetree.exception.jwt;

import com.truetree.exception.common.ErrorTemplate;

public enum JwtError implements ErrorTemplate {
    JWT_ACCESS_TOKEN_TIME_EXPIRED("AccessToken 시간이 만료 되었습니다."),
    JWT_REFRESH_TOKEN_TIME_EXPIRED("RefreshToken 시간이 만료 되었습니다."),
    JWT_VALID_NOT_SIGNATURE("토큰의 서명값이 올바르지 않습니다."),
    JWT_VALID_NOT_INCORRECT_CLAIM("토큰의 필수 클레임값이 존재 하지 않습니다.");

    private final String message;

    JwtError(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
