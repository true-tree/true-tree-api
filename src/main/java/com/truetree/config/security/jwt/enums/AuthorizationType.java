package com.truetree.config.security.jwt.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthorizationType {

    BEARER("Bearer");

    private final String type;

}
