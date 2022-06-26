package com.truetree.exception.jwt;

import com.truetree.exception.common.ErrorTemplate;
import lombok.Getter;

@Getter
public class JwtCustomException extends RuntimeException {
    private final String message;
    public JwtCustomException(ErrorTemplate errorTemplate) {
        this.message = errorTemplate.getMessage();
    }
}
